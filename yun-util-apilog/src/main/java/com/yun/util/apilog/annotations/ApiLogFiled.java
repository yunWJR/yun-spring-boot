package com.yun.util.apilog.annotations;

import java.lang.annotation.*;

/**
 * @author: yun
 * @createdOn: 2019/11/12 16:59.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiLogFiled {
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

