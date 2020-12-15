package com.yun.util.limiter.local.aspect;

/**
 * @author yun
 * <p>
 * create_time  2020/12/15 15:58.
 */

public interface AspectLimiterHandler {
    /**
     * Limiter包含 id 时，获取 id 值
     * @return
     */
    String limiterId();
}
