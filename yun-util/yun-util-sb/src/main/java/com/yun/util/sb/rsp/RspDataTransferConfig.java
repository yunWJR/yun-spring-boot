package com.yun.util.sb.rsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yun
 * @createdOn: 2019/8/30 11:02.
 */

@Configuration
public class RspDataTransferConfig {
    @Autowired
    private RspDataProperties prop;

    @Bean
    @ConditionalOnMissingBean
    RspDataTransfer rspDataTransfer() {
        return new RspDataTransferImpl(prop);
    }
}
