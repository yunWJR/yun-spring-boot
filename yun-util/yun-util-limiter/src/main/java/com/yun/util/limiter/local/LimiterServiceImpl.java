package com.yun.util.limiter.local;

import com.google.common.util.concurrent.RateLimiter;
import com.yun.util.common.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author yun
 */
@Service
public class LimiterServiceImpl {
    /**
     *
     */
    private final ConcurrentHashMap<Long, RateLimiter> userLimiter = new ConcurrentHashMap<>();
    /**
     *
     */
    private final ConcurrentHashMap<String, RateLimiter> ipLimiter = new ConcurrentHashMap<>();

    @Autowired
    private LimiterProperties limiterProperties;

    /**
     * @param userId
     */
    public void checkUserLimiter(Long userId) {
        if (!limiterProperties.isEnable() || limiterProperties.getUserQps() == 0) {
            return;
        }

        RateLimiter limiter = getUserLimiter(userId, limiterProperties.getUserQps());

        if (!limiter.tryAcquire()) {
            throw new CommonException(11001, "服务器繁忙，请稍微重试");
        }
    }

    /**
     * @param ip
     */
    public void checkIpLimiter(String ip) {
        if (!limiterProperties.isEnable() || limiterProperties.getIpQps() == 0) {
            return;
        }

        RateLimiter limiter = getIpLimiter(ip, limiterProperties.getIpQps());

        if (!limiter.tryAcquire()) {
            throw new CommonException(11001, "服务器繁忙，请稍微重试");
        }
    }

    /**
     * @param userId
     * @param qps
     * @return
     */
    private RateLimiter getUserLimiter(Long userId, double qps) {
        RateLimiter limiter = userLimiter.get(userId);
        if (limiter == null) {
            limiter = RateLimiter.create(qps);
            userLimiter.putIfAbsent(userId, limiter);
        }

        return limiter;
    }

    /**
     * @param key
     * @param qps
     * @return
     */
    private RateLimiter getIpLimiter(String key, double qps) {
        RateLimiter limiter = ipLimiter.get(key);
        if (limiter == null) {
            limiter = RateLimiter.create(qps);
            ipLimiter.putIfAbsent(key, limiter);
        }

        return limiter;
    }
}
