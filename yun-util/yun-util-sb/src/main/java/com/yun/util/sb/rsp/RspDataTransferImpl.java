package com.yun.util.sb.rsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yun
 * created_time 2020/3/20 16:40.
 */

@Component
public class RspDataTransferImpl implements RspDataTransfer {

    private final RspDataProperties rspDataProperties;

    private boolean isOrg = true;

    @Autowired
    public RspDataTransferImpl(RspDataProperties prop) {
        this.rspDataProperties = prop;

        RspDataProperties org = new RspDataProperties();
        if (!org.getCodeKey().equals(prop.getCodeKey())) {
            isOrg = false;
            return;
        }

        if (!org.getDataKey().equals(prop.getDataKey())) {
            isOrg = false;
            return;
        }

        if (!org.getErrorMsgKey().equals(prop.getErrorMsgKey())) {
            isOrg = false;
            return;
        }
    }

    @Override
    public <T> Object transferEp(Integer code, String errorMsg) {
        RspDataT rst = new RspDataT(code, errorMsg);

        return transfer(rst);
    }

    @Override
    public <T> Object transfer(RspDataT<T> rspDataT) {
        if (isOrg) {
            return rspDataT;
        }

        return transferToMap(rspDataT);
    }

    @Override
    public <T> Map<String, Object> transferToMap(RspDataT<T> rspDataT) {
        Map<String, Object> mapRst = new HashMap<>(3);

        mapRst.put(rspDataProperties.getCodeKey(), rspDataT.getCode());
        mapRst.put(rspDataProperties.getDataKey(), rspDataT.getResult());
        mapRst.put(rspDataProperties.getErrorMsgKey(), rspDataT.getMessage());

        return mapRst;
    }
}