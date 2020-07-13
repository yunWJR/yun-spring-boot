package com.yun.util.sb.rsp;

import com.yun.util.common.JsonUtil;

import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2020/3/20 16:57.
 */

public interface RspDataTransfer {
    default <T> Object transferEp(Integer code, String errorMsg) {
        RspDataT rst = new RspDataT(code, errorMsg);

        return transfer(rst);
    }

    default <T> Object transfer(RspDataT<T> rspDataT) {
        return rspDataT;
    }

    default <T> Map<String, Object> transferToMap(RspDataT<T> rspDataT) {
        return JsonUtil.objToMapJack(rspDataT);
    }
}
