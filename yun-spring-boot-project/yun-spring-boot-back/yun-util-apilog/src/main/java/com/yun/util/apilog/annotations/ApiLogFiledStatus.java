package com.yun.util.apilog.annotations;

import com.yun.util.apilog.ApiLogProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yun
 * <p>
 * create_time  2020/7/20 13:41.
 */

@Data
@NoArgsConstructor
public class ApiLogFiledStatus {
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

    public ApiLogFiledStatus(ApiLogFiled filed) {
        if (filed == null) {
            return;
        }

        ignoreLog = filed.ignoreLog();

        header = filed.header() && !ignoreLog;
        requestBody = filed.requestBody() && !ignoreLog;
        responseBody = filed.responseBody() && !ignoreLog;
    }

    public ApiLogFiledStatus(ApiLogFiled filed, ApiLogProperties prop) {
        // prop
        ignoreLog = prop.isIgnoreLog();
        header = prop.isHeader() && !ignoreLog;
        requestBody = prop.isRequestBody() && !ignoreLog;
        responseBody = prop.isResponseBody() && !ignoreLog;

        if (filed == null) {
            return;
        }

        // filed 覆盖
        ignoreLog = filed.ignoreLog();
        header = filed.header() && !ignoreLog;
        requestBody = filed.requestBody() && !ignoreLog;
        responseBody = filed.responseBody() && !ignoreLog;
    }
}
