package com.yun.spring.boot.test.api.module.swagger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author yun
 * created_time 2019-03-29 18:31.
 */

@Data
public class CheckValidDTO {

    @NotNull
    @DecimalMin("0")
    @Digits(integer = 6, fraction = 2)
    @ApiModelProperty("付款金额")
    private BigDecimal bigDecimal;
}
