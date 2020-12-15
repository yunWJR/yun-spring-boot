package com.yun.util.limiter.local.aspect;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yun
 * <p>
 * create_time  2020/7/29 14:37.
 */

@Configuration
@Import({
        LimiterHandler.class
})
public class AspectLimiterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public MethodParamExpressionParser methodParamExpressionParser() {
        return new MethodParamExpressionParser();
    }

    @Bean
    @ConditionalOnMissingBean
    public LimiterHandler limiterHandler(MethodParamExpressionParser methodParamExpressionParser) {
        return new LimiterHandler(methodParamExpressionParser);
    }
}
