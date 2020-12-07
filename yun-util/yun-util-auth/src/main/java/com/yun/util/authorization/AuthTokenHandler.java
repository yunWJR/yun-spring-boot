package com.yun.util.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yun
 * created_time 2019/11/7 17:22.
 */

public interface AuthTokenHandler {
    /**
     * 自定义权限检查（ request）
     * @param request
     * @return
     */
    default boolean isNotRequiredAuth(HttpServletRequest request) {
        // 可以对 URL 进行匹配
        return false;
    }

    /**
     * 无需权限的时候，业务处理
     * @param request
     * @return
     */
    default boolean handleNoAuth(HttpServletRequest request) {
        // 可以做无权限限流校验（IP 限流）
        return true;
    }

    /**
     * 检查 token 是否有效
     * @param tokenStr
     * @param request
     * @return
     */
    Object checkUser(String tokenStr, HttpServletRequest request);

    /**
     * 保存额外的请求参数
     * @param request
     */
    default void savePara(HttpServletRequest request) {
    }

    /**
     * 自定义处理未授权返回
     * @return
     */
    default boolean canHandleNoAuthResponse() {
        return false;
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
     * @param request
     * @param user
     */
    default void handleBeforeSaveUser(HttpServletRequest request, Object user) {
        // 针对 user 的全局限流
    }
}
