package com.yun.util.examples.module.test;

import com.yun.util.common.StringUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yun
 * @createdOn: 2019-04-10 13:12.
 */

@Data
public class WeChatTempMessageDTO {
    private String touser; // openid
    private String template_id;
    private String url;
    private String topcolor = "#FF0000";

    private Map<String, Map<String, String>> data;

    public WeChatTempMessageDTO(String wcOpenId, String templateId) {
        touser = wcOpenId;
        template_id = templateId;
    }

    public void addData(String name, String value) {
        addData(name, value, null);
    }

    public void addData(String name, String value, String color) {
        Map<String, String> vMap = new HashMap<>();
        vMap.put("value", value);

        if (StringUtil.hasCtn(color)) {
            vMap.put("color", color);
        } else {
            vMap.put("color", "#173177");
        }

        if (data == null) {
            data = new HashMap<>();
        }

        data.put(name, vMap);
    }
}
