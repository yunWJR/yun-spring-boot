package com.yun.util.limiter.local.global;

import com.yun.util.limiter.local.BaseLimiter;
import com.yun.util.limiter.local.LimiterProperties;
import com.yun.util.limiter.local.SameQpsLimiter;
import org.springframework.util.StringUtils;

/**
 * 全局限流
 * @author yun
 */
public class GlobalLimiterServiceImpl {
    /**
     *
     */
    private final SameQpsLimiter<String> ipLimiter;

    /**
     *
     */
    private final BaseLimiter<String> commonLimiter;

    /**
     *
     */
    private final LimiterProperties limiterProperties;

    /**
     * @param limiterProperties
     */
    public GlobalLimiterServiceImpl(LimiterProperties limiterProperties) {
        this.limiterProperties = limiterProperties;

        ipLimiter = new SameQpsLimiter<>(limiterProperties.getIpQps(), limiterProperties.isEnable(), "IP限流");

        commonLimiter = new BaseLimiter<>(limiterProperties.isEnable());
    }

    /**
     * @param qps
     * @return
     */
    private boolean isLimiterOn(double qps) {
        return limiterProperties.isEnable() && qps > 0;
    }

    /**
     *
     */
    public void checkAllRequest() {
        commonLimiter.checkValid("allRequest", limiterProperties.getAllRequestQps());
    }

    /**
     * @return
     */
    public boolean ipQpsOn() {
        return isLimiterOn(limiterProperties.getIpQps());
    }

    /**
     * @param ip
     */
    public void checkIp(String ip) {
        // 无法获取ip。
        // 正常情况都有 ip，无法获取 ip，大多数为伪造请求，都归为 unknown统一限制。
        if (StringUtils.isEmpty(ip)) {
            ip = "ip-unknown";
        }

        ipLimiter.checkValid(ip);
    }
}
