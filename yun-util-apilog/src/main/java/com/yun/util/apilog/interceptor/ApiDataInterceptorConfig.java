package com.yun.util.apilog.interceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yun
 * @createdOn: 2019/8/30 11:02.
 */

@Configuration
public class ApiDataInterceptorConfig {
    @Bean
    @ConditionalOnMissingBean
    ApiDataInterceptor apiDataInterceptor() {
        return new ApiDataInterceptor();
    }
}
