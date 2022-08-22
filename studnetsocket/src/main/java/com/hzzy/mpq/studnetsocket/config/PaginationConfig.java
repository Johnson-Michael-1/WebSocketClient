package com.hzzy.mpq.studnetsocket.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页配置
 * @author Mei
 * @version V1.0
 * @date
 **/
@Configuration
public class PaginationConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
