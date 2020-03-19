package com.yun.util.swagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: yun
 * @createdOn: 2019-07-29 15:14.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SwaggerProperty.class, Swagger2Config.class})
public @interface EnableSwagger {
}
