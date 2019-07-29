package com.yun.util.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2019-07-02 15:37.
 */

public class ThreadLocalUtil {
    /**
     * The constant threadContext.
     */
    private final static ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<Map<String, Object>>() {
        /**
         * Initial value map.
         * @return the map
         */
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(4);
        }
    };

    /**
     * 取得THREAD_LOCAL的实例。
     * @return THREAD_LOCAL的实例
     */
    private static Map<String, Object> getThreadLocal() {
        return THREAD_LOCAL.get();
    }

    /**
     * 使用完后，清除THREAD_LOCAL对应的 value，防止内存泄露
     */
    public static void removeThreadLocal() {
        THREAD_LOCAL.remove();
    }

    /**
     * Put.
     * @param key   the key
     * @param value the value
     */
    public static void put(String key, Object value) {
        getThreadLocal().put(key, value);
    }

    /**
     * Get object.
     * @param key the key
     * @return the object
     */
    public static Object get(String key) {
        return getThreadLocal().get(key);
    }

    /**
     * Remove object.
     * @param key the key
     * @return the object
     */
    public static Object remove(String key) {
        return getThreadLocal().remove(key);
    }

    /**
     * Remove all
     */
    public static void remove() {
        getThreadLocal().clear();
    }
}
