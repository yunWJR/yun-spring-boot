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
public class BaseLimiter<T> {
    /**
     * 线程安全 map
     */
    private final ConcurrentHashMap<T, RateLimiter> limiterMap = new ConcurrentHashMap<>();

    private boolean enable = true;

    public BaseLimiter() {
    }

    public BaseLimiter(boolean enable) {
        this.enable = enable;
    }

    /**
     * @param key
     * @param qps
     * @return
     */
    public void checkValid(T key, double qps) {
        if (!check(key, qps)) {
            throw LimiterException.CommonEp("访问频率过高", String.format("%s(qps=%f)", key.toString(), qps));
        }
    }

    /**
     * @param key
     * @param qps
     * @return
     */
    public boolean check(T key, double qps) {
        // 限流未激活
        if (!enable || qps <= 0) {
            return true;
        }

        RateLimiter limiter = getLimiter(key, qps);

        return limiter.tryAcquire();
    }

    /**
     * @param key
     * @return
     */
    private RateLimiter getLimiter(T key, double qps) {
        RateLimiter limiter = limiterMap.get(key);
        if (limiter == null) {
            limiter = RateLimiter.create(qps);
            limiterMap.putIfAbsent(key, limiter);
        }

        return limiter;
    }
}
