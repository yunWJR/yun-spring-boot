package com.yun.util.apilog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static net.logstash.logback.argument.StructuredArguments.value;

/**
 * @author: yun
 * @createdOn: 2019/9/5 16:13.
 */

@Configuration
@EnableScheduling
@Slf4j
public class ApiDataHeartConfig {
    @Autowired
    private ApiLogInterceptor apiLogInterceptor;

    /**
     * 每60s，一次心跳日志
     */
    @Scheduled(fixedRate = 60000)
    public void heartLogTask() {
        if (apiLogInterceptor != null && apiLogInterceptor.beforeHeart()) {
            log.info("api data {}", value("api_data_heart", System.currentTimeMillis()));
        }
    }
}