package com.yun.util.auth;

/**
 * @author: yun
 * @createdOn: 2018/6/7 14:19.
 */

public class AuthException extends RuntimeException {

    // region --Field

    private Integer code;

    // endregion

    // region --Constructor

    public AuthException(String error) {
        super(error);
    }

    public AuthException(Integer code, String error) {
        super(error);
        this.code = code;
    }

    // endregion

    // region --static method

    public static AuthException ComErr(String error) {
        return new AuthException(error);
    }

    // endregion

    // region --

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    // endregion

    // region --Public method

    // endregion
}
