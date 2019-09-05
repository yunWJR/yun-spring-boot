package com.yun.util.apilog;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: yun
 * @createdOn: 2019/9/5 16:18.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({ApiDataHeartConfig.class})
public @interface EnableHeartLog {
}
