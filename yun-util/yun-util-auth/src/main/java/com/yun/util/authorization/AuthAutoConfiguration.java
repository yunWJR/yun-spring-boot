package com.yun.util.authorization;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * @author yun
 * <p>
 * create_time  2020/7/29 14:37.
 */

@Configuration
@EnableConfigurationProperties(AuthProperties.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@Import({
        AuthWebMvcConfiguration.class
})
public class AuthAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AuthContextHolder authContextHolder(AuthProperties authProperties) {
        return new AuthContextHolder(authProperties.getSetting());
    }

    @Bean
    @Primary
    public AuthHandlerComposite authHandlerComposite() {
        return new AuthHandlerComposite();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthHandlerUtil authUtil(AuthProperties authProperties, AuthHandlerComposite authHandlerComposite) {
        return new AuthHandlerUtil(authProperties.getSetting().getAllRequiredAuth(), authHandlerComposite);
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthHandlerInterceptor authTokenHandlerInterceptor(
            AuthHandlerUtil authHandlerUtil, AuthProperties authProperties, AuthContextHolder authContextHolder, AuthPathInterceptor[] pathInterceptors) {

        return new AuthHandlerInterceptor(authHandlerUtil, authProperties, authContextHolder, pathInterceptors);
    }
}
