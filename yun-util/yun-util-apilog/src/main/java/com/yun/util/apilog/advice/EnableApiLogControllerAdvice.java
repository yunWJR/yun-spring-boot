package com.yun.util.apilog.advice;

import com.yun.util.apilog.ApiLogInterceptorComposite;
import com.yun.util.apilog.ApiLogProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yun
 * created_time 2019/8/30 14:38.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({ApiDataControllerBodyAdvice.class, ApiDataRequestBodyAdvice.class,
        ApiDataResponseBodyAdvice.class, ApiLogProperties.class, ApiLogInterceptorComposite.class})
public @interface EnableApiLogControllerAdvice {

}