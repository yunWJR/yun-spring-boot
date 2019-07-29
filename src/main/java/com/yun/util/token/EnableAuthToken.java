package com.yun.util.token;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启AuthToken验证，需要使用的框架配置后，方可开启
 * @author: yun
 * @createdOn: 2018/6/4 19:44.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({AuthTokenParam.class, AuthTokenParam.class})
public @interface EnableAuthToken {
}
