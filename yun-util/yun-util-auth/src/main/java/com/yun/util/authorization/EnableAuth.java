package com.yun.util.authorization;

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
@Import({AuthAutoConfiguration.class})
public @interface EnableAuth {
}
