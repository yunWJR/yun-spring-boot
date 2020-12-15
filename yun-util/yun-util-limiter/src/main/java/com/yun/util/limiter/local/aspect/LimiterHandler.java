package com.yun.util.limiter.local.aspect;

import com.yun.util.limiter.LimiterException;
import com.yun.util.limiter.local.BaseLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author yun
 * <p>
 * create_time  2020/12/15 14:09.
 */

@Aspect
@Component
public class LimiterHandler {
    private final BaseLimiter<String> nameLimiter = new BaseLimiter<>();

    private final MethodParamExpressionParser methodParamExpressionParser;

    private final AspectLimiterHandler aspectLimiterHandler;

    /**
     * @param methodParamExpressionParser
     * @param aspectLimiterHandler
     */
    public LimiterHandler(MethodParamExpressionParser methodParamExpressionParser, @Nullable AspectLimiterHandler aspectLimiterHandler) {
        this.methodParamExpressionParser = methodParamExpressionParser;
        this.aspectLimiterHandler = aspectLimiterHandler;
    }

    /**
     * @param joinPoint
     * @param limiter
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(limiter)")
    public Object around(ProceedingJoinPoint joinPoint, Limiter limiter) throws Throwable {
        if (limiter.qps() <= 0) {
            return joinPoint.proceed();
        }

        String id = null;
        if (limiter.id()) {
            if (aspectLimiterHandler == null) {
                throw LimiterException.CommonEp("Limiter获取id无效(AspectLimiterHandler未实现)");
            }

            id = aspectLimiterHandler.limiterId();
            if (StringUtils.isEmpty(id)) {
                throw LimiterException.CommonEp("Limiter获取id 无效");
            }
        }

        String name = getLimiterName(joinPoint, limiter, id);
        nameLimiter.checkValid(name, limiter.qps());

        return joinPoint.proceed();
    }

    /**
     * 获取锁的name。name+keyValues
     * @param joinPoint
     * @param limiter
     * @return
     */
    private String getLimiterName(ProceedingJoinPoint joinPoint, Limiter limiter, String id) {
        String keyName;
        if (StringUtils.isEmpty(limiter.name())) {
            // 无名称则用方法名称
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            keyName = String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        } else {
            keyName = limiter.name();
        }

        // 获取表达式内容
        String param = methodParamExpressionParser.getKeyValueString(joinPoint, limiter.keys(), null);

        if (id != null) {
            return String.format("%s(id:%s)-%s", keyName, id, param);
        } else {
            return String.format("%s-%s", keyName, param);
        }
    }
}
