package com.yun.util.common;

import org.springframework.beans.BeanUtils;

/**
 * 对象属性工具
 * @author yun
 * created_time 2019-07-12 13:36.
 */

public class PropUtil {

    /**
     * 复制属性
     * @param org
     * @param tag
     * @param ignoreProperties
     * @param <T>
     * @param <D>
     * @return
     */
    public static <T, D> T copyProp(D org, T tag, String... ignoreProperties) {
        BeanUtils.copyProperties(org, tag, ignoreProperties);

        // hutool
        // BeanUtil.copyProperties(org, tag,
        //         CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true).
        //                 setIgnoreProperties(ignoreProperties));

        return tag;
    }

    /**
     * 复制属性-创建新对象
     * @param org
     * @param clazz
     * @param ignoreProperties
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    // 比JsonHelper.newObj快
    public static <T> T copyPropForNew(T org, Class<T> clazz, String... ignoreProperties) throws IllegalAccessException, InstantiationException {
        T newItem = clazz.newInstance();
        copyProp(org, newItem, ignoreProperties);

        // try {
        //     newItem = clazz.newInstance();
        //
        //     copyProp(org,newItem,ignoreProperties);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        return newItem;
    }
}
