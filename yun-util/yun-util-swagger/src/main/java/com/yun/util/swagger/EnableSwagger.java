package com.yun.util.swagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yun
 * created_time 2019-07-29 15:14.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SwaggerAutoConfiguration.class})
public @interface EnableSwagger {
}
