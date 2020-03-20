package com.yun.util.apilog.advice;

import com.yun.util.apilog.ApiData;
import com.yun.util.apilog.ApiDataUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    // @InitBinder
    // public void initBinder(WebDataBinder binder) {
    // }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * 应用该方法，记录请求开始时间
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        ApiData apiData = ApiDataUtil.getAdviceData();
        if (apiData == null) {
            apiData = new ApiData();

            ApiDataUtil.saveAdviceData(apiData);
        }
    }
}
