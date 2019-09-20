package com.yun.util.apilog;

/**
 * @author: yun
 * @createdOn: 2019/9/9 17:09.
 */

public interface ApiLogInterceptor {

    /**
     * @param apiData
     * @return
     */
    default boolean beforeLog(ApiData apiData) {
        return true;
    }

    /**
     * @return false -不发送心跳
     */
    default boolean beforeHeart() {
        return true;
    }
}
