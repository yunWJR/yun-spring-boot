package com.yun.util.apilog;

import com.yun.util.base.PropertyDefine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yun
 * created_time 2019/9/9 14:39.
 */

@Component
@ConfigurationProperties(prefix = ApiLogProperties.PREFIX)
public class ApiLogProperties {
    public static final String PREFIX = PropertyDefine.BASE_PREFIX + ".apilog";

    /**
     * 各项
     */
    private Index index = new Index();

    /**
     * 日志前缀
     */
    private String prefix = "api_data";

    /**
     * 消息体标题
     */
    private String msg = "api data";

    /**
     * 心跳信号时间间隔，默认60s
     */
    private String heartRate = "60000";

    /**
     * 不记录日志
     */
    private boolean ignoreLog = false;

    /**
     * 是否记录 header
     * @return
     */
    private boolean header = true;

    /**
     * 是否记录 requestBody
     * @return
     */
    private boolean requestBody = true;

    /**
     * 是否记录 responseBody
     * @return
     */
    private boolean responseBody = true;

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public boolean isIgnoreLog() {
        return ignoreLog;
    }

    public void setIgnoreLog(boolean ignoreLog) {
        this.ignoreLog = ignoreLog;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public boolean isRequestBody() {
        return requestBody;
    }

    public void setRequestBody(boolean requestBody) {
        this.requestBody = requestBody;
    }

    public boolean isResponseBody() {
        return responseBody;
    }

    public void setResponseBody(boolean responseBody) {
        this.responseBody = responseBody;
    }

    public static class Index {
        private boolean startTime = true;

        private boolean endTime = true;

        private boolean costTime = true;

        private boolean host = true;

        private boolean url = true;

        private boolean query = true;

        private boolean account = true;

        private boolean version = true;

        private boolean deviceType = true;

        private boolean deviceInfo = true;

        private boolean header = true;

        private boolean body = true;

        private boolean response = true;

        private boolean throwable = true;

        public boolean isStartTime() {
            return startTime;
        }

        public void setStartTime(boolean startTime) {
            this.startTime = startTime;
        }

        public boolean isEndTime() {
            return endTime;
        }

        public void setEndTime(boolean endTime) {
            this.endTime = endTime;
        }

        public boolean isCostTime() {
            return costTime;
        }

        public void setCostTime(boolean costTime) {
            this.costTime = costTime;
        }

        public boolean isHost() {
            return host;
        }

        public void setHost(boolean host) {
            this.host = host;
        }

        public boolean isUrl() {
            return url;
        }

        public void setUrl(boolean url) {
            this.url = url;
        }

        public boolean isQuery() {
            return query;
        }

        public void setQuery(boolean query) {
            this.query = query;
        }

        public boolean isAccount() {
            return account;
        }

        public void setAccount(boolean account) {
            this.account = account;
        }

        public boolean isVersion() {
            return version;
        }

        public void setVersion(boolean version) {
            this.version = version;
        }

        public boolean isDeviceType() {
            return deviceType;
        }

        public void setDeviceType(boolean deviceType) {
            this.deviceType = deviceType;
        }

        public boolean isDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(boolean deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public boolean isHeader() {
            return header;
        }

        public void setHeader(boolean header) {
            this.header = header;
        }

        public boolean isBody() {
            return body;
        }

        public void setBody(boolean body) {
            this.body = body;
        }

        public boolean isResponse() {
            return response;
        }

        public void setResponse(boolean response) {
            this.response = response;
        }

        public boolean isThrowable() {
            return throwable;
        }

        public void setThrowable(boolean throwable) {
            this.throwable = throwable;
        }
    }
}
