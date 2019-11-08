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
        ThreadLocalUtil.put(AuthPropertyUtil.prop().getTokenAuthKey(), dto);
    }

    public static Object getTokenDto() {
        return getTokenDto(isThrowEp());
    }

    public static Object getTokenDto(boolean throwEp) {
        Object dto = ThreadLocalUtil.get(AuthPropertyUtil.prop().getTokenAuthKey());
        if (dto == null && throwEp) {
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.NoToken);
        }
        return dto;
    }

    public static void saveAccessDto(Object dto) {
        ThreadLocalUtil.put(AuthPropertyUtil.prop().getAccessAuthKey(), dto);
    }

    public static Object getAccessDto() {
        return getAccessDto(isThrowEp());
    }

    public static Object getAccessDto(boolean throwEp) {
        Object loginUser = ThreadLocalUtil.get(AuthPropertyUtil.prop().getAccessAuthKey());
        if (loginUser == null && throwEp) {
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.NoAccessExp);
        }
        return loginUser;
    }

    public static void saveDeviceDto(Object dto) {
        ThreadLocalUtil.put(AuthPropertyUtil.prop().getDeviceTypeKey(), dto);
    }

    public static Object getDeviceDto() {
        return getDeviceDto(isThrowEp());
    }

    public static Object getDeviceDto(boolean throwEp) {
        Object loginUser = ThreadLocalUtil.get(AuthPropertyUtil.prop().getDeviceTypeKey());
        if (loginUser == null && throwEp) {
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.NoDeviceExp);
        }
        return loginUser;
    }

    public static void removeAll() {
        ThreadLocalUtil.removeThreadLocal();
    }

    private static boolean isThrowEp() {
        if (AuthPropertyUtil.prop() != null) {
            return AuthPropertyUtil.prop().getThrowEp();
        }

        return false;
    }
}
