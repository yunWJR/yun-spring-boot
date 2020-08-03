package com.github.yunwjr.yun.spring.boot.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yun
 * created_time 2019-07-12 17:13.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// @Documented
public @interface SystemStatusAuth {
}