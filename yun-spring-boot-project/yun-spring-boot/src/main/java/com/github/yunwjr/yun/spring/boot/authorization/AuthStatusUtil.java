package com.github.yunwjr.yun.spring.boot.authorization;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

/**
 * @author yun
 * created_time 2019-07-29 13:30.
 */

public class AuthStatusUtil {
    /**
     *
     */
    private final boolean allRequiredAuth;

    public AuthStatusUtil(boolean allRequiredAuth) {
        this.allRequiredAuth = allRequiredAuth;
    }

    /**
     * @param handler
     * @return
     */
    public boolean notRequiredAuth(HandlerMethod handler) {
        if (handler == null) {
            return false;
        }

        Method method = handler.getMethod();

        if (method == null) {
            return false;
        }

        AuthStatus noNeed = AnnotationUtils.findAnnotation(method, AuthStatus.class);
        if (noNeed != null) {
            // 以注解为准
            return !noNeed.required();
        } else {
            // 配置状态
            return !allRequiredAuth;
        }
    }

    /**
     * @param handler
     * @return
     */
    public boolean noAuthWhenSystemStop(HandlerMethod handler) {
        return !hasSystemStatus(handler) && !SystemStatusUtil.isServerOn;
    }

    /**
     * @param handler
     * @return
     */
    public boolean hasSystemStatus(HandlerMethod handler) {
        if (handler == null) {
            return false;
        }

        Method method = handler.getMethod();

        if (method == null) {
            return false;
        }

        SystemStatusAuth rps = AnnotationUtils.findAnnotation(method, SystemStatusAuth.class);

        return rps != null;
    }
}