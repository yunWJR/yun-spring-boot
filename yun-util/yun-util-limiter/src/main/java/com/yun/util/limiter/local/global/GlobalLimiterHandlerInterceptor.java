package com.yun.util.limiter.local.global;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 授权拦截器实现
 * @author yun
 * created_time 2019/11/7 17:22.
 */

@Order(10)
public class GlobalLimiterHandlerInterceptor implements HandlerInterceptor {
    private final GlobalLimiterServiceImpl globalLimiterService;

    public GlobalLimiterHandlerInterceptor(GlobalLimiterServiceImpl globalLimiterService) {
        this.globalLimiterService = globalLimiterService;
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // OPTIONS 不处理
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }

        // 全局请求，可以用全局连接数代替
        globalLimiterService.checkAllRequest();

        // ip 限定
        if (globalLimiterService.ipQpsOn()) {
            String ip = ServletUtil.getClientIP(request);
            globalLimiterService.checkIp(ip);
        }

        return true;
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
    }

    /**
     * @return
     */
    public List<String> allowPathPatterns() {
        HashSet<String> allows = new HashSet<>();

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

        return new ArrayList<String>(excludes);
    }
}
