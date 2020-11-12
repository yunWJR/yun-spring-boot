package com.yun.util.authorization;

import com.yun.util.common.CommonException;

/**
 * @author yun
 * created_time 2018/6/7 14:19.
 */

public class AuthException extends CommonException {

    public AuthException() {
        super();
    }

    public AuthException(Integer code, String error) {
        super(code, error);
    }
}
