package com.yun.util.examples.module.test;

import com.yun.util.authorization.AuthStatus;
import com.yun.util.common.SpringEvn;
import com.yun.util.sb.rsp.RspDataT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yun
 * created_time 2019-04-11 10:16.
 */

@Slf4j
@RestController
@RequestMapping("v1/api/test")
@Api(tags = "00-09-测试模块")
public class TestController {

    @Autowired
    private SpringEvn springEvn;

    @GetMapping("checkAuth")
    @ApiOperation("检查权限")
    public RspDataT<String> checkAuth() {
        return RspDataT.SurBean("验证通过");
    }

    @GetMapping("checkValid")
    @ApiOperation("检查服务是否正常")
    @AuthStatus(required = false)
    public RspDataT<String> checkValid() {
        // if (isTestEnv()) {
        //     return null;
        // }

        // 测试数据库
        // configParaJrp.findFirstByTypeAndIsProduction(0, 0);
        //
        // boolean limit = redisLimit.checkLimit();

        return RspDataT.SurBean("验证通过");
    }

    @GetMapping("checkDur")
    @ApiOperation("耗时测试-测试用")
    @AuthStatus(required = false)
    // @Transactional
    public RspDataT<String> checkDur() {
        if (isTestEnv()) {
            return null;
        }

        Long sT = System.currentTimeMillis();

        // configParaJrp.findAll();

        Long eT = System.currentTimeMillis();

        return RspDataT.SurBean("耗时：" + (eT - sT) + ":");
    }

    @PostMapping("checkDto")
    @ApiOperation("参数校验-测试用")
    @AuthStatus(required = false)
    public RspDataT<CheckValidDTO> checkDto(@RequestBody(required = true) @Valid CheckValidDTO data
    ) {
        if (isTestEnv()) {
            return null;
        }

        return RspDataT.SurBean(data);
    }

    private boolean isTestEnv() {
        if (springEvn.isProEvn()) {
            return true;
        }

        return false;
    }
}