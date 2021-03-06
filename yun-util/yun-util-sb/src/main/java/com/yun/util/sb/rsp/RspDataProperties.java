package com.yun.util.sb.rsp;

import com.yun.util.base.PropertyDefine;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun
 * created_time 2019/9/9 14:39.
 */

@Component
@ConfigurationProperties(prefix = RspDataProperties.PREFIX)
public class RspDataProperties {
    // 不能用驼峰，用-分隔
    public static final String PREFIX = PropertyDefine.BASE_PREFIX + ".response-data";

    private String codeKey = "code";
    private String dataKey = "result";
    private String errorMsgKey = "message";

    private List<KeyName> other = new ArrayList<>();

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getErrorMsgKey() {
        return errorMsgKey;
    }

    public void setErrorMsgKey(String errorMsgKey) {
        this.errorMsgKey = errorMsgKey;
    }

    public List<KeyName> getOther() {
        return other;
    }

    public void setOther(List<KeyName> other) {
        this.other = other;
    }

    public static class KeyName {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
