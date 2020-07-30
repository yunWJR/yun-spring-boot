package com.yun.util.apilog.annotations;

import java.lang.annotation.*;

/**
 * 注解，可覆盖ApiLogProperties设定的值
 * @author yun
 * created_time 2019/11/12 16:59.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiLogFiled {
    /**
     * 忽略记录
     * @return
     */
    boolean ignoreLog() default false;

    /**
     * 是否记录 header
     * @return
     */
    boolean header() default true;

    /**
     * 是否记录 requestBody
     * @return
     */
    boolean requestBody() default true;

    /**
     * 是否记录 responseBody
     * @return
     */
    boolean responseBody() default true;
}

