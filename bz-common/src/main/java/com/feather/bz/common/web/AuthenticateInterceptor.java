package com.feather.bz.common.web;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.feather.bz.common.enums.UserErrorCodeEnum;
import com.feather.bz.common.exception.UserBizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.web
 * @className: AuthenticateInterceptor
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-12-02 14:30
 * @version: 1.0
 */
@Slf4j
public class AuthenticateInterceptor implements HandlerInterceptor {
//    @Autowired
//    private   RedisService redisService;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {
        //采用sa-token
        String authToken = request.getHeader("token");
        if (StringUtils.isBlank(authToken)){
            throw  new UserBizException(UserErrorCodeEnum.TOKEN_EXPIRE_ERROR);
        }
        // 对其他接口进行身份验证
        try {
            StpUtil.checkLogin();
            return true;
        } catch (NotLoginException e) {
            throw  new UserBizException(UserErrorCodeEnum.TOKEN_EXPIRE_ERROR);
            // 这里可以自定义返回错误信息，例如：
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write("{\"code\": 401, \"msg\": \"未登录，请先登录\"}");
        }
        // String token = authToken.substring("Bearer".length() + 1).trim();
        //  UserTokenDTO userTokenDTO = JWTUtil.verifyToken(authToken);
        //1.判断请求是否有效
//        if (redisService.get(RedisConstants.USER+userTokenDTO.getUsername()) == null
//                || !redisService.get(RedisConstants.USER+userTokenDTO.getUsername()).equals(authToken)) {
//            throw  new UserBizException(UserErrorCodeEnum.TOKEN_EXPIRE_ERROR);
//        }
//
//        //2.判断是否需要续期
//        if (redisService.getExpireTime(RedisConstants.USER+userTokenDTO.getUsername()) < RedisConstants.DURATION) {
//            redisService.set(RedisConstants.USER+userTokenDTO.getUsername(), authToken);
//            log.error("update token info, id is:{}, user info is:{}", userTokenDTO.getUsername(), authToken);
//        }
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // log.info("posthaste执行了");
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // log.info("after handle执行了");
    }

}
