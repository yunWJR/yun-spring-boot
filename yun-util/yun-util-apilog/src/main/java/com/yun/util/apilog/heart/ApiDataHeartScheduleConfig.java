package com.yun.util.apilog.heart;

import com.yun.util.apilog.ApiLogInterceptorComposite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import static net.logstash.logback.argument.StructuredArguments.value;

/**
 * @author yun
 * created_time 2019/9/5 16:13.
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
     * 读取配置文件，默认每60s，一次心跳日志
     */
    @Scheduled(fixedRateString = "${yun.apilog.heartRate:60000}")
    public void heartLogTask() {
        if (apiLogInterceptor == null || apiLogInterceptor.beforeHeart()) {
            log.info("api data {}", value("api_data_heart", System.currentTimeMillis()));
        }
    }
}