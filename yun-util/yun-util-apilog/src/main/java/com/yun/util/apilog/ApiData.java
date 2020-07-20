package com.yun.util.apilog;

import com.yun.util.apilog.annotations.ApiLogFiled;
import com.yun.util.apilog.annotations.ApiLogFiledStatus;
import com.yun.util.common.JsonUtil;
import com.yun.util.common.ThrowableUtil;
import org.springframework.http.server.ServerHttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 记录的 api 数据
 * @author yun
 * created_time 2019/8/29 14:40.
 */

public class ApiData {

    private ApiLogFiledStatus status;

    // region --Field

    private long startTime;

    private long endTime;

    private long costTime;

    private String host;

    private String url;

    private String query;

    private String account;

    private String version;

    private String deviceType;

    private String deviceInfo;

    private Throwable throwable;

    private String header;

    private String body;

    private String response;

    private Map customMap = new HashMap();

    // endregion

    // region --Constructor

    public ApiData() {
        this.startTime = System.currentTimeMillis();
    }

    // public ApiData(HttpServletRequest request) {
    //     this.startTime = System.currentTimeMillis();
    //
    //     this.url = request.getMethod() + " " + request.getRequestURI();
    //
    //     this.query = request.getQueryString();
    //
    //     Enumeration<String> names = request.getHeaderNames();
    //     Map<String, String> headerMap = new HashMap<>();
    //     //输出所有的头信息的名字
    //     while (names.hasMoreElements()) {
    //         //获取request 的请求头名字name
    //         String name = names.nextElement();
    //         //根据头名字获取对应的值
    //         String value = request.getHeader(name);
    //         //输出：
    //         headerMap.put(name, value);
    //     }
    //
    //     this.header = JsonUtil.toStr(headerMap);
    //
    //     try {
    //         this.body = new RequestWrapper(request).getBodyString();
    //     } catch (Exception e) {
    //         this.body = "error body";
    //     }
    // }

    public static ApiData newItem() {
        ApiData apiData = new ApiData();

        // -1为错误数据，没有记录到开始时间
        apiData.setStartTime(-1);

        return apiData;
    }

    public void updateEndTime() {
        this.endTime = System.currentTimeMillis();

        // -1无开始时间记录
        if (this.startTime == -1) {
            this.costTime = -1;
            return;
        }

        this.costTime = this.endTime - this.startTime;
    }

    public void updateHttp(ServerHttpRequest request, ApiLogFiled filed) {
        if (request == null) {
            return;
        }

        if (request.getURI() != null) {
            if (request.getMethod() == null) {
                this.host = request.getURI().getHost();
            } else {
                this.host = request.getMethod() + " " + request.getURI().getHost();
            }

            this.url = request.getURI().getPath();

            this.query = request.getURI().getQuery();
        }

        if (request.getHeaders() != null && (filed == null || filed.header())) {
            this.header = JsonUtil.toStr(request.getHeaders().entrySet());
        }

        // filed
        if (filed != null && !filed.requestBody()) {
            this.body = "disable";
        }
        if (filed != null && !filed.responseBody()) {
            this.response = "disable";
        }
        if (filed != null && !filed.header()) {
            this.header = "disable";
        }

        updateEndTime();
    }

    public Map getLogMap(ApiLogProperties prop) {
        Map map = new HashMap(customMap.size() + 20);
        if (prop.getIndex().isStartTime()) {
            map.put("startTime", startTime);
        }
        if (prop.getIndex().isEndTime()) {
            map.put("endTime", endTime);
        }
        if (prop.getIndex().isCostTime()) {
            map.put("costTime", costTime);
        }
        if (prop.getIndex().isHost()) {
            map.put("host", host);
        }
        if (prop.getIndex().isUrl()) {
            map.put("url", url);
        }
        if (prop.getIndex().isQuery()) {
            map.put("query", query);
        }
        if (prop.getIndex().isAccount()) {
            map.put("account", account);
        }
        if (prop.getIndex().isVersion()) {
            map.put("version", version);
        }
        if (prop.getIndex().isDeviceType()) {
            map.put("deviceType", deviceType);
        }
        if (prop.getIndex().isDeviceInfo()) {
            map.put("deviceInfo", deviceInfo);
        }
        if (prop.getIndex().isHeader()) {
            map.put("header", header);
        }
        if (prop.getIndex().isBody()) {
            map.put("body", body);
        }
        if (prop.getIndex().isResponse()) {
            map.put("response", response);
        }
        if (throwable != null && prop.getIndex().isThrowable()) {
            map.put("throwable", ThrowableUtil.getStack(throwable));
        }

        map.putAll(customMap);

        return map;
    }

    // endregion

    public boolean isErrorData() {
        return throwable != null;
    }

    // region --Getter and Setter

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Map getCustomMap() {
        return customMap;
    }

    public void setCustomMap(Map customMap) {
        this.customMap = customMap;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public ApiLogFiledStatus getStatus() {
        return status;
    }

    public void setStatus(ApiLogFiledStatus status) {
        this.status = status;
    }

    // endregion
}