package com.yun.util.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019-07-29 13:31.
 */

@Component
@ConfigurationProperties(prefix = "yun.auth")
@Data
public class AuthProperty {
    private String tokenAuthKey = "Authorization";
    private String accessAuthKey = "ACCESS_AUTH";
    private String deviceTypeKey = "DEVICE_TYPE";

    private Boolean throwEp = false;
}