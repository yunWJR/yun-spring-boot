package com.yun.util.common;

/**
 * @author: yun
 * @createdOn: 2018/6/8 09:08.
 */

public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }

        if (str.length() == 0) {
            return true;
        }

        return false;
    }

    public static boolean hasCtn(String str) {
        return !isNullOrEmpty(str);
    }
}
