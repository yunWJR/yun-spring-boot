package com.yun.util.module.rsp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author: yun
 * @createdOn: 2018/8/7 10:13.
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
    private String errorMsg = "";

    /**
     * 3.结果数据
     */
    private T data;

    /**
     * 4.服务名称-可选
     */
    private String serverName = "";

    // endregion

    // region --Constructor

    /**
     * Instantiates a new Base rst bean.
     */
    public RspDataT() {
    }

    /**
     * Instantiates a new Base rst bean.
     * @param data the data
     */
    public RspDataT(T data) {
        this.initForSuc();

        this.data = data;
    }

    /**
     * Instantiates a new Base rst bean.
     * @param type the itemType
     */
    public RspDataT(RspDataCodeType type) {
        this.code = type.getCode();
        this.errorMsg = type.getMsg();
    }

    /**
     * Instantiates a new Base rst bean.
     * @param type     the itemType
     * @param errorMsg the error msg
     */
    public RspDataT(RspDataCodeType type, String errorMsg) {
        this.code = type.getCode();

        if (errorMsg.length() > 0) {
            this.errorMsg = errorMsg;
        } else {
            this.errorMsg = type.getMsg();
        }
    }

    /**
     * Instantiates a new Base rst bean.
     * @param code     the code
     * @param errorMsg the error msg
     */
    public RspDataT(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
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
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets error msg.
     * @param errorMsg the error msg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Gets data.
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    // endregion

    // region --Public method

    /**
     * Is suc boolean.
     * @return the boolean
     */
    @JsonIgnore // isSuc 会默认为 get 方法，忽略该字段
    public boolean isSuc() {
        if (code == RspDataCodeType.Suc.getCode() || code == RspDataCodeType.None.getCode()) {
            return true;
        }

        return false;
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
        this.errorMsg = type.getMsg();
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

        this.setData(null);
    }

    public <E> RspDataT<E> errRst() {
        RspDataT<E> err = new RspDataT<E>();
        err.setCode(this.code);
        err.setErrorMsg(this.errorMsg);

        return err;
    }

    // endregion
}
