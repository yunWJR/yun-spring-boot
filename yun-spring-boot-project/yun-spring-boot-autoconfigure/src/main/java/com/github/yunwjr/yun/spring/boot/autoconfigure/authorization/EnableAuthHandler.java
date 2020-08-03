package com.github.yunwjr.yun.spring.boot.autoconfigure.authorization;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启权限处理功能
 * @author yun
 * created_time 2019/11/7 17:31.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({AuthProperties.class, AuthorizationAutoConfiguration.class})
public @interface EnableAuthHandler {
}
