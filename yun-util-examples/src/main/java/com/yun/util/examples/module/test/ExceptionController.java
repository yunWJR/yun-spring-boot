package com.yun.util.examples.module.test;

import com.yun.util.auth.NoNeedAccessAuth;
import com.yun.util.sb.rsp.RspDataT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yun
 * @createdOn: 2019-04-11 10:16.
 */

@Slf4j
@RestController
@RequestMapping("v1/api/test/exception")
@Api(tags = "00-01-Exception")
public class ExceptionController {

    @Autowired
    private ExceptionServerImpl exceptionServer;

    @GetMapping("RuntimeException")
    @ApiOperation("RuntimeException")
    @NoNeedAccessAuth
    public RspDataT<String> runtimeException() {
        exceptionServer.runtimeException();
        return RspDataT.SurBean("验证通过");
    }

    @GetMapping("RspDataException")
    @ApiOperation("RspDataException")
    @NoNeedAccessAuth
    public RspDataT<String> rspDataException() {
        exceptionServer.rspDataException();

        return RspDataT.SurBean("验证通过");
    }
}