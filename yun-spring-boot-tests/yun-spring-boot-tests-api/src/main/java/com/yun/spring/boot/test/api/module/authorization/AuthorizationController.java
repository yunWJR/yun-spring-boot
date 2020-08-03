package com.yun.spring.boot.test.api.module.authorization;

import com.github.yunwjr.yun.spring.boot.authorization.AuthStatus;
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
@RequestMapping("v1/api/authorization")
@Api(tags = "00-02-authorization")
public class AuthorizationController {

    @GetMapping("AuthStatusNull")
    @ApiOperation("AuthStatusNull")
    public Object AuthStatusNull() {
        return "AuthStatusNull";
    }

    @GetMapping("AuthStatus")
    @ApiOperation("AuthStatus")
    @AuthStatus
    public Object AuthStatus() {
        return "AuthStatus";
    }

    @GetMapping("AuthStatusFalse")
    @ApiOperation("AuthStatusFalse")
    @AuthStatus(required = false)
    public Object AuthStatusFalse() {
        return "AuthStatusFalse";
    }
}