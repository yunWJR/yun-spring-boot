package com.yun.util.apilog;

import com.yun.util.common.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.value;

/**
 * @author: yun
 * @createdOn: 2019/8/30 13:37.
 */

@Slf4j
@RestControllerAdvice
public class ApiDataResponseBodyAdvice implements ResponseBodyAdvice {
    private final ApiLogProperty apiLogProperty;

    private final ApiLogInterceptor apiLogInterceptor;

    public ApiDataResponseBodyAdvice(ApiLogProperty apiLogProperty, ApiLogInterceptor apiLogInterceptor) {
        this.apiLogProperty = apiLogProperty;
        this.apiLogInterceptor = apiLogInterceptor;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        ApiData apiData = ApiDataUtil.getAdviceData();
        if (apiData == null) {
            apiData = ApiData.newItem();
        }

        boolean isJsonBody = false;

        if (request.getHeaders() != null) {
            MediaType ctType = request.getHeaders().getContentType();

            if (ctType != null && ApiDataUtil.isJson(ctType.toString())) {
                isJsonBody = true;
            }
        }

        if (isJsonBody) {
            apiData.setResponse(JsonUtil.toStr(body));
        } else {
            apiData.setResponse(body.toString());
        }

        apiData.updateHttp(request);

        if (apiLogInterceptor == null || apiLogInterceptor.beforeLog(apiData)) {
            try {
                Map alMap = apiData.getLogMap(apiLogProperty);

                log.info(apiLogProperty.getMsg() + " {}", value(apiLogProperty.getPrefix(), alMap));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        ApiDataUtil.removeAdviceData();

        return body;
    }
}
