package com.yun.util;

import com.yun.util.apilog.advice.EnableApiLogControllerAdvice;
import com.yun.util.apilog.heart.EnableHeartLog;
import com.yun.util.authorization.EnableAuth;
import com.yun.util.common.SpringEvn;
import com.yun.util.common.SpringEvnImpl;
import com.yun.util.limiter.local.EnableLocalLimiter;
import com.yun.util.sb.config.EnableCorsFilter;
import com.yun.util.swagger.EnableSwagger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * YunUtil 功能:
 * 1、权限、跨域配置
 * 2、日志功能
 * 3、swagger 功能
 * 其他、功能bean 注入
 * @author yun
 * created_time 2019-05-30 13:05.
 */

@Configuration

@EnableLocalLimiter

@EnableAuth
@EnableCorsFilter

@EnableApiLogControllerAdvice
@EnableHeartLog

@EnableSwagger

// @EnableRspDataTransfer

public class YunUtilAllConfiguration {

    @ConditionalOnMissingBean(SpringEvn.class)
    @Bean()
    public SpringEvn springEvn() {
        return new SpringEvnImpl();
    }
}
