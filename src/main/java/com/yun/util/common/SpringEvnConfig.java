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
public class SpringEvnConfig {

    @Value("${spring.profiles.active}")
    private String profile;

    private Boolean isProEvn;

    public boolean isProEvn() {
        if (isProEvn == null) {
            isProEvn = "pro".equals(profile);
        }

        return isProEvn;
    }

    public Integer isProValue() {
        return isProEvn ? 1 : 0;
    }
}
