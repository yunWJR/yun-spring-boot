package com.yun.util.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019-04-09 15:51.
 */

@Component
public class SpringEvnImpl implements SpringEvn {

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${yun.evn.pro-name:pro}")
    private String proName;

    private Boolean isPro;

    @Override
    public boolean isProEvn() {
        if (isPro == null) {
            if (StringUtil.hasCtn(proName)) {
                isPro = proName.equals(profile);
            } else {
                isPro = "pro".equals(profile.toLowerCase()) || "prod".equals(profile.toLowerCase());
            }
        }

        return isPro;
    }

    @Override
    public Integer isProValue() {
        return isPro ? 1 : 0;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Boolean getPro() {
        return isPro;
    }

    public void setPro(Boolean pro) {
        isPro = pro;
    }
}
