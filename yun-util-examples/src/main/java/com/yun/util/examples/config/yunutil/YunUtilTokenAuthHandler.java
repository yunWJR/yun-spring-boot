package com.yun.util.examples.config.yunutil;

import com.yun.util.authorization.AuthHandler;
import com.yun.util.common.StringUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yun
 * created_time 2019/11/7 17:22.
 */

@Component
public class YunUtilTokenAuthHandler implements AuthHandler {

    /**
     * 检查 token 是否有效
     * @param authStr
     * @param request
     * @return
     */
    @Override
    public Object checkUser(String authStr, HttpServletRequest request) {
        if (StringUtil.hasCtn(authStr) && !"none".equals(authStr)) {
            return new Object();
        }

        return null;
    }

}
