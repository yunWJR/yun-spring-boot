package com.yun.util.apilog;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yun
 * @createdOn: 2019/9/9 14:39.
 */

@Component
@ConfigurationProperties(prefix = "yun.apilog")
@Data
public class ApiLogProperty {
    private Index index = new Index();

    private String prefix = "api_data";

    private String msg = "api data";

    @Data
    public static class Index {
        private boolean startTime = false;

        private boolean endTime = false;

        private boolean costTime = true;

        private boolean host = true;

        private boolean url = true;

        private boolean query = true;

        private boolean account = true;

        private boolean version = true;

        private boolean deviceType = true;

        private boolean header = true;

        private boolean body = true;

        private boolean response = true;
    }
}
