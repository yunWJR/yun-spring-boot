package com.yun.util.sb.rsp;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: yun
 * @createdOn: 2020/3/20 16:41.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RspDataProperties.class, RspDataTransferConfig.class})
public @interface EnableRspDataTransfer {
}
