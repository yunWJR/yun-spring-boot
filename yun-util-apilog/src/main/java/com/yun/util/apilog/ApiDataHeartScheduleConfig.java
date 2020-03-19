package com.yun.util.apilog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ApiDataHeartScheduleConfig {
    private final Logger log = LoggerFactory.getLogger(ApiDataHeartScheduleConfig.class);

    private final ApiLogInterceptorComposite apiLogInterceptor;

    @Autowired(required = false)
    public ApiDataHeartScheduleConfig(ApiLogInterceptorComposite apiLogInterceptor) {
        this.apiLogInterceptor = apiLogInterceptor;
    }

    /**
     * 每60s，一次心跳日志
     */
    @Scheduled(fixedRate = 60000)
    public void heartLogTask() {
        if (apiLogInterceptor == null || apiLogInterceptor.beforeHeart()) {
            log.info("api data {}", value("api_data_heart", System.currentTimeMillis()));
        }
    }
}