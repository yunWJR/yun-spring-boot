package com.yun.util.limiter.local;

import com.google.common.util.concurrent.RateLimiter;
import com.yun.util.limiter.LimiterException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局限流
 * @author yun
 */
@Service
public class SameQpsLimiter<T> {
    /**
     * 线程安全 map
     */
    private final ConcurrentHashMap<T, RateLimiter> limiterMap = new ConcurrentHashMap<>();

    private double qps = 0;

    /**
     * 是否激活限流器。
     */
    private boolean valid = false;

    private String logType;

    /**
     * @param qps
     */
    public SameQpsLimiter(double qps, String logType) {
        this.qps = qps;
        this.valid = this.qps > 0;
        this.logType = logType;
    }

    /**
     * @param qps
     */
    public SameQpsLimiter(double qps, boolean enable, String logType) {
        this.qps = qps;
        this.valid = enable && this.qps > 0;
        this.logType = logType;
    }

    /**
     * @return
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param key
     */
    public void checkValid(T key) {
        if (!check(key)) {
            throw LimiterException.CommonEp("访问频率过高", String.format("%s(qps=%f):%s", key.toString(), qps, logType));
        }
    }

    /**
     * @param key
     * @return
     */
    public boolean check(T key) {
        // 限流器未激活
        if (!valid) {
            return true;
        }

        RateLimiter limiter = getLimiter(key);

        return limiter.tryAcquire();
    }

    /**
     * @param key
     * @return
     */
    private RateLimiter getLimiter(T key) {
        RateLimiter limiter = limiterMap.get(key);
        if (limiter == null) {
            limiter = RateLimiter.create(qps);
            limiterMap.putIfAbsent(key, limiter);
        }

        return limiter;
    }
}
