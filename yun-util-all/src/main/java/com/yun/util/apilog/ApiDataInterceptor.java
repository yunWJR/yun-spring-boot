package com.yun.util.apilog;

import com.yun.util.common.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.logstash.logback.argument.StructuredArguments.value;

/**
 * 拦截请求，记录请求数据和返回数据
 * @author: yun
 * @createdOn: 2019/8/30 09:47.
 */

@Slf4j
public class ApiDataInterceptor implements HandlerInterceptor {
    private final static String API_DATA_LT_KEY = "API_DATA";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ApiData apiData = new ApiData(request);

        ApiDataUtil.saveInterceptorData(apiData);

        ThreadLocalUtil.put(API_DATA_LT_KEY, apiData);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ApiData apiData = ApiDataUtil.getInterceptorData();

        if (apiData != null) {
            apiData.updateEndTime();

            log.info("api data {}", value("api_data", apiData));
            // log.info("api data {}", fields(apiData));

            ApiDataUtil.removeInterceptorData();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
