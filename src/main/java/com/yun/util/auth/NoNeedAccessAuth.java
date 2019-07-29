package com.yun.util.auth;

import java.lang.annotation.*;

/**
 * @author: yun
 * @createdOn: 2019-07-12 11:28.
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// @Documented
public @interface NoNeedAccessAuth {

}
