package com.yun.util.limiter.local;

import com.yun.util.limiter.local.global.GlobalLimiterAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启权限处理功能
 * @author yun
 * created_time 2019/11/7 17:31.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({GlobalLimiterAutoConfiguration.class})
@EnableConfigurationProperties(LimiterProperties.class)
public @interface EnableLocalLimiter {
}
