package com.github.yunwjr.yun.spring.boot.autoconfigure.swagger;

import com.github.yunwjr.yun.spring.boot.autoconfigure.authorization.AuthPathInterceptor;

import java.util.List;

/**
 * @author yun
 * <p>
 * create_time  2020/8/3 15:17.
 */

public class SwaggerAuthPathInterceptor implements AuthPathInterceptor {
    private final SwaggerProperties swaggerProperties;

    public SwaggerAuthPathInterceptor(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Override
    public List<String> excludePathPatterns() {
        return swaggerProperties.getPathPatterns();
    }
}
