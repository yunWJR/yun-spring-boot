package com.yun.util.apilog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yun
 * created_time 2019/11/7 16:47.
 */

@Component
@Primary
public class ApiLogInterceptorComposite implements ApiLogInterceptor {
    private List<ApiLogInterceptor> list = null;

    @Autowired(required = false)
    public void setConfigurers(List<ApiLogInterceptor> items) {
        this.list = items;
    }

    @Override
    public boolean beforeLog(ApiData apiData) {
        if (list == null) {
            return true;
        }

        for (ApiLogInterceptor apiLogInterceptor : list) {
            if (!apiLogInterceptor.beforeLog(apiData)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean beforeHeart() {
        if (list == null) {
            return true;
        }

        for (ApiLogInterceptor apiLogInterceptor : list) {
            if (!apiLogInterceptor.beforeHeart()) {
                return false;
            }
        }

        return true;
    }
}
