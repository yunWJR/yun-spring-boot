package com.yun.util.common;

/**
 * @author yun
 * created_time 2018/6/7 14:19.
 */

public class CommonException extends RuntimeException {

    // region --Field

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 日志信息
     */
    private String logMsg;

    // endregion

    // region --Constructor

    /**
     *
     */
    public CommonException() {
        super("未知错误");
        this.code = -1;
    }

    /**
     * @param code
     * @param error
     */
    public CommonException(Integer code, String error) {
        super(error);
        this.code = code;
    }

    /**
     * @param code
     * @param error
     */
    public CommonException(Integer code, String error, String logMsg) {
        super(error);
        this.code = code;
        this.logMsg = logMsg;
    }

    public static CommonException CommonEp(String err) {
        CommonException ep = new CommonException(-1, err);
        return ep;
    }

    public static CommonException CommonEp(String err, String logMsg) {
        CommonException ep = new CommonException(-1, err, logMsg);
        return ep;
    }

    // endregion

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }
}