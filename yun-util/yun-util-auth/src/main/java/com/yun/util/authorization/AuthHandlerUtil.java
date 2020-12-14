package com.yun.util.authorization;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yun
 * created_time 2019-07-29 13:30.
 */

public class AuthHandlerUtil {
    /**
     *
     */
    private final boolean allRequiredAuth;

    /**
     *
     */
    private final AuthHandlerComposite authHandlerComposite;

    /**
     * @param allRequiredAuth
     * @param authHandlerComposite
     */
    public AuthHandlerUtil(boolean allRequiredAuth, AuthHandlerComposite authHandlerComposite) {
        this.allRequiredAuth = allRequiredAuth;
        this.authHandlerComposite = authHandlerComposite;
    }

    /**
     * 服务器为停止状态时，是否无权限访问
     * @param handler
     * @return
     */
    public boolean noAuthWhenSystemStop(HandlerMethod handler) {
        return !SystemStatusUtil.isServerOn && !hasSystemStatus(handler);
    }

    /**
     * 是否有设置系统状态参数的权限
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

    /**
     * 是否不需要权限的
     * @param handler
     * @return
     */
    public boolean notRequiredAuth(HandlerMethod handler, HttpServletRequest request) {
        return notRequiredAuthAnnotation(handler) || notRequiredAuthRequest(request);
    }

    /**
     * 是否有不需要权限的注解
     * @param handler
     * @return
     */
    public boolean notRequiredAuthAnnotation(HandlerMethod handler) {
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
     * 请求是否不需要权限：默认需要
     * @param request
     * @return
     */
    public boolean notRequiredAuthRequest(HttpServletRequest request) {
        if (authHandlerComposite.notRequiredAuthRequest(request)) {
            return true;
        }

        return false;
    }

    /**
     * 不需要权限的业务处理
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public void handleNotRequiredAuth(HttpServletRequest request, HttpServletResponse response, Object handler) {
        authHandlerComposite.handleNotRequiredAuth(request, response, handler);
    }

    /**
     * @param request
     * @param response
     * @param handler
     */
    public void handleOptionsRequest(HttpServletRequest request, HttpServletResponse response, Object handler) {
        authHandlerComposite.savePara(request, "handleOptionsRequest");
    }

    /**
     * @param request
     * @param response
     * @param handler
     */
    public void handleNotHandlerMethod(HttpServletRequest request, HttpServletResponse response, Object handler) {
        authHandlerComposite.savePara(request, "handleNotHandlerMethod");
    }

    /**
     * @param request
     * @param response
     * @param handler
     */
    public void handleNoAuthWhenSystemStop(HttpServletRequest request, HttpServletResponse response, Object handler) {
        authHandlerComposite.savePara(request, "handleNoAuthWhenSystemStop");
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     */
    public boolean handleNoAuthResponse(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return authHandlerComposite.handleNoAuthResponse(request, response, handler);
    }

    /**
     * @param authStr
     * @param request
     * @return
     */
    public Object checkUser(String authStr, HttpServletRequest request) {
        return authHandlerComposite.checkUser(authStr, request);
    }

    /**
     * @param user
     * @param request
     * @param response
     * @param handler
     */
    public void handleBeforeSaveUser(Object user, HttpServletRequest request, HttpServletResponse response, Object handler) {
        authHandlerComposite.savePara(request, "handleBeforeSaveUser");

        authHandlerComposite.handleBeforeSaveUser(user, request, response, handler);
    }
}
