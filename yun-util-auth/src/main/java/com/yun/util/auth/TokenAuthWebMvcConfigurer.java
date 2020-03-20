package com.yun.util.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author: yun
 * @createdOn: 2019/11/7 17:22.
 */

@Configuration
public class TokenAuthWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenAuthHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**");
    }

    // 跨域访问
    // 使用此方法配置之后再使用自定义拦截器时跨域相关配置就会失效
    // 原因是请求经过的先后顺序问题，当请求到来时会先进入拦截器中，而不是进入Mapping映射中，所以返回的头信息中并没有配置的跨域信息。浏览器就会报跨域异常。
    // 使用CorsFilter拦截器
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("*")
    //             .exposedHeaders("Set-Cookie")
    //             .allowedHeaders("*")
    //             .allowCredentials(false)
    //             .allowedMethods("*")
    //             .maxAge(3600);
    // }

    /**
     * 格式化
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).
                setUseTrailingSlashMatch(true);
    }

    /**
     * 添加静态资源--过滤swagger-api (开源的在线API文档)
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //过滤swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v1/api/**")
                .addResourceLocations("classpath:/META-INF/resources/v1/api/");
    }

    /**
     * URL到视图的映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/index.html").setViewName("/index.btl");
        // registry.addRedirectViewController("/**/*.do", "/index.html");
    }
}
