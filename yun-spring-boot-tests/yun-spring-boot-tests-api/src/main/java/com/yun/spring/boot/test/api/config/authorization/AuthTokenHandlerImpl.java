package com.yun.spring.boot.test.api.config.authorization;

import com.github.yunwjr.yun.spring.boot.authorization.AuthTokenHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yun
 * <p>
 * create_time  2020/8/3 14:01.
 */

@Component
public class AuthTokenHandlerImpl implements AuthTokenHandler {
    @Override
    public Object checkUser(String tokenStr, HttpServletRequest request) {
        return new Object();
    }

    @Override
    public void savePara(HttpServletRequest request) {

    }

    @Override
    public boolean canHandleNoAuthResponse() {
        return true;
    }

    @Override
    public boolean handleNoAuthResponse(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            response.resetBuffer();
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":401 ,\"status\":401 ,\"message\" :\"no auth\"}");
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
