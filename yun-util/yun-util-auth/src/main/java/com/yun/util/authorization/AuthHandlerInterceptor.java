package com.yun.util.authorization;

import com.yun.util.common.StringUtil;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 授权拦截器实现
 * 1、检查 token
 * 2、记录请求参数
 * @author yun
 * created_time 2019/11/7 17:22.
 */

public class AuthHandlerInterceptor implements HandlerInterceptor {

    private final AuthHandlerUtil authHandlerUtil;

    private final AuthProperties authProperties;

    private final AuthContextHolder authContextHolder;

    private final AuthPathInterceptor[] pathInterceptors;

    public AuthHandlerInterceptor(AuthHandlerUtil authHandlerUtil, AuthProperties authProperties, AuthContextHolder authContextHolder, AuthPathInterceptor[] pathInterceptors) {
        this.authHandlerUtil = authHandlerUtil;
        this.authProperties = authProperties;
        this.authContextHolder = authContextHolder;
        this.pathInterceptors = pathInterceptors;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // OPTIONS 不处理
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            authHandlerUtil.handleOptionsRequest(request, response, handler);
            return true;
        }

        // 类型判断 非HandlerMethod如何处理？
        if (!(handler instanceof HandlerMethod)) {
            authHandlerUtil.handleNotHandlerMethod(request, response, handler);
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 判断服务器状态
        if (authHandlerUtil.noAuthWhenSystemStop(handlerMethod)) {
            authHandlerUtil.handleNoAuthWhenSystemStop(request, response, handler);
            throw AuthException.CommonEp("服务已停止，请稍候重试或联系管理员");
        }

        // 检查是否需要权限。注解和自定义方法
        if (authHandlerUtil.notRequiredAuth(handlerMethod, request)) {
            authHandlerUtil.handleNotRequiredAuth(request, response, handler);
            return true;
        }

        // token 检查
        String authStr = request.getHeader(authProperties.getSetting().getAuthKey());

        // 无 token
        if (StringUtil.isNullOrEmpty(authStr)) {
            return handleNoAuth(request, response, handler);
        }

        Object user = authHandlerUtil.checkUser(authStr, request);

        // 授权无效
        if (user == null) {
            return handleNoAuth(request, response, handler);
        }

        // 保存用户前业务处理（针对用户的限流）
        authHandlerUtil.handleBeforeSaveUser(user, request, response, handler);

        // 保存用户信息
        authContextHolder.saveAuthObj(user);

        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     */
    private boolean handleNoAuth(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 自己处理 response
        if (authHandlerUtil.handleNoAuthResponse(request, response, handler)) {
            return false;
        }

        // 默认处理
        setRspNoAuthHttpStatus(response);
        return false;
    }

    /**
     * @param response
     */
    private void setRspNoAuthHttpStatus(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
        response.setHeader("content-type", "application/json");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setStatus(401);
    }

    /**
     * @param response
     * @throws IOException
     */
    private void setRspNoAuthData(HttpServletResponse response) throws IOException {
        response.resetBuffer();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"code\":401 ,\"status\":401 ,\"message\" :\"no auth\"}");
        response.flushBuffer();
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

    /**
     * @param res
     * @param ex
     * @throws IOException
     */
    private void handleException(HttpServletResponse res, Exception ex) throws IOException {
        res.resetBuffer();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(String.format("{\"code\":-1 ,\"message\" :\"%s\"}", ex.getMessage())); // todo 格式化要求
        res.flushBuffer();
    }

    /**
     * @return
     */
    public List<String> allowPathPatterns() {
        HashSet<String> allows = new HashSet<>();
        if (authProperties.getAllowPathPatterns() != null) {
            allows.addAll(authProperties.getAllowPathPatterns());
        }

        if (pathInterceptors != null) {
            for (AuthPathInterceptor pathInterceptor : pathInterceptors) {
                List<String> allowsI = pathInterceptor.allowPathPatterns();
                if (allowsI != null) {
                    allows.addAll(allowsI);
                }
            }
        }

        // 没有则默认所有
        if (allows.size() == 0) {
            return Collections.singletonList("/**");
        }

        return new ArrayList<String>(allows);
    }

    /**
     * @return
     */
    public List<String> excludePathPatterns() {
        HashSet<String> excludes = new HashSet<>();
        if (authProperties.getExcludePathPatterns() != null) {
            excludes.addAll(authProperties.getExcludePathPatterns());
        }

        if (pathInterceptors != null) {
            for (AuthPathInterceptor pathInterceptor : pathInterceptors) {
                List<String> excludesI = pathInterceptor.excludePathPatterns();
                if (excludesI != null) {
                    excludes.addAll(excludesI);
                }
            }
        }

        return new ArrayList<String>(excludes);
    }
}
