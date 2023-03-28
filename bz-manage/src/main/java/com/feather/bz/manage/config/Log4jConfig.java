package com.feather.bz.manage.config;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.config
 * @className: Log4jConfig
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-03-28 11:11
 * @version: 1.0
 */

@Configuration
public class Log4jConfig {
    @Value("${logging.file-path}")
    private String logFilePath;

    @Bean
    public void configureLog4j() {
        Properties log4jProperties = new Properties();
        try (InputStream inputStream = new ClassPathResource("log4j.properties").getInputStream()) {
            log4jProperties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load log4j.properties", e);
        }

        log4jProperties.setProperty("log4j.appender.file.File", logFilePath);
        PropertyConfigurator.configure(log4jProperties);
    }
}

