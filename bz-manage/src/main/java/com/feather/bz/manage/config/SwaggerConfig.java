package com.feather.bz.manage.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.config
 * @className: SwaggerConfig
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-26 22:09
 * @version: 1.0
 */
@Slf4j
@Configuration
public class SwaggerConfig {
    @Value("${server.port:}")
    private String port;

    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

//    @Bean
//    public Docket createRestApi() {
//        log.info("接口地址:"+"http://localhost:"+port+"/swagger-ui/");
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public Docket createRestApi() {
        log.info("接口地址:"+"http://localhost:"+port+"/swagger-ui/");
        log.info("接口地址:"+"http://localhost:"+port+"/doc.html");
        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerEnabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("(?!/error.*).*"))
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                // ApiKey的name需与SecurityReference的reference保持一致
                .securitySchemes(Arrays.asList(new ApiKey("token", "token", SecurityScheme.In.HEADER.name())));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("/*.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(
                new SecurityReference("token", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("xxxxxxx")
                .contact(new Contact("feather", "http://xxx.com", "Brian608@163.com"))
                .version("1.0")
                .build();
    }


}
