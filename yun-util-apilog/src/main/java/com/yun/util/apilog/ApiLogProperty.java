package com.yun.util.apilog;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019/9/9 14:39.
 */

@Component
@ConfigurationProperties(prefix = "yun.apilog")
public class ApiLogProperty {
    private Index index = new Index();

    private String prefix = "api_data";

    private String msg = "api data";

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

    public static class Index {
        private boolean startTime = false;

        private boolean endTime = false;

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
    }
}
