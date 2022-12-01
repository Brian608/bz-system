package com.feather.bz.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.bz.common.enums.UserErrorCodeEnum;
import com.feather.bz.common.exception.UserBizException;
import com.feather.bz.common.utils.MD5Utils;
import com.feather.bz.common.utils.ParamCheckUtil;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.domain.bo.AddUserBO;
import com.feather.bz.manage.mapper.SysUserMapper;
import com.feather.bz.manage.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author feather
 * @since 2022-11-30
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private  final  SysUserMapper sysUserMapper;

    @Override
    public SysUser registerUser(AddUserBO addUserBO ) {
        ParamCheckUtil.checkObjectNonNull(addUserBO);
        this.checkUserExist(addUserBO);
        if (!addUserBO.getPassword().equals(addUserBO.getConfirmPassWord())){
                throw  new UserBizException(UserErrorCodeEnum.USER_COUPON_IS_NULL);
        }
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(addUserBO,sysUser);
        if (Objects.isNull(addUserBO.getBirthday())){
            sysUser.setBirthday(new Date());
        }
        try {
            sysUser.setPassword(MD5Utils.getMD5Str(addUserBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.save(sysUser);
        return sysUser;
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
}
