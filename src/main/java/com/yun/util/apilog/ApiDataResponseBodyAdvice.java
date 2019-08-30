package com.yun.util.apilog;

import com.yun.util.common.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static net.logstash.logback.argument.StructuredArguments.value;

/**
 * @author: yun
 * @createdOn: 2019/8/30 13:37.
 */

@Slf4j
@RestControllerAdvice
public class ApiDataResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        ApiData apiData = ApiDataUtil.getAdviceData();

        if (apiData != null && request.getHeaders() != null) {
            MediaType ctType = request.getHeaders().getContentType();

            if (ctType != null && ApiDataUtil.isJson(ctType.toString())) {
                apiData.setResponse(JsonHelper.toStr(body));
            } else {
                apiData.setResponse("无法解析非 JSON 类型的 response");
            }

            apiData.updateHttp(request);

            log.info("api data {}", value("api_data", apiData));

            ApiDataUtil.removeAdviceData();
        }

        return body;
    }
}
