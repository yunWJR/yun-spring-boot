package com.yun.util.common;

/**
 * @author yun
 * created_time 2018/6/7 14:19.
 */

public class CommonException extends RuntimeException {

    // region --Field

    private Integer code;

    // endregion

    // region --Constructor

    public CommonException() {
        super("未知错误");
        this.code = -1;
    }

    public CommonException(Integer code, String error) {
        super(error);
        this.code = code;
    }

    public static CommonException CommonEp(String err) {
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