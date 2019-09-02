package com.yun.util.apilog;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.server.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 记录的 api 数据
 * @author: yun
 * @createdOn: 2019/8/29 14:40.
 */

@Data
public class ApiData {
    @JsonIgnore
    private long startTime;

    @JsonIgnore
    private long endTime;

    private long costTime;

    private String host;

    private String url;

    private String query;

    @JsonIgnore
    private String header;

    @JsonIgnore
    private String body;

    @JsonIgnore
    private String response;

    public ApiData() {
        this.startTime = System.currentTimeMillis();
    }

    public ApiData(HttpServletRequest request) {
        this.startTime = System.currentTimeMillis();

        this.url = request.getMethod() + " " + request.getRequestURI();

        this.query = request.getQueryString();

        Enumeration<String> names = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        //输出所有的头信息的名字
        while (names.hasMoreElements()) {
            //获取request 的请求头名字name
            String name = names.nextElement();
            //根据头名字获取对应的值
            String value = request.getHeader(name);
            //输出：
            headerMap.put(name, value);
        }

        this.header = JSONUtil.toJsonStr(headerMap);

        try {
            this.body = new RequestWrapper(request).getBodyString();
        } catch (Exception e) {
            this.body = "error body";
        }
    }

    public static ApiData newItem() {
        ApiData apiData = new ApiData();

        apiData.setStartTime(0);

        return apiData;
    }

    public void updateEndTime() {
        this.endTime = System.currentTimeMillis();

        // 无开始时间记录
        if (this.startTime == 0) {
            this.costTime = 99999;
            return;
        }

        this.costTime = this.endTime - this.startTime;
    }

    public void updateHttp(ServerHttpRequest request) {
        if (request == null) {
            return;
        }

        if (request.getURI() != null) {
            if (request.getMethod() == null) {
                this.host = request.getURI().getHost();
            } else {
                this.host = request.getMethod() + " " + request.getURI().getHost();
            }

            this.url = request.getURI().getPath();

            this.query = request.getURI().getQuery();
        }

        if (request.getHeaders() != null) {
            this.header = JSONUtil.toJsonStr(request.getHeaders().entrySet());
        }

        updateEndTime();
    }
}