package com.feather.bz.common.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feather.bz.common.core.DateProvider;
import com.feather.bz.common.core.DateProviderImpl;
import com.feather.bz.common.core.ObjectMapperImpl;
import com.feather.bz.common.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.web
 * @className: WebConfiguration
 * @author: feather
 * @description:
 * @since: 25-Sep-23 4:28 PM
 * @version: 1.0
 */
@Configuration
@Import(value = {GlobalExceptionHandler.class, GlobalResponseBodyAdvice.class})
public class WebConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImpl();
    }

    @Bean
    public DateProvider dateProvider() {
        return new DateProviderImpl();
    }


}