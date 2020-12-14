package com.yun.util.swagger;

/**
 * @author yun
 * created_time 2019-04-09 15:51.
 */

public interface SwaggerPara {
    /**
     * 是否正式环境
     * @return
     */
    default boolean isProEvn() {
        return false;
    }

    /**
     * @return
     */
    default String getTokenAuthKey() {
        return null;
    }

    /**
     * @return
     */
    default String getDeviceTypeKey() {
        return null;
    }
}