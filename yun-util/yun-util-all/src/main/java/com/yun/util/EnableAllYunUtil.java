package com.yun.util;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启所有的 YunUtil 功能，详见 {@link YunUtilAllConfiguration}
 * @author yun
 * created_time 2019/8/30 14:38.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({YunUtilAllConfiguration.class})
public @interface EnableAllYunUtil {

}