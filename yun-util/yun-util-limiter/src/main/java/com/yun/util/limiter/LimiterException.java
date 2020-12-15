package com.yun.util.limiter;

import com.yun.util.common.CommonException;

/**
 * @author yun
 * created_time 2018/6/7 14:19.
 */

public class LimiterException extends CommonException {

    public LimiterException() {
        super();
    }

    public LimiterException(Integer code, String error) {
        super(code, error);
    }
}
