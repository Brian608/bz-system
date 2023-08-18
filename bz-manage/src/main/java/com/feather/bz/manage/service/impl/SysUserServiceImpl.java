package com.feather.bz.manage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.bz.common.constants.CoreConstant;
import com.feather.bz.common.constants.RedisConstants;
import com.feather.bz.common.domain.dto.UserTokenDTO;
import com.feather.bz.common.enums.UserErrorCodeEnum;
import com.feather.bz.common.exception.UserBizException;
import com.feather.bz.common.service.RedisService;
import com.feather.bz.common.utils.*;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.domain.bo.AddUserBO;
import com.feather.bz.manage.domain.dto.LoginDTO;
import com.feather.bz.manage.domain.vo.UserVO;
import com.feather.bz.manage.mapper.SysUserMapper;
import com.feather.bz.manage.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author feather
 * @since 2022-11-30
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private  final  SysUserMapper sysUserMapper;

    private  final RedisService redisService;

    @Value("${images.dir:}")
    private String imageDir;

    private  final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public Boolean registerUser(AddUserBO addUserBO ) throws IOException {
        ParamCheckUtil.checkObjectNonNull(addUserBO);
        this.checkUserExist(addUserBO);
        SysUser sysUser=new SysUser();
        String salt = RandomUtil.genRandomNumberStr(8);
        sysUser.setSalt(salt);
        BeanUtils.copyProperties(addUserBO,sysUser);
        String avatar = ImageUtils.saveBase64ImageToFile(addUserBO.getAvatar(),imageDir);
        sysUser.setAvatar(avatar);
        if (Objects.isNull(addUserBO.getBirthday())){
            sysUser.setBirthday(new Date());
        }
        try {
            sysUser.setPassword(MD5Utils.getMD5Str(addUserBO.getPassword().concat(salt)));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new UserBizException("加密异常");
        }
      return   this.save(sysUser);
    }
    public  void  checkUserExist(AddUserBO addUserBO){
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,addUserBO.getUsername()).or()
                .eq(SysUser::getMobile,addUserBO.getMobile());
        List<SysUser> sysUserList = sysUserMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(sysUserList)){
            throw  new UserBizException(UserErrorCodeEnum.USER_EXIST);
        }

    }

    public  SysUser getByUserName(String userName){
        ParamCheckUtil.checkStringNonEmpty(userName);
       return this.sysUserMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername,userName));
    }

    @Override
    public Map<String, String> login(LoginDTO loginDTO , HttpServletRequest request, HttpServletResponse response) throws Exception {
        ParamCheckUtil.checkObjectNonNull(loginDTO);
        SysUser byUserName = this.getByUserName(loginDTO.getUserName());
        if (Objects.isNull(byUserName)){
            throw  new UserBizException(UserErrorCodeEnum.USER_NOT_EXIST);
        }
        HttpSession session = request.getSession(true);
        Object verifyCode = session.getAttribute(CoreConstant.VERIFY_CODE);
        session.removeAttribute(CoreConstant.VERIFY_CODE);
        if (Objects.isNull(verifyCode)||!loginDTO.getVerifyCode().equalsIgnoreCase((String) verifyCode )){
            throw  new UserBizException(UserErrorCodeEnum.VERIFY_CODE_ERROR);
        }
        if (!MD5Utils.getMD5Str(loginDTO.getPassword().concat(byUserName.getSalt())).equals(byUserName.getPassword())){
            throw  new UserBizException(UserErrorCodeEnum.PASSWORD_ERROR);
        }
        UserTokenDTO userTokenDTO=new UserTokenDTO();
        BeanUtils.copyProperties(byUserName,userTokenDTO);
        StpUtil.login(loginDTO.getUserName());
        String token =  StpUtil.getTokenValue();
//        String token = JWTUtil.generateToken(userTokenDTO);
//        redisService.set(RedisConstants.USER+byUserName.getUsername(), token);
        CookieUtils.setCookie(request,response,"user", JsonUtil.object2Json(userTokenDTO),true);
        session.setMaxInactiveInterval(RedisConstants.DURATION);
        session.setAttribute("User",byUserName);
        Map<String, String> resultMap=new HashMap<>();
        resultMap.put("token",token);
        return resultMap;
    }

    @Override
    public Boolean logOut(String userName, HttpServletRequest request, HttpServletResponse response) {
//        boolean result = redisService.delete(RedisConstants.USER+userName);
//        if (!result) {
//            throw new UserBizException(UserErrorCodeEnum.LOGINOUT_ERROR);
//        }
        StpUtil.logoutByTokenValue(StpUtil.getTokenValue());
        CookieUtils.deleteCookie(request ,response,"user");
        return true;
    }

    @Override
    public List<UserVO> exportUser() {
        List<SysUser> sysUserList = this.getBaseMapper().selectList(null);
        return CollectionUtils.isEmpty(sysUserList)?Collections.emptyList(): com.feather.bz.common.utils.CollectionUtils.copy(sysUserList,UserVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 * * * * *")
    @Override
    public void encryptPhone() {
        List<String> unencryptedUserPhoneList = new ArrayList<>();
        for (int i = 0; i <101 ; i++) {
            unencryptedUserPhoneList.add(RandomUtil.genRandomNumberStr(6));
        }
        // 每批处理的大小
        int batchSize = 100;
        log.info("#unencryptedUserPhone size : [{}]",unencryptedUserPhoneList.size());
        Runnable processTask = unencryptedUserPhoneList.size() > batchSize ?
                () -> processInBatches(unencryptedUserPhoneList, batchSize) :
                () -> processBatch(unencryptedUserPhoneList);

        processTask.run();

    }

    private void processInBatches(List<String> unencryptedUserPhoneList, int batchSize) {
        for (int i = 0; i < unencryptedUserPhoneList.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, unencryptedUserPhoneList.size());
            List<String> batchList = unencryptedUserPhoneList.subList(i, endIndex);
            executorService.execute(() -> processBatch(batchList));
        }
    }

    private void processBatch(List<String> batchList) {
        log.info("Thread [{}] is starting to process [{}] data.", Thread.currentThread().getName(), batchList.size());
        batchList.forEach(p -> {
            try {
                MD5Utils.getMD5Str(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Thread [{}] finished processing [{}] data.", Thread.currentThread().getName(),
                batchList.size());

    }

}
