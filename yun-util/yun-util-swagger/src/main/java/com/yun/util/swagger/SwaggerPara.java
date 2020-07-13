package com.yun.util.swagger;

/**
 * @author: yun
 * @createdOn: 2019-04-09 15:51.
 */

public interface SwaggerPara {
    /**
     * 是否正式环境
     * @return
     */
    default boolean isProEvn() {
        return false;
    }

    default String getTokenAuthKey() {
        return null;
    }

    default String getDeviceTypeKey() {
        return null;
    }
}