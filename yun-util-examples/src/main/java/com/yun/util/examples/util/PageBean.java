package com.yun.util.examples.util;

import com.querydsl.core.QueryResults;
import lombok.Data;

import java.util.List;

/**
 * @author yun
 * created_time 2019-02-28 18:54.
 */

@Data
public class PageBean<T> {
    private final long pageIndex;
    private final long pageSize;
    private final long totalCount;
    private final long totalPage;
    private final List<T> results;

    public PageBean(QueryResults<T> rst) {
        long pI = rst.getOffset() / rst.getLimit();
        if (ParaUtil.isStartFromOne) {
            pI++;
        }

        pageIndex = pI;

        // todo 暂时处理
        pageSize = rst.getLimit() == Long.MAX_VALUE ? 20 : rst.getLimit();

        totalCount = rst.getTotal();

        results = rst.getResults();

        if (pageSize > 0) {
            long page = totalCount / pageSize;
            if (totalCount % pageSize > 0) {
                page++;
            }

            totalPage = page;
        } else {
            totalPage = 0;
        }
    }
}
