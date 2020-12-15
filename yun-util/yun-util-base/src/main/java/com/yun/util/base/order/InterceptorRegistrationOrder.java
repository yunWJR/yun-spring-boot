package com.yun.util.base.order;

/**
 * InterceptorRegistration order 定义
 * @author yun
 * <p>
 * create_time  2020/12/15 10:52.
 */

public class InterceptorRegistrationOrder {
    /**
     * {@link com.yun.util.limiter.local.global.GlobalLimiterHandlerInterceptor}
     */
    public static int GlobalLimiterOrder = 1000;

    /**
     * {@link com.yun.util.authorization.AuthHandlerInterceptor}
     */
    public static int AuthHandlerOrder = 1001;
}
