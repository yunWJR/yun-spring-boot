package com.yun.util.apilog.interceptor;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 只开启RequestWrapper
 * @author yun
 * created_time 2019/8/30 10:57.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({HttpWrapperFilterConfig.class})
public @interface EnableRequestWrapper {
}
