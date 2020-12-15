package com.yun.util.limiter.local.global;

import com.yun.util.limiter.local.LimiterProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yun
 * <p>
 * create_time  2020/7/29 14:37.
 */

@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@Import({
        GlobalLimiterWebMvcConfiguration.class
})
public class GlobalLimiterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public GlobalLimiterServiceImpl globalLimiterServiceImpl(LimiterProperties authProperties) {
        return new GlobalLimiterServiceImpl(authProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalLimiterHandlerInterceptor globalLimiterHandlerInterceptor(GlobalLimiterServiceImpl globalLimiterService) {
        return new GlobalLimiterHandlerInterceptor(globalLimiterService);
    }
}
