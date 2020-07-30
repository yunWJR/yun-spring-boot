package com.yun.util.apilog.heart;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yun
 * created_time 2019/9/5 16:18.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({ApiDataHeartScheduleConfig.class})
public @interface EnableHeartLog {
}
