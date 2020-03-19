package com.yun.util.examples.config.yunutil;

import com.yun.util.apilog.ApiData;
import com.yun.util.apilog.ApiLogInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019/9/19 13:47.
 */

@Component
public class YunUtilLogInterceptor implements ApiLogInterceptor {
    @Override
    public boolean beforeHeart() {

        return true;
    }

    @Override
    public boolean beforeLog(ApiData apiData) {
        // 日志中，添加userId
        apiData.setAccount("test user acct");

        return true;
    }
}
