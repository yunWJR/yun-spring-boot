package com.yun.util.apilog;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yun
 * @createdOn: 2019/9/9 17:21.
 */

@Configuration
public class ApiLogAdapterConfig {
    @Bean
    @ConditionalOnMissingBean
    ApiLogAdapter apiLogAdapterBean() {
        return new ApiLogAdapterImpl();
    }
}
