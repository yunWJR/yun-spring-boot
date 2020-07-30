package com.yun.util.examples.module.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yun
 * created_time 2019-04-11 10:16.
 */

@Slf4j
@RestController
@RequestMapping("v1/api/test")
@Api(tags = "00-09-测试模块")
public class TestController {

    @GetMapping("checkAuth")
    @ApiOperation("检查权限")
    public Object checkAuth() {
        return "验证通过";
    }
}