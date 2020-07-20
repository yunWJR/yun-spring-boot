package com.yun.util.apilog.interceptor;

import com.yun.util.apilog.ApiLogProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启RequestWrapper和 api data 记录功能
 * @author yun
 * created_time 2019/8/30 11:00.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({HttpWrapperFilterConfig.class, ApiDataInterceptorConfig.class, ApiLogProperties.class})
public @interface EnableApiLogInterceptor {

}