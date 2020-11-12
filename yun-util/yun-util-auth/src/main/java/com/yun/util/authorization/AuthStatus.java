package com.yun.util.authorization;

import java.lang.annotation.*;

/**
 * @author yun
 * created_time 2019-07-12 11:28.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthStatus {
    /**
     * 需要权限
     * @return
     */
    boolean required() default true;
}
