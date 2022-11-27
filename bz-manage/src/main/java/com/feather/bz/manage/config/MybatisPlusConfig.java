package com.feather.bz.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.config
 * @className: MybatisPlusConfig
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-27 20:30
 * @version: 1.0
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 插件配置
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}
