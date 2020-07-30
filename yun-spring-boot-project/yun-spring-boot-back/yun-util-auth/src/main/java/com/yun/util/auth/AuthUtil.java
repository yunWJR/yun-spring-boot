package com.yun.util.auth;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

/**
 * @author yun
 * created_time 2019-07-29 13:30.
 */

@Component
public class AuthUtil {
    public boolean hasNoCheckToken(HandlerMethod handler) {
        if (handler == null) {
            return false;
        }

        Method method = handler.getMethod();

        if (method == null) {
            return false;
        }

        NoNeedAccessAuth rps = AnnotationUtils.findAnnotation(method, NoNeedAccessAuth.class);

        return rps != null;
    }

    public boolean noAuthWhenSystemStop(HandlerMethod handler) {
        return !hasSystemStatus(handler) && !SystemStatusUtil.isServerOn;
    }

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