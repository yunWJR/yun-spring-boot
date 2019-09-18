package com.yun.util.auth;

import com.yun.util.common.ThreadLocalUtil;
import com.yun.util.module.rsp.RspDataCodeType;
import com.yun.util.module.rsp.RspDataException;

/**
 * @author: yun
 * @createdOn: 2019-07-02 15:36.
 */

public class AuthDtoUtil {
    public static void saveTokenDto(Object dto) {
        ThreadLocalUtil.put(AuthPropertyUtil.instance.getObj().getTokenAuthKey(), dto);
    }

    public static Object getTokenDto() {
        return getTokenDto(isThrowEp());
    }

    public static Object getTokenDto(boolean throwEp) {
        Object dto = ThreadLocalUtil.get(AuthPropertyUtil.instance.getObj().getTokenAuthKey());
        if (dto == null && throwEp) {
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.NoToken);
        }
        return dto;
    }

    public static void saveAccessDto(Object dto) {
        ThreadLocalUtil.put(AuthPropertyUtil.instance.getObj().getAccessAuthKey(), dto);
    }

    public static Object getAccessDto() {
        return getAccessDto(isThrowEp());
    }

    public static Object getAccessDto(boolean throwEp) {
        Object loginUser = ThreadLocalUtil.get(AuthPropertyUtil.instance.getObj().getAccessAuthKey());
        if (loginUser == null && throwEp) {
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.NoAccessExp);
        }
        return loginUser;
    }

    public static void saveDeviceDto(Object dto) {
        ThreadLocalUtil.put(AuthPropertyUtil.instance.getObj().getDeviceTypeKey(), dto);
    }

    public static Object getDeviceDto() {
        return getDeviceDto(isThrowEp());
    }

    public static Object getDeviceDto(boolean throwEp) {
        Object loginUser = ThreadLocalUtil.get(AuthPropertyUtil.instance.getObj().getDeviceTypeKey());
        if (loginUser == null && throwEp) {
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.NoDeviceExp);
        }
        return loginUser;
    }

    public static void removeAll() {
        ThreadLocalUtil.removeThreadLocal();
    }

    private static boolean isThrowEp() {
        if (AuthPropertyUtil.instance.getObj() != null) {
            return AuthPropertyUtil.instance.getObj().getThrowEp();
        }

        return false;
    }
}
