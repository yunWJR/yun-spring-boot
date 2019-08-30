package com.yun.util.apilog;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启RequestWrapper和 api data 记录功能
 * @author: yun
 * @createdOn: 2019/8/30 11:00.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({HttpWrapperFilterConfig.class, ApiDataInterceptorConfig.class})
public @interface EnableApiLogInterceptor {

}