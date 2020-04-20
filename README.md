# yun-util: springboot 开发工具

提供 springboot 开发的常用工具



## 版本需求：

- java:1.8

- springboot:2.0.0 以上



## Maven

```xml
<dependency>
    <groupId>com.github.yunwjr</groupId>
    <artifactId>yun-util-all</artifactId>
    <version>0.0.29</version>
</dependency>
```



## 各模块说明：

### apilog-接口日志记录

- 记录每个接口的参数（header、请求值、返回值）、接口请求用时、接口错误堆栈
- 自定义日志心跳信号
- 可添加自定义信息。
- 可存储到 ES（通过 logstash）



### auth-权限管理辅助类

- 基于 token 的权限管理

- token定义、存储

  

### sb-分层各模块工具

- GlobalExceptionHandler：全局异常拦截

- dtovo：常用请求对象封装

- rsp：统一返回对象封装

- BaseServiceImpl：service 基类功能封装

  


### common-通用工具

通用工具类



### idgenerator-分布式 ID 生成器

- 基于Snowflake

  

### querydsl-封装

JAP+querydsl 方案封装



### mybatis -mybatis封装 todo



### swagger-配置辅助

swagger配置辅助类



### limit-限流工具（准备从 yun-limit 迁入）



### lock-锁  todo



### OSS -对象存储  todo



### PAY - 支付处理  todo



### message - 消息管理  todo



### WC - 微信工具  todo



### REDIS -redis辅助工具  todo

[]()