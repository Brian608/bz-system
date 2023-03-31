package com.feather.bz.manage.support;

import cn.dev33.satoken.stp.StpUtil;
import com.feather.bz.common.domain.dto.UserTokenDTO;
import com.feather.bz.common.enums.UserErrorCodeEnum;
import com.feather.bz.common.exception.UserBizException;
import com.feather.bz.common.utils.HttpContextUtils;
import com.feather.bz.common.utils.JWTUtil;
import com.feather.bz.manage.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.support
 * @className: UserSupport
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-12-02 22:16
 * @version: 1.0
 */
@Slf4j
@Component
public class UserSupport {



   public UserTokenDTO getCurrentUserInfo(){
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        return JWTUtil.verifyToken(token);
    }

    public SysUser getCurrentUser(){
        if (StpUtil.isLogin()){
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            HttpSession session = request.getSession();
            Object user = session.getAttribute("User");
            if (Objects.isNull(user)){
                throw  new UserBizException(UserErrorCodeEnum.TOKEN_EXPIRE_ERROR);
            }
            return (SysUser) user;
        }
        return  null;
    }



}
