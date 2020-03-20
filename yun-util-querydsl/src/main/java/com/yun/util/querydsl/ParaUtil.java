package com.yun.util.querydsl;

import com.yun.util.common.CommonException;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-03-01 08:46.
 */

public class ParaUtil {
    public static boolean isStartFromOne = true;

    public static Integer checkPageIndex(Integer pageIndex) {
        if (pageIndex == null) {
            return 1;
        }

        if (pageIndex < 0) {
            throw CommonException.comEp("pageIndex不能小于0");
        }

        if (isStartFromOne) {
            return pageIndex - 1;
        }

        return pageIndex;
    }

    public static Integer checkPageSize(Integer pageSize) {
        if (pageSize == null) {
            return 10;
        }

        if (pageSize <= 0) {
            throw CommonException.comEp("pageSize不能小于或等于0");
        }

        return pageSize;
    }

    public static void checkPagePara(Integer pageIndex, Integer pageSize) {
        if (pageIndex < 0) {
            throw CommonException.comEp("pageIndex不能小于0");
        }

        if (pageSize <= 0) {
            throw CommonException.comEp("pageSize不能小于等于0");
        }
    }

    public static <T> boolean hasItem(List<T> items) {
        if (items == null || items.isEmpty()) {
            return false;
        }

        return true;
    }

    public static <T> boolean noItem(List<T> items) {
        return !hasItem(items);
    }
}
