## yun-util: springboot 开发工具

提供 springboot 开发的常用封装

# 一、基本信息

## 1、环境

- java:1.8

- springboot:2.0.0 以上



## 2、使用（Maven）[]()

1）pom 引入全部项目

```xml
<dependency>
    <groupId>com.github.yunwjr</groupId>
    <artifactId>yun-util-all</artifactId>
    <version>${yu-vision}</version>
</dependency>
```



2）配置

```java
@Configuration
@EnableAllYunUtil
public class YunUtilConfig {

}
```





## 3、示例

查看 yun-util-examples 目录

# 二、各模块说明：

## apilog

接口日志记录

- 记录每个接口的参数（header、请求值、返回值）、接口请求用时、接口错误堆栈详情
- 自定义日志心跳信号
- 可添加自定义信息。
- 可存储到 ES



## auth

权限管理

- 基于 token 的权限管理
- token定义、存储



## base

项目基本信息配置



## common

共用工具类



## idgenerator

分布式 ID 生成器

- 基于Snowflake



## querydsl

JAP+querydsl 方案封装



## sb

业务部分通用工具

- GlobalExceptionHandler：全局异常拦截

- dtovo：常用请求对象封装

- rsp：统一返回对象封装

- BaseServiceImpl：service 基类功能封装

  

## swagger

swagger 接口文档配置



## token

token 生成辅助类



## mybatis  todo

mybatis封装 todo



## limit todo

限流工具（准备从 yun-limit 迁入）



## lock todo

锁  todo



## oss todo

对象存储  todo



## pay todo

支付处理  todo



## message todo

消息管理  todo



## wc todo

微信工具  todo



## redis todo

redis辅助工具  todo

