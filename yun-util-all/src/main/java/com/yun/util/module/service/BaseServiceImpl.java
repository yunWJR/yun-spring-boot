package com.yun.util.module.service;

import com.yun.util.common.StringUtil;
import com.yun.util.module.rsp.RspDataException;

/**
 * @author: yun
 * @createdOn: 2019/9/7 22:34.
 */

public class BaseServiceImpl {
    public RspDataException commonEp(String errMsg) {
        return RspDataException.RstComErrBeanWithStr(errMsg);
    }

    public void handleCheckRst(String rst) {
        if (!StringUtil.isNullOrEmpty(rst)) {
            throw commonEp(rst);
        }
    }
}