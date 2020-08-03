package com.github.yunwjr.yun.spring.boot.authorization;

/**
 * @author yun
 * created_time 2019-07-29 13:31.
 */

public class AuthSettingBean {
    private String authKey = "Authorization";

    private String deviceTypeKey = "DEVICE_TYPE";

    private String apiKeyKey = "API_KEY";

    private String apiSecretKey = "API_SECRET";

    /**
     * 所有都需要授权
     */
    private boolean allRequiredAuth = true;

    private boolean throwEp = false;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getApiKeyKey() {
        return apiKeyKey;
    }

    public void setApiKeyKey(String apiKeyKey) {
        this.apiKeyKey = apiKeyKey;
    }

    public String getApiSecretKey() {
        return apiSecretKey;
    }

    public void setApiSecretKey(String apiSecretKey) {
        this.apiSecretKey = apiSecretKey;
    }

    public String getDeviceTypeKey() {
        return deviceTypeKey;
    }

    public void setDeviceTypeKey(String deviceTypeKey) {
        this.deviceTypeKey = deviceTypeKey;
    }

    public boolean getThrowEp() {
        return throwEp;
    }

    public void setThrowEp(boolean throwEp) {
        this.throwEp = throwEp;
    }

    public boolean getAllRequiredAuth() {
        return allRequiredAuth;
    }

    public void setAllRequiredAuth(boolean allRequiredAuth) {
        this.allRequiredAuth = allRequiredAuth;
    }
}