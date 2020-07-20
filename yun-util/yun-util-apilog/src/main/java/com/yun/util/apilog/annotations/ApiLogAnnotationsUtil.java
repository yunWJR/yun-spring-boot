package com.yun.util.apilog.annotations;

import com.yun.util.apilog.ApiLogProperties;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author yun
 * <p>
 * create_time  2020/7/20 13:22.
 */

public class ApiLogAnnotationsUtil {
    private static Map<Method, ApiLogFiled> mtFiledMap = new HashMap<>();

    private static HashSet<Method> noFiledSet = new HashSet<>();

    /**
     * 获取方法是否有ApiLogFiled注解
     * @param mt
     * @return
     */
    public static ApiLogFiled getFiled(Method mt) {
        ApiLogFiled item = mtFiledMap.get(mt);
        if (item != null) {
            return item;
        }

        if (noFiledSet.contains(mt)) {
            return null;
        }

        for (Annotation at : mt.getDeclaredAnnotations()) {
            if (at.annotationType() == ApiLogFiled.class) {

                ApiLogFiled filed = (ApiLogFiled) at;

                mtFiledMap.put(mt, filed);
                return filed;
            }
        }

        noFiledSet.add(mt);

        return null;
    }

    /**
     * 获取ApiLogFiledStatus
     * @param mt
     * @return
     */
    public static ApiLogFiledStatus getFiledStatus(Method mt, ApiLogProperties prop) {
        ApiLogFiled filed = getFiled(mt);

        return new ApiLogFiledStatus(filed, prop);
    }
}
