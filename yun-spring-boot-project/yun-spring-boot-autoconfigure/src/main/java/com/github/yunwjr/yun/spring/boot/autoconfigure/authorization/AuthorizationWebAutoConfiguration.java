package com.github.yunwjr.yun.spring.boot.autoconfigure.authorization;

import com.github.yunwjr.yun.spring.boot.authorization.AuthTokenHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author yun
 * <p>
 * create_time  2020/8/3 13:38.
 */

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@AutoConfigureAfter(AuthorizationAutoConfiguration.class)
public class AuthorizationWebAutoConfiguration implements WebMvcConfigurer {
    @Autowired
    private AuthTokenHandlerInterceptor authTokenHandlerInterceptor;

    @Autowired
    private AuthProperties authProperties;

    @Autowired(required = false)
    private AuthPathInterceptor[] pathInterceptors;

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> allowPath = allowPathPatterns();
        List<String> excludePath = excludePathPatterns();

        registry.addInterceptor(authTokenHandlerInterceptor)
                .addPathPatterns(allowPath)
                .excludePathPatterns(excludePath);
    }

    /**
     * @return
     */
    private List<String> allowPathPatterns() {
        HashSet<String> allows = new HashSet<>();
        if (authProperties.getAllowPathPatterns() != null) {
            allows.addAll(authProperties.getAllowPathPatterns());
        }

        if (pathInterceptors != null) {
            for (AuthPathInterceptor pathInterceptor : pathInterceptors) {
                List<String> allowsI = pathInterceptor.allowPathPatterns();
                if (allowsI != null) {
                    allows.addAll(allowsI);
                }
            }
        }

        // 没有则默认所有
        if (allows.size() == 0) {
            return Collections.singletonList("/**");
        }

        return new ArrayList<String>(allows);
    }

    /**
     * @return
     */
    private List<String> excludePathPatterns() {
        HashSet<String> excludes = new HashSet<>();
        if (authProperties.getExcludePathPatterns() != null) {
            excludes.addAll(authProperties.getExcludePathPatterns());
        }

        if (pathInterceptors != null) {
            for (AuthPathInterceptor pathInterceptor : pathInterceptors) {
                List<String> excludesI = pathInterceptor.excludePathPatterns();
                if (excludesI != null) {
                    excludes.addAll(excludesI);
                }
            }
        }

        return new ArrayList<String>(excludes);
    }
}
