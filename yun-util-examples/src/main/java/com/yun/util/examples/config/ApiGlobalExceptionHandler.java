package com.yun.util.examples.config;

import com.yun.util.module.config.GlobalExceptionHandler;
import com.yun.util.module.rsp.RspDataException;
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

@Slf4j
@RestControllerAdvice //自动继承
public class ApiGlobalExceptionHandler extends GlobalExceptionHandler {

    @Override
    public RspDataT handleRspDataExceptionPre(RspDataException e) {
        return new RspDataT();
    }

    /**
     * Hibernate 抛出的参数验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RspDataT ConstraintViolationException(ConstraintViolationException e) {
        logError("ConstraintViolationException", e);

        if (isProEvn()) {
            return new RspDataT(-1, super.isDetailsInProEvn ? e.getMessage() : "参数异常");
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

        return new RspDataT(-1, errMsg);
    }

    // endregion
}
