package com.yun.util.common;

import java.math.BigDecimal;

/**
 * 验证工具
 * @author yun
 * created_time 2019-07-12 13:36.
 */

public class VerifyUtil {

    /**
     * 判断不能位负数
     * @param bd
     * @param ep
     * @return
     */
    public static boolean isNotNegative(BigDecimal bd, boolean ep) {
        boolean isValid = false;

        if (bd == null) {
            if (ep) {
                throw new CommonException(-1, "对象不存在");
            }

            return isValid;
        }

        isValid = bd.compareTo(BigDecimal.ZERO) >= 0;

        if (!isValid && ep) {
            throw new CommonException(-1, "不能为负数");
        }

        return isValid;
    }

    /**
     * 判断不能位负数
     * @param bd
     * @return
     */
    public static boolean isNotNegative(BigDecimal bd) {
        return isNotNegative(bd, false);
    }

    /**
     * 0 或 null
     * @param value
     * @return
     */
    public static boolean isNullOrZeroLong(Long value) {
        if (value == null) {
            return true;
        }

        if (value == 0) {
            return true;
        }

        return false;
    }

    /**
     * 0 或 null
     * @param value
     * @return
     */
    public static boolean isNullOrZeroInteger(Integer value) {
        if (value == null) {
            return true;
        }

        if (value == 0) {
            return true;
        }

        return false;
    }

    /**
     * null 或 无内容
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }

        if (str.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * @param str
     * @return
     */
    public static boolean hasCtn(String str) {
        return !isNullOrEmpty(str);
    }
}
