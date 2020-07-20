package com.yun.util.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yun
 * created_time 2019/11/7 17:22.
 */

public interface TokenAuthHandler {
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
}
