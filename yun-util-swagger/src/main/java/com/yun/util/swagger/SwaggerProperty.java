package com.yun.util.swagger;

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
public class SwaggerProperty {
    private String basePackage;

    private String title = "api文档";
    private String description = "-";
    private String version = "-";

    private boolean enableOnPro = false;

    private List<Para> para = new ArrayList<>();

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isEnableOnPro() {
        return enableOnPro;
    }

    public void setEnableOnPro(boolean enableOnPro) {
        this.enableOnPro = enableOnPro;
    }

    public List<Para> getPara() {
        return para;
    }

    public void setPara(List<Para> para) {
        this.para = para;
    }

    public static class Para {
        private String name;
        private String description;
        private boolean required = false;
        private String paramType;
        private String modelRef;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public String getParamType() {
            return paramType;
        }

        public void setParamType(String paramType) {
            this.paramType = paramType;
        }

        public String getModelRef() {
            return modelRef;
        }

        public void setModelRef(String modelRef) {
            this.modelRef = modelRef;
        }
    }
}