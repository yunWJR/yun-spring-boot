package com.yun.util.authorization;

import java.util.List;

/**
 * @author yun
 * <p>
 * create_time  2020/8/3 15:05.
 */

public interface AuthPathInterceptor {
    default List<String> allowPathPatterns() {
        return null;
    }

    default List<String> excludePathPatterns() {
        return null;
    }
}
