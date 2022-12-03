package com.feather.bz.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.domain.bo.AddUserBO;
import com.feather.bz.manage.domain.dto.LoginDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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

    /**
     * 登录
     * @param loginDTO
     * @return
     */
    String login (LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户名
     * @param userName
     * @return
     */
    Boolean logOut(String userName);

}
