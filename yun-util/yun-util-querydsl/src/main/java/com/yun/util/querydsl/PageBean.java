package com.yun.util.querydsl;

import com.querydsl.core.QueryResults;

import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-02-28 18:54.
 */

public class PageBean<T> {
    private final long current;
    private final long size;
    private final long total;
    private final long pages;
    private final List<T> records;

    public PageBean(QueryResults<T> rst) {
        long pI = rst.getOffset() / rst.getLimit();
        if (ParaUtil.isStartFromOne) {
            pI++;
        }

        current = pI;

        // todo 暂时处理
        size = rst.getLimit() == Long.MAX_VALUE ? 20 : rst.getLimit();

        total = rst.getTotal();

        records = rst.getResults();

        if (size > 0) {
            long page = total / size;
            if (total % size > 0) {
                page++;
            }

            pages = page;
        } else {
            pages = 0;
        }
    }

    public long getCurrent() {
        return current;
    }

    public long getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public long getPages() {
        return pages;
    }

    public List<T> getRecords() {
        return records;
    }
}
