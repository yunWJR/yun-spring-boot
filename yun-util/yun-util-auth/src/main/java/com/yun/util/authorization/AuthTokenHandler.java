package com.yun.util.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yun
 * created_time 2019/11/7 17:22.
 */

public interface AuthTokenHandler {
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
}
