package com.yun.util.swagger;

import com.yun.util.base.PropertyDefine;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yun
 * created_time 2019-07-29 13:31.
 */

@Data
@Configuration
@ConfigurationProperties(prefix = SwaggerProperties.PREFIX)
public class SwaggerProperties {
    public static final String PREFIX = PropertyDefine.BASE_PREFIX + ".swagger";

    private boolean enable = true;

    private String basePackage;

    private String title = "api文档";
    private String description = "-";
    private String version = "-";

    private boolean enableOnPro = false;

    private List<Para> para = new ArrayList<>();

    // "/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html"
    private List<String> pathPatterns = Arrays.asList("/swagger-resources/**", "/swagger-ui.html/**", "/doc.html/**", "/v2/api-docs/**");

    @Data
    public static class Para {
        private String name;
        private String description;
        private boolean required = false;
        private String paramType;
        private String modelRef;
    }
}