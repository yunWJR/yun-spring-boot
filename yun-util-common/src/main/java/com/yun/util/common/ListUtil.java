package com.yun.util.common;

import java.util.*;

/**
 * The itemType List util.
 * todo 使用 stream 识别
 * @author: yun
 * @createdOn: 2018 /8/3 16:44.
 */
public class ListUtil {
    /**
     * 删除ArrayList中重复元素
     * @param list the list
     * @return the list
     */
    public static List<Object> removeDuplicate(List<Object> list) {
        if (list == null) {
            return null;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }

        return list;
    }

    /**
     * 删除ArrayList中重复元素,add进去顺序就变了不考虑顺序的话可以使用
     * 通过HashSet剔除
     * @param list the list
     * @return the list
     */
    public static List removeDuplicateHash(List list) {
        if (list == null) {
            return null;
        }

        HashSet hashSet = new HashSet(list);
        list.clear();
        list.addAll(hashSet);

        return list;
    }

    /**
     * 删除ArrayList中重复元素，保持顺序
     * 删除ArrayList中重复元素，保持顺序
     * @param list the list
     * @return the list
     */
    public static List<Object> removeDuplicateWithOrder(List<Object> list) {
        if (list == null) {
            return null;
        }

        Set set = new HashSet();
        List<Object> newList = new ArrayList<>();
        for (Iterator<Object> iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        list.clear();
        list.addAll(newList);

        return list;
    }
}