package com.yun.util.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yun
 * <p>
 * create_time  2020/12/14 15:22.
 */

@Component
@Primary
public class AuthHandlerComposite implements AuthHandler {
    /**
     *
     */
    private List<AuthHandler> list = new ArrayList<>();

    /**
     * @param items
     */
    @Autowired(required = false)
    public void setConfigurers(List<AuthHandler> items) {
        this.list = items;

        if (this.list == null) {
            this.list = new ArrayList<>();
            return;
        }

        this.resortList();
    }

    /**
     * 添加 handle
     * @param handler
     */
    public void addHandler(AuthHandler handler) {
        if (handler == null) {
            return;
        }

        if (this.list == null) {
            this.list = new ArrayList<>();
        }

        this.list.add(handler);

        this.resortList();
    }

    /**
     * 排序
     */
    private void resortList() {
        this.list = list.stream().sorted(Comparator.comparing(AuthHandler::getOrder)).collect(Collectors.toList());
    }

    @Override
    public void handleNotRequiredAuth(HttpServletRequest request, HttpServletResponse response, Object handler) {
        for (AuthHandler authHandler : list) {
            authHandler.handleNotRequiredAuth(request, response, handler);
        }
    }

    @Override
    public Object checkUser(String authStr, HttpServletRequest request) {
        Object user;
        for (AuthHandler authHandler : list) {
            user = authHandler.checkUser(authStr, request);
            if (user != null) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void savePara(HttpServletRequest request, String type) {
        for (AuthHandler authHandler : list) {
            authHandler.savePara(request, type);
        }
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean handleNoAuthResponse(HttpServletRequest request, HttpServletResponse response, Object handler) {
        for (AuthHandler authHandler : list) {
            if (authHandler.handleNoAuthResponse(request, response, handler)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param user
     * @param request
     * @param response
     * @param handler
     */
    @Override
    public void handleBeforeSaveUser(Object user, HttpServletRequest request, HttpServletResponse response, Object handler) {
        for (AuthHandler authHandler : list) {
            authHandler.handleBeforeSaveUser(user, request, response, handler);
        }
    }

    /**
     * @param request
     * @return
     */
    @Override
    public boolean notRequiredAuthRequest(HttpServletRequest request) {
        for (AuthHandler authHandler : list) {
            if (authHandler.notRequiredAuthRequest(request)) {
                return true;
            }
        }

        return false;
    }
}
