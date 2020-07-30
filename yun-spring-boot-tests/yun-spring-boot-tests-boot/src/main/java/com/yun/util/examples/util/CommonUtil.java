package com.yun.util.examples.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author yun
 * created_time 2019-03-19 16:40.
 */

public class CommonUtil {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>(); // stream 是多线程，需要线程安全
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; // null-key 不存在
    }

    public static Integer getInt(String str) {
        if (str != null && str.length() > 0) {
            try {
                Integer vl = Integer.valueOf(str);

                return vl;
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public static Long getLong(String str) {
        if (str != null && str.length() > 0) {
            try {
                Long vl = Long.valueOf(str);

                return vl;
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
