package com.feather.bz.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @projectName: bz-system
 * @package: com.feather.manage
 * @className: ManageApplication
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-25 22:55
 * @version: 1.0
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.feather.bz.*"})
@MapperScan(basePackages = {" com.feather.bz.manage.mapper"})
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class,args);
    }
}
