package com.yun.util.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

/**
 * @author yun
 * <p>
 * create_time  2020/7/29 14:37.
 */

@Configuration
@EnableConfigurationProperties(AuthProperties.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@Import({
        AuthorizationWebAutoConfiguration.class
})
public class AuthorizationAutoConfiguration {
    @Autowired
    private AuthProperties authProperties;

    @Bean
    @ConditionalOnMissingBean
    public AuthContextHolder authContextHolder(AuthProperties authProperties) {
        return new AuthContextHolder(authProperties.getSetting());
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthStatusUtil authUtil(AuthProperties authProperties) {
        return new AuthStatusUtil(authProperties.getSetting().getAllRequiredAuth());
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthTokenHandlerInterceptor authTokenHandlerInterceptor(AuthTokenHandler authTokenHandler, AuthStatusUtil authStatusUtil, AuthProperties authProperties, AuthContextHolder authContextHolder) {
        Assert.notNull(authTokenHandler, "AuthTokenHandler must not be null");

        return new AuthTokenHandlerInterceptor(authTokenHandler, authStatusUtil, authProperties.getSetting(), authContextHolder);
    }

}
