package com.yun.util.auth;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启权限处理功能
 * @author: yun
 * @createdOn: 2019/11/7 17:31.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({AuthProperty.class, AuthUtil.class, AuthPropertyUtil.class, TokenAuthWebMvcConfigurer.class, CorsFilterConfig.class})
public @interface EnableAuthHandler {
}
