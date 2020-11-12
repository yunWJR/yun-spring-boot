package com.yun.util.sb.rsp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author yun
 * created_time 2018/8/7 10:13.
 */

public class RspDataT<T> {

    // region --Field

    /**
     * 1.状态码
     */
    private int code = RspDataCodeType.None.getCode();

    /**
     * 2.错误信息
     */
    private String message = "";

    /**
     * 3.结果数据
     */
    private T result;

    /**
     * 4.详细信息
     */
    private String details = "";

    // endregion

    // region --Constructor

    /**
     * Instantiates a new Base rst bean.
     */
    public RspDataT() {
    }

    /**
     * Instantiates a new Base rst bean.
     * @param result the data
     */
    public RspDataT(T result) {
        this.initForSuc();

        this.result = result;
    }

    /**
     * Instantiates a new Base rst bean.
     * @param type the itemType
     */
    public RspDataT(RspDataCodeType type) {
        this.code = type.getCode();
        this.message = type.getMsg();
    }

    /**
     * Instantiates a new Base rst bean.
     * @param type    the itemType
     * @param message the error msg
     */
    public RspDataT(RspDataCodeType type, String message) {
        this.code = type.getCode();

        if (message != null && message.length() > 0) {
            this.message = message;
        } else {
            this.message = type.getMsg();
        }
    }

    /**
     * Instantiates a new Base rst bean.
     * @param code    the code
     * @param message the error msg
     */
    public RspDataT(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // endregion

    // region --static method

    /**
     * Sur bean base rst bean.
     * @param data the data
     * @return the base rst bean
     */
    public static <T> RspDataT<T> SurBean(T data) {
        RspDataT<T> rst = new RspDataT<T>(data);

        return rst;
    }

    /**
     * Com err bean base rst bean.
     * @param errorMsg the error msg
     * @return the base rst bean
     */
    public static <T> RspDataT<T> ComErrBean(String errorMsg) {
        RspDataT rst = new RspDataT(RspDataCodeType.ComErr.getCode(), errorMsg);

        return rst;
    }

    public static <T> RspDataT<T> ErrBeanByCode(int code) {
        RspDataT rst = new RspDataT(code, "");

        return rst;
    }

    // endregion

    // region --Getter and Setter

    /**
     * Gets code.
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets error msg.
     * @return the error msg
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets error msg.
     * @param message the error msg
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets data.
     * @return the data
     */
    public T getResult() {
        return result;
    }

    /**
     * Sets data.
     * @param result the data
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * @return
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    // endregion

    // region --Public method

    /**
     * Is suc boolean.
     * @return the boolean
     */
    @JsonIgnore // isSuc 会默认为 get 方法，忽略该字段
    public boolean isSuc() {
        return code == RspDataCodeType.Suc.getCode() || code == RspDataCodeType.None.getCode();
    }

    /**
     * Is error boolean.
     * @return the boolean
     */
    @JsonIgnore // isError 会默认为 get 方法，忽略该字段
    public boolean isError() {
        return !this.isSuc();
    }

    /**
     * Sets by code itemType.
     * @param type the itemType
     */
    public void updateByCodeType(RspDataCodeType type) {
        this.code = type.getCode();
        this.message = type.getMsg();
    }

    /**
     * Init for suc.
     */
    public void initForSuc() {
        this.updateByCodeType(RspDataCodeType.Suc);
    }

    /**
     * Init for com err.
     */
    public void initForComErr() {
        this.updateByCodeType(RspDataCodeType.ComErr);

        this.setResult(null);
    }

    public <E> RspDataT<E> errRst() {
        RspDataT<E> err = new RspDataT<E>();
        err.setCode(this.code);
        err.setMessage(this.message);

        return err;
    }

    // endregion
}
