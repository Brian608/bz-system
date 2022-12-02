package com.feather.bz.manage.support;

import com.feather.bz.common.domain.dto.UserTokenDTO;
import com.feather.bz.common.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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


}
