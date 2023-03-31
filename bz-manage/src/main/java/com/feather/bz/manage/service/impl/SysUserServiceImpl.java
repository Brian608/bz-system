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
import com.feather.bz.manage.mapper.SysUserMapper;
import com.feather.bz.manage.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Override
    public Boolean registerUser(AddUserBO addUserBO ) {
        ParamCheckUtil.checkObjectNonNull(addUserBO);
        this.checkUserExist(addUserBO);
        SysUser sysUser=new SysUser();
        String salt = RandomUtil.genRandomNumberStr(8);
        sysUser.setSalt(salt);
        BeanUtils.copyProperties(addUserBO,sysUser);
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
}
