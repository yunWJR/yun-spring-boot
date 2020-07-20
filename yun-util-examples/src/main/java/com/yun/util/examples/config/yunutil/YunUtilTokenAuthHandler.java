package com.yun.util.examples.config.yunutil;

import com.yun.util.auth.TokenAuthHandler;
import com.yun.util.common.StringUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yun
 * created_time 2019/11/7 17:22.
 */

@Component
public class YunUtilTokenAuthHandler implements TokenAuthHandler {

    /**
     * 检查 token 是否有效
     * @param tokenStr
     * @param request
     * @return
     */
    @Override
    public Object checkUser(String tokenStr, HttpServletRequest request) {
        if (StringUtil.hasCtn(tokenStr) && !"none".equals(tokenStr)) {
            return new Object();
        }

        return null;
    }

    /**
     * 保存额外的请求参数
     * @param request
     */
    @Override
    public void savePara(HttpServletRequest request) {
        // String appIdStr = request.getHeader(GlobalDefine.AppIdHeaderName);
        // if (!StringUtil.isNullOrEmpty(appIdStr)) {
        //     Long appId = Long.valueOf(appIdStr);
        //
        //     if (appId != null) {
        //         ThreadLocalUtil.put(GlobalDefine.AppIdHeaderName, appId);
        //     }
        // }
    }
}
