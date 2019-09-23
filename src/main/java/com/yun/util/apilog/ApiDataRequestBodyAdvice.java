package com.yun.util.apilog;

import com.yun.util.common.JsonHelper;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019/8/30 13:37.
 */

@RestControllerAdvice
public class ApiDataRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        ApiData apiData = ApiDataUtil.getAdviceData();
        if (apiData == null) {
            apiData = ApiData.newItem();
        }

        boolean isJsonBody = false;
        if (inputMessage.getHeaders() != null) {
            List<String> ctHeaders = inputMessage.getHeaders().get("Content-Type");
            if (ctHeaders != null && ctHeaders.size() > 0) {
                for (String ctHeader : ctHeaders) {
                    if (ApiDataUtil.isJson(ctHeader)) {
                        isJsonBody = true;
                        break;
                    }
                }
            }
        }

        if (isJsonBody) {
            apiData.setBody(JsonHelper.toStr(body));
        } else {
            apiData.setBody(body.toString());
        }

        ApiDataUtil.saveAdviceData(apiData);

        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
