package com.feather.bz.common.web;

import com.feather.bz.common.constants.CoreConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.web
 * @className: InterceptorConfig
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-12-02 14:26
 * @version: 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        String[] allow = {"/api/v1/sys-user/login","/api/v1/file-preview/preview/","/swagger-ui/","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"};
//        registry.addInterceptor( authorityInterceptor())
//                .addPathPatterns("/"+ CoreConstant.API+"/"+CoreConstant.V1+"/**")
//                .excludePathPatterns(allow);
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//
//
//    @Bean
//    public AuthenticateInterceptor authorityInterceptor() {
//        return new AuthenticateInterceptor();
//    }


}