package com.yun.util.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019-04-09 15:51.
 */

@Component
@Data
public class SpringEvnImpl implements SpringEvn {

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${yun.evn.pro_name}")
    private String proName = "pro";

    private Boolean isProEvn;

    @Override
    public boolean isProEvn() {
        if (StringUtil.isNullOrEmpty(proName)) {
            proName = "pro";
        }

        if (isProEvn == null) {
            isProEvn = proName.equals(profile);
        }

        return isProEvn;
    }

    @Override
    public Integer isProValue() {
        return isProEvn ? 1 : 0;
    }
}
