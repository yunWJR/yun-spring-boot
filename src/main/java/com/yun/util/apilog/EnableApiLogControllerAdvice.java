package com.yun.util.apilog;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: yun
 * @createdOn: 2019/8/30 14:38.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({ApiDataControllerBodyAdvice.class, ApiDataRequestBodyAdvice.class,
        ApiDataResponseBodyAdvice.class, ApiLogProperty.class, ApiLogAdapterConfig.class})
public @interface EnableApiLogControllerAdvice {

}