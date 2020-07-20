package com.yun.util.idgenerator;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yun
 * created_time 2018/7/25 14:23.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SnowflakeIdWorkerAutoConfigure.class, SnowflakeIdUtil.class})
public @interface EnableSnowflakeId {
}
