package com.yun.util.examples.config;

import com.yun.util.module.config.GlobalExceptionHandler;
import com.yun.util.module.rsp.RspDataT;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局的的异常拦截器
 * @author: yun
 * @createdOn: 2019-02-25 13:23.
 */

// @Component
@Slf4j
@RestControllerAdvice //自动继承
public class ApiGlobalExceptionHandler extends GlobalExceptionHandler {

    // TODO: 2020/1/8 无效
    @Override
    public boolean isDetailsInProEvn() {
        return true;
    }

    /**
     * Hibernate 抛出的参数验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RspDataT ConstraintViolationException(ConstraintViolationException e) {
        log.error(this.getLogFileExceptionMsg(e));

        if (isProEvn()) {
            return new RspDataT(-1, isDetailsInProEvn() ? e.getMessage() : "参数异常");
        }

        String errMsg = null;

        Object[] objs = e.getConstraintViolations().toArray();
        if (objs != null && objs.length > 0) {
            // todo 其他类型
            ConstraintViolationImpl obj = (ConstraintViolationImpl) objs[0];

            if (obj != null) {
                errMsg = String.format("参数(%s) 错误：%s", obj.getPropertyPath(), obj.getMessage());
            }
        }

        if (errMsg == null) {
            errMsg = "参数异常";
        }

        log.error("参数非法异常={}", e.getMessage(), e);
        return new RspDataT(-1, errMsg);
    }

    // endregion
}
