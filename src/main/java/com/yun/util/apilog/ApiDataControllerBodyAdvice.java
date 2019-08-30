package com.yun.util.apilog;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: yun
 * @createdOn: 2019/8/30 13:56.
 */

@RestControllerAdvice
public class ApiDataControllerBodyAdvice {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        ApiData apiData = ApiDataUtil.getAdviceData();
        if (apiData == null) {
            apiData = new ApiData();

            ApiDataUtil.saveAdviceData(apiData);
        }
    }
}
