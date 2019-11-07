package com.yun.util.apilog;

/**
 * @author: yun
 * @createdOn: 2019/9/9 17:09.
 */

public interface ApiLogInterceptor {

    /**
     * @param apiData
     * @return true -记录日志；false -不记录
     */
    default boolean beforeLog(ApiData apiData) {
        return true;
    }

    /**
     * @return true -发送心跳； false -不发送心跳
     */
    default boolean beforeHeart() {
        return true;
    }
}
