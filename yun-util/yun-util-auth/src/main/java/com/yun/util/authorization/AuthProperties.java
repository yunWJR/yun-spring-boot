package com.yun.util.authorization;

import com.yun.util.base.PropertyDefine;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author yun
 * created_time 2019-07-29 13:31.
 */

@Data
@Configuration
@ConfigurationProperties(prefix = AuthProperties.PREFIX)
public class AuthProperties {
    public static final String PREFIX = PropertyDefine.BASE_PREFIX + ".authorization";

    @NestedConfigurationProperty
    private AuthSettingBean setting = new AuthSettingBean();

    private List<String> excludePathPatterns;

    private List<String> allowPathPatterns;
}