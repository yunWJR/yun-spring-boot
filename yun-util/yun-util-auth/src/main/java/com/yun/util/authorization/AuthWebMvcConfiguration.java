package com.yun.util.authorization;

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
@AutoConfigureAfter(AuthAutoConfiguration.class)
public class AuthWebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private AuthHandlerInterceptor authHandlerInterceptor;

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns(authHandlerInterceptor.allowPathPatterns())
                .excludePathPatterns(authHandlerInterceptor.excludePathPatterns())
                .order(authHandlerInterceptor.order());
    }
}
