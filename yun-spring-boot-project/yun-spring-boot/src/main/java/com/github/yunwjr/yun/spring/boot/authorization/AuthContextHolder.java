package com.github.yunwjr.yun.spring.boot.authorization;

import com.github.yunwjr.yun.spring.boot.common.ThreadLocalUtil;

/**
 * @author yun
 * created_time 2019-07-02 15:36.
 */

public class AuthContextHolder {

    private final AuthSettingBean settingBean;

    public AuthContextHolder(AuthSettingBean settingBean) {
        this.settingBean = settingBean;
    }

    /**
     * @param auth
     */
    public void saveAuthObj(Object auth) {
        ThreadLocalUtil.put(settingBean.getAuthKey(), auth);
    }

    /**
     * @return
     */
    public Object getAuthObj() {
        return getAuthObj(isThrowEp());
    }

    /**
     * @param throwEp
     * @return
     */
    public Object getAuthObj(boolean throwEp) {
        Object dto = ThreadLocalUtil.get(settingBean.getAuthKey());
        if (dto == null && throwEp) {
            throw AuthException.ComErr("无Token数据");
        }
        return dto;
    }

    /**
     * @param tClass
     * @param throwEp
     * @param <T>
     * @return
     */
    public <T> T getAuthObj(Class<T> tClass, boolean throwEp) {
        return getClassDto(tClass, settingBean.getAuthKey(), throwEp);
    }

    /**
     * @param device
     */
    public void saveDevice(Object device) {
        ThreadLocalUtil.put(settingBean.getDeviceTypeKey(), device);
    }

    /**
     * @return
     */
    public Object getDevice() {
        return getDevice(isThrowEp());
    }

    /**
     * @param tClass
     * @param throwEp
     * @param <T>
     * @return
     */
    public <T> T getDevice(Class<T> tClass, boolean throwEp) {
        return getClassDto(tClass, settingBean.getDeviceTypeKey(), throwEp);
    }

    /**
     * @param throwEp
     * @return
     */
    public Object getDevice(boolean throwEp) {
        Object loginUser = ThreadLocalUtil.get(settingBean.getDeviceTypeKey());
        if (loginUser == null && throwEp) {
            throw AuthException.ComErr("无Device数据");
        }
        return loginUser;
    }

    /**
     *
     */
    public void removeAll() {
        ThreadLocalUtil.removeThreadLocal();
    }

    /**
     * @param tClass
     * @param key
     * @param throwEp
     * @param <T>
     * @return
     */
    public <T> T getClassDto(Class<T> tClass, String key, boolean throwEp) {
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

    /**
     * @return
     */
    private boolean isThrowEp() {
        if (settingBean != null) {
            return settingBean.getThrowEp();
        }

        return false;
    }
}
