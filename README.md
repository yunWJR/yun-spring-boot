# yun-util: springboot 应用开发工具

该工具提供 springboot 开发的常用工具



## Requirements

> java:1.8

> springboot:2.0.0 以上



## Maven

```xml
<dependency>
    <groupId>com.github.yunwjr</groupId>
    <artifactId>yun-util-all</artifactId>
    <version>0.0.28</version>
</dependency>
```



## 各模块说明：

### apilog-接口日志记录

- 记录每个接口的参数（header、请求值、返回值），接口用时
- 添加自定义信息。

- 可存储到 ES（通过 logstash）



### auth-权限管理辅助类

- 基于 token 的权限管理
- token定义、存储

### idgenerator-分布式 ID 生成器

- 基于Snowflake

### module-分层各模块工具

- 全局异常拦截
- 常用请求对象封装
- 统一返回对象封装
- service 基类封装


### common-通用工具



### jpa-JPA 封装（准备移到独立项目）



### limit-限流工具（准备从 yun-limit 迁入）

