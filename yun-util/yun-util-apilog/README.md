# apilog

接口日志记录

- 记录每个接口的参数（header、请求值、返回值）、接口请求用时、接口错误堆栈详情
- 自定义日志心跳信号
- 可添加自定义信息。
- 可存储到 ES

# 1、记录接口参数

## 1）接口参数获取

有两种实现方式：

- 使用 RestControllerAdvice

- 使用 HandlerInterceptor

## 2）接口数据存储

使用ThreadLocal



## 3）配置项

- 可以配置需要记录的字段
- 可添加自定义字段

# 2、日志保存

使用 slf4j 保存日志。

支持 ES

