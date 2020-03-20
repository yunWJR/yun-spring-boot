package com.yun.util.common;

/**
 * @author: yun
 * @createdOn: 2018/6/7 14:19.
 */

public class CommonException extends RuntimeException {

    // region --Field

    private Integer code;

    // endregion

    // region --Constructor

    public CommonException(Integer code, String error) {
        super(error);
        this.code = code;
    }

    public static CommonException comEp(String err) {
        CommonException ep = new CommonException(-1, err);
        return ep;
    }

    // endregion

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}