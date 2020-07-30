package com.yun.util.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * AuthProperty 工具类
 * @author yun
 * created_time 2019-07-29 14:59.
 */

@Component
public class AuthPropertyUtil {
    private static AuthPropertyUtil instance;

    @Autowired
    private AuthProperty obj;

    public AuthPropertyUtil() {
    }

    public static AuthProperty prop() {
        return instance.getObj();
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    private AuthProperty getObj() {
        return obj;
    }

    public void setObj(AuthProperty obj) {
        this.obj = obj;
    }
}
