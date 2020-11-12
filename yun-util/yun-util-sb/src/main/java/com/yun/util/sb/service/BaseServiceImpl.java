package com.yun.util.sb.service;

import com.yun.util.authorization.AuthContextHolder;
import com.yun.util.common.StringUtil;
import com.yun.util.sb.rsp.RspDataException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service 封装
 * @author yun
 * created_time 2019/9/7 22:34.
 */

public class BaseServiceImpl {

    @Autowired
    private AuthContextHolder authContextHolder;

    // region -- auth

    public <T> T getValidTokenUser() {
        return getTokenUser(false);
    }

    public <T> T getTokenUser(boolean allowNull) {
        Object obj = authContextHolder.getAuthObj(false);
        if (obj == null) {
            if (allowNull) {
                return null;
            }

            throw commonEp("无用户信息");
        }

        return (T) obj;
    }

    public <T> T getValidDevice() {
        return getDevice(false);
    }

    public <T> T getDevice(boolean allowNull) {
        Object obj = authContextHolder.getDevice(false);
        if (obj == null) {
            if (allowNull) {
                return null;
            }

            throw commonEp("无Access信息");
        }

        return (T) obj;
    }

    // endregion

    // region -- Exception

    public void throwCommonError(String error) {
        throw commonEp(error);
    }

    public RspDataException commonEp(String errMsg) {
        return RspDataException.RstComErrBeanWithStr(errMsg);
    }

    public void handleCheckRst(String rst) {
        if (!StringUtil.isNullOrEmpty(rst)) {
            throw commonEp(rst);
        }
    }

    // endregion
}