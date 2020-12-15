package com.yun.util.limiter.local.aspect;

import com.yun.util.limiter.LimiterException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 方法参数表达式转换器
 * @author yun
 * <p>
 * create_time  2020/12/15 14:03.
 */
public class MethodParamExpressionParser {
    /**
     *
     */
    private final ExpressionParser epParser = new SpelExpressionParser();

    /**
     *
     */
    private final ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    /**
     * 组装key
     * @param joinPoint
     * @param keys
     * @return
     */
    public String getKeyValueString(JoinPoint joinPoint, String[] keys, String sp) {
        List<String> keyList = getKeyValues(joinPoint, keys);

        if (sp == null) {
            sp = "-";
        }

        return StringUtils.collectionToDelimitedString(keyList, sp, "", "");
    }

    /**
     * 组装key
     * @param joinPoint
     * @param keys
     * @return
     */
    public List<String> getKeyValues(JoinPoint joinPoint, String[] keys) {
        if (keys == null || keys.length == 0) {
            return Collections.emptyList();
        }

        Method method = getMethod(joinPoint);

        List<String> valueList = getMethodParamValues(method, joinPoint.getArgs(), keys);

        return valueList;
    }

    /**
     * 获取方法的签名
     * @param joinPoint
     * @return
     */
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 接口
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
            } catch (Exception e) {
                e.printStackTrace();
                throw LimiterException.CommonEp("获取方法失败", e.getMessage());
            }
        }

        return method;
    }

    /**
     * 表达式取key值
     * @param method
     * @param parameterValues
     * @param keys
     * @return
     */
    private List<String> getMethodParamValues(Method method, Object[] parameterValues, String[] keys) {
        List<String> values = new ArrayList<>();

        EvaluationContext context = new MethodBasedEvaluationContext(null, method, parameterValues, nameDiscoverer);

        for (String key : keys) {
            if (StringUtils.isEmpty(key)) {
                continue;
            }

            // 非参数设定
            if (!key.startsWith("#")) {
                values.add(key);
                continue;
            }

            // 去参数设置
            Object paraValue = epParser.parseExpression(key).getValue(context);

            // 无值用 null
            String value = ObjectUtils.nullSafeToString(paraValue);
            values.add(value);
        }

        return values;
    }
}
