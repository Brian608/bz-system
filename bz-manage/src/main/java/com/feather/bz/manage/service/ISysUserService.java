package com.feather.bz.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.domain.bo.AddUserBO;

/**
 * <p>
 * 用户表  服务类
 * </p>
 *
 * @author feather
 * @since 2022-11-30
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 注册用户
     * @param addUserBO
     * @return
     */
    SysUser registerUser(AddUserBO addUserBO );

}
