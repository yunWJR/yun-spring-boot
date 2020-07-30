package com.yun.util.apilog.advice;

import com.yun.util.apilog.ApiData;
import com.yun.util.apilog.ApiDataUtil;
import com.yun.util.apilog.ApiLogInterceptor;
import com.yun.util.apilog.ApiLogProperties;
import com.yun.util.apilog.annotations.ApiLogAnnotationsUtil;
import com.yun.util.apilog.annotations.ApiLogFiledStatus;
import com.yun.util.common.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.value;

/**
 * @author yun
 * created_time 2019/8/30 13:37.
 */

@RestControllerAdvice
public class ApiDataResponseBodyAdvice implements ResponseBodyAdvice {
    private final Logger log = LoggerFactory.getLogger(ApiDataResponseBodyAdvice.class);

    private final ApiLogProperties apiLogProperties;

    private final ApiLogInterceptor apiLogInterceptor;

    public ApiDataResponseBodyAdvice(ApiLogProperties apiLogProperties, ApiLogInterceptor apiLogInterceptor) {
        this.apiLogProperties = apiLogProperties;
        this.apiLogInterceptor = apiLogInterceptor;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 记录返回数据和 header
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ApiData apiData = ApiDataUtil.getAdviceData();
        if (apiData == null) {
            apiData = ApiData.newItem();
        }

        if (apiData.getStatus() == null) {
            ApiLogFiledStatus status = ApiLogAnnotationsUtil.getFiledStatus(returnType.getMethod(), apiLogProperties);
            apiData.setStatus(status);
        }

        // 保存 rsp
        if (apiData.getStatus().isResponseBody()) {
            String rsp = getRsp(body, request);

            apiData.setResponse(rsp);
        }

        // 保存 header
        if (apiData.getStatus().isHeader()) {
            apiData.updateHttp(request);
        }

        // 判断是否需要记录日志
        if (!apiData.getStatus().isIgnoreLog() && apiLogInterceptor == null || apiLogInterceptor.beforeLog(apiData)) {
            try {
                Map alMap = apiData.getLogMap(apiLogProperties);

                if (apiData.isErrorData()) {
                    log.error(apiLogProperties.getMsg() + " {}", value(apiLogProperties.getPrefix(), alMap));
                } else {
                    log.info(apiLogProperties.getMsg() + " {}", value(apiLogProperties.getPrefix(), alMap));
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        ApiDataUtil.removeAdviceData();

        return body;
    }

    private String getRsp(Object body, ServerHttpRequest request) {
        boolean isJsonBody = false;

        if (request.getHeaders() != null) {
            MediaType ctType = request.getHeaders().getContentType();

            if (ctType != null && ApiDataUtil.canToJson(ctType.toString())) {
                isJsonBody = true;
            }
        }

        if (isJsonBody) {
            return JsonUtil.toStr(body);
        } else {
            return body.toString();
        }
    }
}
