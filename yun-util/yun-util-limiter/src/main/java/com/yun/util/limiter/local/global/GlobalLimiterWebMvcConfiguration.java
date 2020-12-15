package com.yun.util.limiter.local.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yun
 * <p>
 * create_time  2020/8/3 13:38.
 */

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@AutoConfigureAfter(GlobalLimiterAutoConfiguration.class)
public class GlobalLimiterWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private GlobalLimiterHandlerInterceptor authHandlerInterceptor;

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns(authHandlerInterceptor.allowPathPatterns())
                .excludePathPatterns(authHandlerInterceptor.excludePathPatterns());
    }
}
