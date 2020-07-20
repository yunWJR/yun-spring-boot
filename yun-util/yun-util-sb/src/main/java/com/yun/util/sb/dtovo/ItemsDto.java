package com.yun.util.sb.dtovo;

import java.util.List;

/**
 * @param <T>
 * @author yun
 * created_time 2018/5/28 18:33.
 */
public class ItemsDto<T> {
    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
