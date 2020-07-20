package com.yun.util.auth;

import com.yun.util.common.ThreadLocalUtil;

/**
 * @author yun
 * created_time 2019-07-02 15:36.
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
            throw AuthException.ComErr("无Token数据");
        }
        return dto;
    }

    public static <T> T getTokenDto(Class<T> tClass, boolean throwEp) {
        return getClassDto(tClass, AuthPropertyUtil.prop().getTokenAuthKey(), throwEp);
    }

    public static void saveAccessDto(Object dto) {
        ThreadLocalUtil.put(AuthPropertyUtil.prop().getAccessAuthKey(), dto);
    }

    public static Object getAccessDto() {
        return getAccessDto(isThrowEp());
    }

    public static <T> T getAccessDto(Class<T> tClass, boolean throwEp) {
        return getClassDto(tClass, AuthPropertyUtil.prop().getAccessAuthKey(), throwEp);
    }

    public static Object getAccessDto(boolean throwEp) {
        Object loginUser = ThreadLocalUtil.get(AuthPropertyUtil.prop().getAccessAuthKey());
        if (loginUser == null && throwEp) {
            throw AuthException.ComErr("无Access数据");
        }
        return loginUser;
    }

    public static void saveDeviceDto(Object dto) {
        ThreadLocalUtil.put(AuthPropertyUtil.prop().getDeviceTypeKey(), dto);
    }

    public static Object getDeviceDto() {
        return getDeviceDto(isThrowEp());
    }

    public static <T> T getDeviceDto(Class<T> tClass, boolean throwEp) {
        return getClassDto(tClass, AuthPropertyUtil.prop().getDeviceTypeKey(), throwEp);
    }

    public static Object getDeviceDto(boolean throwEp) {
        Object loginUser = ThreadLocalUtil.get(AuthPropertyUtil.prop().getDeviceTypeKey());
        if (loginUser == null && throwEp) {
            throw AuthException.ComErr("无Device数据");
        }
        return loginUser;
    }

    public static void removeAll() {
        ThreadLocalUtil.removeThreadLocal();
    }

    public static <T> T getClassDto(Class<T> tClass, String key, boolean throwEp) {
        Object dto = ThreadLocalUtil.get(key);

        if (dto != null) {
            if (dto.getClass() == tClass) {
                return (T) dto;
            }
        }

        if (dto == null && throwEp) {
            throw AuthException.ComErr(String.format("无%s数据", key));
        }

        return null;
    }

    private static boolean isThrowEp() {
        if (AuthPropertyUtil.prop() != null) {
            return AuthPropertyUtil.prop().getThrowEp();
        }

        return false;
    }
}
