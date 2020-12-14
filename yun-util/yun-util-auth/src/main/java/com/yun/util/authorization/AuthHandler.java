package com.yun.util.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权 handler
 * @author yun
 * created_time 2019/11/7 17:22.
 */

public interface AuthHandler {
    /**
     * 排序
     * @return
     */
    default int getOrder() {
        return 0;
    }

    /**
     * 无需权限的时候，业务处理。
     * @param request
     * @param response
     * @param handler
     */
    default void handleNotRequiredAuth(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 可以做无权限限流校验（IP 限流）
    }

    /**
     * 检查 token 是否有效
     * @param authStr
     * @param request
     * @return
     */
    Object checkUser(String authStr, HttpServletRequest request);

    /**
     * 保存额外的请求参数
     * @param request
     * @param type
     */
    default void savePara(HttpServletRequest request, String type) {
    }

    /**
     * 处理请求逻辑
     * @param request
     * @param response
     * @param handler
     * @return
     */
    default boolean handleNoAuthResponse(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return false;
    }

    /**
     * 保存用户信息前，业务处理
     * @param user
     * @param request
     * @param response
     * @param handler
     */
    default void handleBeforeSaveUser(Object user, HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 针对 user 的全局限流
    }

    /**
     * @param request
     * @return
     */
    default boolean notRequiredAuthRequest(HttpServletRequest request) {
        return false;
    }
}
