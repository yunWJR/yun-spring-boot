package com.github.yunwjr.yun.spring.boot.common;

/**
 * @author yun
 * created_time 2019-04-09 15:51.
 */

public interface SpringEvn {
    /**
     * 是否正式环境
     * @return true
     */
    boolean isProEvn();

    /**
     * 是否正式环境
     * @return 1
     */
    Integer isProValue();
}
