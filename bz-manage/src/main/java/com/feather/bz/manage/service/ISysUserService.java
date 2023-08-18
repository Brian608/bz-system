package com.feather.bz.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.domain.bo.AddUserBO;
import com.feather.bz.manage.domain.dto.LoginDTO;
import com.feather.bz.manage.domain.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
    Boolean registerUser(AddUserBO addUserBO ) throws IOException;

    /**
     * 登录
     * @param loginDTO
     * @return
     */
    Map<String,String> login (LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 用户名
     * @param userName
     * @return
     */
    Boolean logOut(String userName, HttpServletRequest request, HttpServletResponse response);

    List<UserVO> exportUser();

    void encryptPhone();
}
