package com.start.mb.boot.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wll
 * @version version
 * @title MyBatisPlusConfig
 * @desc
 * @date 2020/3/26
 */
@Configuration
public class MyBatisPlusConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 配置分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return  new PaginationInterceptor();
    }
}
