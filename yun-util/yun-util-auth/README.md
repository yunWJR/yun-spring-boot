# auth

- 权限管理

  - 基于 token 的权限管理
  - token定义、存储



## 通过实现WebMvcConfigurer的拦截器实现权限管理。



1、实现拦截器HandlerInterceptor

在请求之前对权限进行处理。

权限处理的逻辑由实现接口TokenAuthHandler的类实现。



2、注入拦截器到WebMvcConfigurer



3、权限授权成功后，使用ThreadLocal存储权限信息。





## TODO

多角色权限管理：



