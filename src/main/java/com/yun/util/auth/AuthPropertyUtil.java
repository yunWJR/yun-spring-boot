package com.yun.util.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: yun
 * @createdOn: 2019-07-29 14:59.
 */

@Component
public class AuthPropertyUtil {
    public static AuthPropertyUtil instance;

    @Autowired
    private AuthProperty obj;

    public AuthPropertyUtil() {
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    public AuthProperty getObj() {
        return obj;
    }

    public void setObj(AuthProperty obj) {
        this.obj = obj;
    }
}
