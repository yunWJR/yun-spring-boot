package com.yun.util.sb.rsp;

/**
 * The itemType Base rst bean.
 * @author yun
 * created_time 2018 /5/29 14:38.
 */
public class RspData extends RspDataT<Object> {
    /**
     * Instantiates a new Base rst bean.
     */
    public RspData() {
    }

    /**
     * Instantiates a new Base rst bean.
     * @param data the data
     */
    public RspData(Object data) {
        this.initForSuc();

        setData(data);
    }

    /**
     * Instantiates a new Base rst bean.
     * @param type the itemType
     */
    public RspData(RspDataCodeType type) {
        setCode(type.getCode());
        setErrorMsg(type.getMsg());
    }

    /**
     * Instantiates a new Base rst bean.
     * @param type     the itemType
     * @param errorMsg the error msg
     */
    public RspData(RspDataCodeType type, String errorMsg) {
        setCode(type.getCode());

        if (errorMsg != null && errorMsg.length() > 0) {
            setErrorMsg(errorMsg);
        } else {
            setErrorMsg(type.getMsg());
        }
    }

    /**
     * Instantiates a new Base rst bean.
     * @param code     the code
     * @param errorMsg the error msg
     */
    public RspData(int code, String errorMsg) {
        setCode(code);
        setErrorMsg(errorMsg);
    }

    // endregion

    // region --static method

    /**
     * Sur bean base rst bean.
     * @param data the data
     * @return the base rst bean
     */
    public static RspData SurBean(Object data) {
        RspData rst = new RspData(data);

        return rst;
    }

    /**
     * Com err bean base rst bean.
     * @param errorMsg the error msg
     * @return the base rst bean
     */
    public static RspData ComErrBean(String errorMsg) {
        RspData rst = new RspData(RspDataCodeType.ComErr.getCode(), errorMsg);

        return rst;
    }

    public static RspData ErrBeanByCode(int code) {
        RspData rst = new RspData(code, "");

        return rst;
    }
}