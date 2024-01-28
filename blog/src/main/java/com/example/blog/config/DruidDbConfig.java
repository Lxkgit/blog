package com.example.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lxk
 * @date: 2021/7/25 18:18
 * @description: 配置druid
 * @modified By:
 */
@Configuration
public class DruidDbConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDbConfig.class);
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }
}
