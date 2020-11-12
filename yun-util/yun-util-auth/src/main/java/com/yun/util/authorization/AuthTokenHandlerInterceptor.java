package com.yun.util.authorization;

import com.yun.util.common.StringUtil;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器
 * 1、检查 token
 * 2、记录请求参数
 * @author yun
 * created_time 2019/11/7 17:22.
 */

public class AuthTokenHandlerInterceptor implements HandlerInterceptor {

    private final AuthTokenHandler authTokenHandler;

    private final AuthStatusUtil authStatusUtil;

    private final AuthSettingBean authSettingBean;

    private final AuthContextHolder authContextHolder;

    public AuthTokenHandlerInterceptor(AuthTokenHandler authTokenHandler, AuthStatusUtil authStatusUtil, AuthSettingBean authSettingBean, AuthContextHolder authContextHolder) {
        this.authTokenHandler = authTokenHandler;
        this.authStatusUtil = authStatusUtil;
        this.authSettingBean = authSettingBean;
        this.authContextHolder = authContextHolder;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // OPTIONS不处理
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            savePara(request);
            return true;
        }
        // // OPTIONS 不鉴权
        // if (HttpMethod.OPTIONS.matches(request.getMethod())) {
        //     savePara(request);
        //     return true;
        // }

        // 类型判断
        if (!(handler instanceof HandlerMethod)) {
            savePara(request);
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 是否设置系统状态
        if (authStatusUtil.noAuthWhenSystemStop(handlerMethod)) {
            throw AuthException.CommonEp("服务状态已停止，请稍候重试");
        }

        // 根据注解确定是否检查
        if (authStatusUtil.notRequiredAuth(handlerMethod)) {
            savePara(request);
            return true;
        }

        // token 检查
        String authStr = request.getHeader(authSettingBean.getAuthKey());

        // 无 token
        if (StringUtil.isNullOrEmpty(authStr)) {
            return handleNoAuth(request, response, handler);
        }

        Object user = authTokenHandler.checkUser(authStr, request);

        // 授权无效
        if (user == null) {
            return handleNoAuth(request, response, handler);
        }

        // 获取设备类型
        savePara(request);

        // 保存用户信息
        authContextHolder.saveAuthObj(user);

        return true;
    }

    private boolean handleNoAuth(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 自己处理 response
        if (authTokenHandler.canHandleNoAuthResponse()) {
            boolean status = authTokenHandler.handleNoAuthResponse(request, response, handler);

            return status;
        }

        // 默认处理
        setRspNoAuthHttpStatus(response);
        return false;
    }

    private void setRspNoAuthHttpStatus(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        response.setHeader("content-type", "application/json");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setStatus(401);
    }

    private void setRspNoAuthData(HttpServletResponse response) throws IOException {
        response.resetBuffer();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"code\":401 ,\"status\":401 ,\"message\" :\"no auth\"}");
        response.flushBuffer();
    }

    private void savePara(HttpServletRequest request) {
        authTokenHandler.savePara(request);
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        authContextHolder.removeAll();

        if (ex != null) {
            // response 处理 ex
            ex.printStackTrace();
            this.handleException(response, ex);
        }
    }

    private void handleException(HttpServletResponse res, Exception ex) throws IOException {
        res.resetBuffer();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(String.format("{\"code\":-1 ,\"message\" :\"%s\"}", ex.getMessage())); // todo 格式化要求
        res.flushBuffer();
    }
}
