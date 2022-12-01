package com.feather.bz.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.bz.common.enums.UserErrorCodeEnum;
import com.feather.bz.common.exception.UserBizException;
import com.feather.bz.common.utils.MD5Utils;
import com.feather.bz.common.utils.ParamCheckUtil;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.domain.bo.AddUserBO;
import com.feather.bz.manage.mapper.SysUserMapper;
import com.feather.bz.manage.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author feather
 * @since 2022-11-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser registerUser(AddUserBO addUserBO ) {
        ParamCheckUtil.checkObjectNonNull(addUserBO);
        if (!addUserBO.getPassword().equals(addUserBO.getConfirmPassWord())){
                throw  new UserBizException(UserErrorCodeEnum.USER_COUPON_IS_NULL);
        }
        SysUser sysUser=new SysUser();
//        BeanUtils.copyProperties(addUserBO,sysUser);
//        if (Objects.isNull(addUserBO.getBirthday())){
//            sysUser.setBirthday(new Date());
//        }
//        try {
//            sysUser.setPassword(MD5Utils.getMD5Str(addUserBO.getPassword()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        this.save(sysUser);
        return sysUser;

    }
}
