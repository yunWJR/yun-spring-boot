package com.yun.util.module.restTemplate;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yun
 * created_time 2019/8/30 14:38.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RestTemplateConfig.class})
public @interface EnableRestTemplate {

}