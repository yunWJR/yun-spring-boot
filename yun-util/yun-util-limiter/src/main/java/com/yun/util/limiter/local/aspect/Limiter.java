package com.yun.util.limiter.local.aspect;

import java.lang.annotation.*;

/**
 * @author yun
 * <p>
 * create_time  2020/12/15 14:03.
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface Limiter {

    /**
     * 锁名称：默认方法名称
     * @return
     */
    String name() default "";

    /**
     * 锁名称参数:
     * 支持 SpEL 表达式。（如：#id 取 参数名 id 的值）
     * @return
     */
    String[] keys() default {};

    /**
     * qps：默认0-无限制
     * @return
     */
    double qps();

    /**
     * 超时（s）:默认0-无等待
     * @return
     */
    long timeout() default 0;

    /**
     * name加入id
     * 需要实现{@link AspectLimiterHandler#limiterId()}方法
     * @return
     */
    boolean id() default false;
}
