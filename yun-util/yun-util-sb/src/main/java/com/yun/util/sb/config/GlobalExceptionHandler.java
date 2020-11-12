package com.yun.util.sb.config;

import com.yun.util.apilog.ApiDataUtil;
import com.yun.util.common.CommonException;
import com.yun.util.common.SpringEvn;
import com.yun.util.common.ThrowableUtil;
import com.yun.util.sb.rsp.RspData;
import com.yun.util.sb.rsp.RspDataCodeType;
import com.yun.util.sb.rsp.RspDataException;
import com.yun.util.sb.rsp.RspDataT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局的的异常拦截器
 * @author yun
 * created_time 2019-02-25 13:23.
 */
@RestControllerAdvice  // todo 由客户端继承后使用
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public boolean isDetailsInProEvn = true;

    @Autowired
    private SpringEvn springEvn;

    // // TODO: 2020/11/12 yun  暂未实现
    // @Autowired(required = false)
    // private RspDataTransfer rspDataTransfer;

    public boolean isProEvn() {
        return springEvn.isProEvn();
    }

    /**
     * 参数非法异常.
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Object handleIllegalArgumentException(IllegalArgumentException e) {
        logError("IllegalArgumentException", e);

        Object rst = handleIllegalArgumentExceptionPre(e);
        if (rst != null) {
            return rst;
        }

        if (isProEvn()) {
            return rstObj(RspDataCodeType.ComErr.getCode(), isDetailsInProEvn ? e.getMessage() : "参数异常");
        }

        return rstObj(RspDataCodeType.ComErr.getCode(), e.getMessage());
    }

    public Object handleIllegalArgumentExceptionPre(IllegalArgumentException e) {
        return null;
    }

    /**
     * RspDataException 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Object handleCommonException(CommonException e) {
        logError("CommonException", e);

        Object rst = handleCommonExceptionPre(e);
        if (rst != null) {
            return rst;
        }

        return rstObj(e.getCode(), e.getMessage());
    }

    public Object handleCommonExceptionPre(CommonException e) {
        return null;
    }

    /**
     * RspDataException 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(RspDataException.class)
    @ResponseBody
    public Object handleRspDataException(RspDataException e) {
        logError("RspDataException", e);

        Object rst = handleRspDataExceptionPre(e);
        if (rst != null) {
            return rst;
        }

        if (e.getRst() != null) {
            return e.getRst();
        }

        return rstObj(RspDataCodeType.ComErr.getCode(), e.getMessage());
    }

    public Object handleRspDataExceptionPre(RspDataException e) {
        return null;
    }

    /**
     * 处理通用异常
     * @param e the e
     * @return the base rst bean
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        logError("Exception", e);

        Object rst = handleExceptionPre(e);
        if (rst != null) {
            return rst;
        }

        if (isProEvn()) {
            return rstObj(RspDataCodeType.ComErr.getCode(), isDetailsInProEvn ? e.getMessage() : "异常");
        }

        String errMsg = this.getExceptionMsg(e);

        return RspDataT.ComErrBean(errMsg);
    }

    public Object handleExceptionPre(Exception e) {
        return null;
    }

    /**
     * 处理所有接口数据验证异常（@valid）
     * @param e the e
     * @return the base rst bean
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        logError("MethodArgumentNotValidException", e);

        Object rst = handleMethodArgumentNotValidExceptionPre(e, req);
        if (rst != null) {
            return rst;
        }

        if (isProEvn()) {
            return rstObj(RspDataCodeType.ComErr.getCode(), isDetailsInProEvn ? e.getMessage() : "参数验证失败");
        }

        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuilder errorStr = new StringBuilder("参数不合法：");
        for (FieldError error : errors) {
            String err = error.getField() + ":" + error.getDefaultMessage();
            errorStr.append(err).append(";");
        }

        return rstObj(RspDataCodeType.ComErr.getCode(), errorStr.toString());
    }

    public Object handleMethodArgumentNotValidExceptionPre(MethodArgumentNotValidException e, HttpServletRequest req) {
        return null;
    }

    // region -- method

    public String getExceptionMsg(Exception e) {
        String errMsg = null;
        if (e.getCause() != null) {
            errMsg = "msg:" + e.getCause().getMessage() + " locMsg:" + e.getCause().getLocalizedMessage();
        } else {
            errMsg = "msg:" + e.getMessage() + " locMsg:" + e.getLocalizedMessage();
        }

        // errMsg = e.toString();

        return errMsg;
    }

    public String getLogFileExceptionMsg(Exception e) {
        return e.getMessage() + "\n" + ThrowableUtil.getStack(e);
    }

    public void logError(String title, Throwable e) {
        if (!ApiDataUtil.saveThrowable(e)) {
            log.error(title == null ? "error" : title, e);
        }
    }

    private Object rstObj(Integer code, String errorMsg) {
        RspData rsp = new RspData(code, errorMsg);

        // if (rspDataTransfer != null) {
        //     return rspDataTransfer.transferEp(code, errorMsg);
        // }

        return rsp;
    }

    // endregion
}
