package com.github.yunwjr.yun.spring.boot.authorization;

/**
 * @author yun
 * created_time 2018/6/7 14:19.
 */

public class AuthException extends RuntimeException {

    // region --Field

    /**
     *
     */
    private Integer code;

    // endregion

    // region --Constructor

    /**
     * @param error
     */
    public AuthException(String error) {
        super(error);
    }

    /**
     * @param code
     * @param error
     */
    public AuthException(Integer code, String error) {
        super(error);
        this.code = code;
    }

    // endregion

    // region --static method

    /**
     * @param error
     * @return
     */
    public static AuthException ComErr(String error) {
        return new AuthException(error);
    }

    // endregion

    // region --

    /**
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    // endregion

    // region --Public method

    // endregion
}
