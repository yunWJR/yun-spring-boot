package com.yun.util.auth;

import com.yun.util.common.SpringContextUtil;
import com.yun.util.common.StringUtil;
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
 * @author: yun
 * @createdOn: 2019/11/7 17:22.
 */

public class TokenAuthHandlerInterceptor implements HandlerInterceptor {

    /**
     * 需要手动注入
     */
    private TokenAuthHandler tokenAuthHandler;

    private AuthUtil authUtil;

    /**
     * 该类不会自动注入，所以 bean 需要手动注入
     * @return
     */
    private TokenAuthHandler tokenAuthHd() {
        if (tokenAuthHandler == null) {
            tokenAuthHandler = SpringContextUtil.getBean(TokenAuthHandler.class);
        }

        if (tokenAuthHandler == null) {
            throw AuthException.ComErr("请实现TokenAuthHandler接口");
        }

        return tokenAuthHandler;
    }

    private AuthUtil authUtil() {
        if (authUtil == null) {
            authUtil = SpringContextUtil.getBean(AuthUtil.class);
        }

        return authUtil;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 跨域试探请求不处理 todo
        if (!(handler instanceof HandlerMethod)) {
            savePara(request);
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 是否设置系统状态
        if (authUtil().noAuthWhenSystemStop(handlerMethod)) {
            throw AuthException.ComErr("服务状态已停止，请稍候重试");
        }

        // 根据注解确定是否检查
        if (authUtil().hasNoCheckToken(handlerMethod)) {
            savePara(request);
            return true;
        }

        // token 检查
        String tokenStr = request.getHeader(AuthPropertyUtil.prop().getTokenAuthKey());

        // 无 token
        if (StringUtil.isNullOrEmpty(tokenStr)) {
            setRspNoToken(response);

            return false;
        }

        Object user = tokenAuthHd().checkUser(tokenStr, request);

        // token 无效
        if (user == null) {
            setRspNoToken(response);

            return false;
        }

        // 获取设备类型
        savePara(request);

        // 保存用户信息
        AuthDtoUtil.saveTokenDto(user);

        return true;
    }

    private void setRspNoToken(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        response.setHeader("content-type", "application/json");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setStatus(401);
    }

    private void savePara(HttpServletRequest request) {
        // 不记录默认值，避免冲突，header 参数里面已经记录这些内容
        // String hVale = request.getHeader(AuthPropertyUtil.prop().getDeviceTypeKey());
        // if (!StringUtil.isNullOrEmpty(hVale)) {
        //     ThreadLocalUtil.put(AuthPropertyUtil.prop().getDeviceTypeKey(), hVale);
        // }
        //
        // hVale = request.getHeader(AuthPropertyUtil.prop().getAccessAuthKey());
        // if (!StringUtil.isNullOrEmpty(hVale)) {
        //     ThreadLocalUtil.put(AuthPropertyUtil.prop().getAccessAuthKey(), hVale);
        // }
        //
        // hVale = request.getHeader(AuthPropertyUtil.prop().getTokenAuthKey());
        // if (!StringUtil.isNullOrEmpty(hVale)) {
        //     ThreadLocalUtil.put(AuthPropertyUtil.prop().getTokenAuthKey(), hVale);
        // }

        tokenAuthHd().savePara(request);
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
        AuthDtoUtil.removeAll();

        if (ex != null) {
            this.handleException(response, ex);
        }
    }

    private void handleException(HttpServletResponse res, Exception ex) throws IOException {
        res.resetBuffer();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(String.format("{\"code\":-1 ,\"message\" :\"%s\"}", ex.getMessage()));
        res.flushBuffer();
    }
}
