package com.yun.util.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yun
 * created_time 2019-07-29 13:31.
 */

@Component
@ConfigurationProperties(prefix = "yun.auth")
public class AuthProperty {
    private String tokenAuthKey = "Authorization";
    private String accessAuthKey = "ACCESS_AUTH";
    private String deviceTypeKey = "DEVICE_TYPE";

    private Boolean throwEp = false;

    public String getTokenAuthKey() {
        return tokenAuthKey;
    }

    public void setTokenAuthKey(String tokenAuthKey) {
        this.tokenAuthKey = tokenAuthKey;
    }

    public String getAccessAuthKey() {
        return accessAuthKey;
    }

    public void setAccessAuthKey(String accessAuthKey) {
        this.accessAuthKey = accessAuthKey;
    }

    public String getDeviceTypeKey() {
        return deviceTypeKey;
    }

    public void setDeviceTypeKey(String deviceTypeKey) {
        this.deviceTypeKey = deviceTypeKey;
    }

    public Boolean getThrowEp() {
        return throwEp;
    }

    public void setThrowEp(Boolean throwEp) {
        this.throwEp = throwEp;
    }
}