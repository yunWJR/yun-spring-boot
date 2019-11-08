package com.yun.util.module.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-29 13:31.
 */

@Component
@ConfigurationProperties(prefix = "yun.swagger")
@Data
public class SwaggerProperty {
    private String basePackage;

    private String title = "api文档";
    private String description = "-";
    private String version = "-";

    private boolean enableOnPro = false;

    private List<Para> para = new ArrayList<>();

    @Data
    public static class Para {
        private String name;
        private String description;
        private boolean required = false;
        private String paramType;
        private String modelRef;
    }
}