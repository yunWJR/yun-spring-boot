package com.yun.util;

import com.yun.util.auth.AuthProperty;
import com.yun.util.common.SpringEvn;
import com.yun.util.swagger.SwaggerPara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yun
 * created_time 2020/3/19 10:03.
 */

@Component
public class SwaggerParaImpl implements SwaggerPara {
    @Autowired
    private SpringEvn springEvn;

    @Autowired
    private AuthProperty authProperty;

    @Override
    public boolean isProEvn() {
        return springEvn.isProEvn();
    }

    @Override
    public String getTokenAuthKey() {
        return authProperty.getTokenAuthKey();
    }

    @Override
    public String getDeviceTypeKey() {
        return authProperty.getDeviceTypeKey();
    }
}
