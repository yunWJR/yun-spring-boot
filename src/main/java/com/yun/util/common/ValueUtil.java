package com.yun.util.common;

/**
 * @author: yun
 * @createdOn: 2018/7/26 15:25.
 */

public class ValueUtil {
    public static boolean isNullOrZeroLong(Long value) {
        if (value == null) {
            return true;
        }

        if (value == 0) {
            return true;
        }

        return false;
    }

    public static boolean isNullOrZeroInteger(Integer value) {
        if (value == null) {
            return true;
        }

        if (value == 0) {
            return true;
        }

        return false;
    }
}
