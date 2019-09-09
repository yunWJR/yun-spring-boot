package com.yun.util.apilog;

import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019/9/9 17:18.
 */

@Component
public class ApiLogAdapterImpl implements ApiLogAdapter {
    /**
     * 记录apiData 之前调用
     * @param apiData
     * @return
     */
    @Override
    public boolean beforeLog(ApiData apiData) {
        return true;
    }
}
