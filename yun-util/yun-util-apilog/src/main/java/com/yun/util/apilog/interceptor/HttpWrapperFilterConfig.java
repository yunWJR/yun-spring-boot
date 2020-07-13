package com.yun.util.apilog.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author: yun
 * @createdOn: 2019/8/30 09:45.
 */

@Configuration
public class HttpWrapperFilterConfig {
    /**
     * 注册过滤器
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(replaceHttpWrapperFilter());
        registration.addUrlPatterns("/*");
        registration.setName("replaceHttpWrapperFilter");
        return registration;
    }

    /**
     * 实例化StreamFilter
     * @return Filter
     */
    @Bean(name = "replaceHttpWrapperFilter")
    public Filter replaceHttpWrapperFilter() {
        return new ReplaceHttpWrapperFilter();
    }
}
