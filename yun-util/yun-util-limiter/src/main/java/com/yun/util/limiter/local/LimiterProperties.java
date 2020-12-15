package com.yun.util.limiter.local;

import com.yun.util.base.PropertyDefine;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yun
 * created_time 2019-07-29 13:31.
 */

@Data
@Configuration
@ConfigurationProperties(prefix = LimiterProperties.PREFIX)
public class LimiterProperties {
    /**
     *
     */
    public static final String PREFIX = PropertyDefine.BASE_PREFIX + ".limiter";

    /**
     *
     */
    private boolean enable = true;

    /**
     * 所有请求 qps。0为无限制
     */
    private double allRequestQps = 0;

    /**
     * 用户 qps。0为无限制
     */
    private double userQps = 5;

    /**
     * ip qps。0为无限制
     */
    private double ipQps = 50;
}