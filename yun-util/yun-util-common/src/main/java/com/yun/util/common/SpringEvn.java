package com.yun.util.common;

/**
 * @author: yun
 * @createdOn: 2019-04-09 15:51.
 */

public interface SpringEvn {
    /**
     * 是否正式环境
     * @return
     */
    boolean isProEvn();

    /**
     * 是否正式环境
     * @return
     */
    Integer isProValue();
}
