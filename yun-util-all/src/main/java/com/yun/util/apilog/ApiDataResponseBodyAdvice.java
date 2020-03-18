package com.yun.util.apilog;

import com.yun.util.common.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
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

    private Map<Method, ApiLogFiled> mtFiledMap = new HashMap<>();
    private HashSet<Method> noFiledSet = new HashSet<>();

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

        ApiLogFiled filed = getFiled(returnType.getMethod());

        if (filed == null || filed.responseBody()) {
            String rsp = getRsp(body, request);

            apiData.setResponse(rsp);
        }

        apiData.updateHttp(request, filed);

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

    private ApiLogFiled getFiled(Method mt) {
        ApiLogFiled item = mtFiledMap.get(mt);
        if (item != null) {
            return item;
        }

        if (noFiledSet.contains(mt)) {
            return null;
        }

        for (Annotation at : mt.getDeclaredAnnotations()) {
            if (at.annotationType() == ApiLogFiled.class) {

                ApiLogFiled filed = (ApiLogFiled) at;

                mtFiledMap.put(mt, filed);
                return filed;
            }
        }

        noFiledSet.add(mt);

        return null;
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
