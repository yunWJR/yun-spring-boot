package com.yun.util.auth;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启权限处理工具
 * @author yun
 * created_time 2019-07-29 15:14.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({AuthProperty.class, AuthUtil.class, AuthPropertyUtil.class})
public @interface EnableAuthUtil {
}
