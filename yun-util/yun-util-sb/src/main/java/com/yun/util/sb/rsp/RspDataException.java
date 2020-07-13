package com.yun.util.sb.rsp;

/**
 * @author: yun
 * @createdOn: 2018/6/7 14:19.
 */

public class RspDataException extends RuntimeException {

    // region --Field

    private RspData rst;

    // endregion

    // region --Constructor

    public RspDataException(RspData rst) {
        super(rst.getErrorMsg());
        this.rst = rst;
    }

    public RspDataException(Integer code, String error) {
        super(error);
        this.rst = new RspData(code, error);
    }

    // endregion

    // region --static method

    public static RspDataException RstComErrBeanWithStr(String error) {
        return new RspDataException(RspData.ComErrBean(error));
    }

    public static RspDataException RstTypeErrBeanWithType(RspDataCodeType type) {
        return new RspDataException(new RspData(type));
    }

    public static RspDataException RstCodeErrBean(Integer code) {
        return new RspDataException(new RspData(code, ""));
    }

    // endregion   

    // region --Getter and Setter

    public RspData getRst() {
        return rst;
    }

    // endregion

    // region --Public method

    public void setRst(RspData rst) {
        this.rst = rst;
    }

    // endregion
}
