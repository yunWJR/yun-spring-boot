package com.yun.util.apilog;

import com.yun.util.common.ThreadLocalUtil;
import org.springframework.http.MediaType;

/**
 * @author yun
 * created_time 2019/8/30 14:02.
 */

public class ApiDataUtil {
    private final static String API_DATA_ADVICE = "API_DATA_ADVICE";

    private final static String API_DATA_INTERCEPTOR = "API_DATA_INTERCEPTOR";

    public static void saveAdviceData(ApiData apiData) {
        ThreadLocalUtil.put(API_DATA_ADVICE, apiData);
    }

    public static ApiData getAdviceData() {
        return getData(API_DATA_ADVICE);
    }

    public static void putAdviceData(String key, Object value) {
        ApiData apiData = getAdviceData();
        if (apiData == null) {
            apiData = new ApiData();
        }

        apiData.getCustomMap().put(key, value);

        saveAdviceData(apiData);
    }

    public static void removeAdviceData() {
        ThreadLocalUtil.remove(API_DATA_ADVICE);
    }

    public static void saveInterceptorData(ApiData apiData) {
        ThreadLocalUtil.put(API_DATA_INTERCEPTOR, apiData);
    }

    public static ApiData getInterceptorData() {
        return getData(API_DATA_INTERCEPTOR);
    }

    public static void removeInterceptorData() {
        ThreadLocalUtil.remove(API_DATA_INTERCEPTOR);
    }

    private static ApiData getData(String key) {
        Object data = ThreadLocalUtil.get(key);
        if (data != null && data instanceof ApiData) {
            ApiData apiData = (ApiData) data;
            return apiData;
        }

        return null;
    }

    /**
     * 判断本次请求的数据类型是否为json
     * @param hd
     * @return boolean
     */
    public static boolean isJsonContent(String hd) {
        if (hd != null) {
            return hd.contains(MediaType.APPLICATION_JSON_VALUE);
        }

        return false;
    }

    public static boolean isUrlencodedContent(String hd) {
        if (hd != null) {
            return hd.contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }

        return false;
    }

    public static boolean canToJson(String header) {
        return isJsonContent(header) || isUrlencodedContent(header);
    }

    public static boolean saveThrowable(Throwable e) {
        boolean save = false;

        ApiData dData = getAdviceData();
        if (dData != null) {
            dData.setThrowable(e);
            saveAdviceData(dData);

            save = true;
        }

        ApiData iData = getInterceptorData();
        if (iData != null) {
            iData.setThrowable(e);
            saveInterceptorData(iData);

            save = true;
        }

        return save;
    }
}
