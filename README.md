SpringCloud
===========
一、Feign简介
-----------
    Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。

简而言之：

1.Feign 采用的是基于接口的注解.<br>
2.Feign 整合了ribbon.

Feign具有如下特性：<br>
    可插拔的注解支持，包括Feign注解和JAX-RS注解;<br>
    支持可插拔的HTTP编码器和解码器;<br>
    支持Hystrix和它的Fallback;<br>
    支持Ribbon的负载均衡;<br>
    支持HTTP请求和响应的压缩。<br>


四、Zuul
-------


        由于Spring Cloud Zuul包含了对Hystrix和Ribbon的依赖，所以Zuul拥有线程隔离和断路器的自我保护功能，以及对客户端的负载均衡能力。但是，需要注意在使用path和url的映射关系来配置路由规则时，对于路由转发的请求不会采用HystrixCommand来包装，所以这类路由请求没有线程隔离和断路器的保护以及负载均衡的能力。所以使用Zuul的时候尽量使用path和serviceId的方式配置。
在使用Zuul时可以配置Hystrix和Ribbon的参数来调整路由各种超时时间等配置。
<br>1、hystrix.command.default.execution.isolation.thread.timeoutMilliseconds：该参数用来设置API网关中路由转发请求的HystrixCommand超时时间，单位为毫秒。当路由转发请求的命令执行时间超过该配置值后，Hystrix会将该执行命令标记为TIMEOUT并抛出异常。
<br>2、ribbon.ConnectTimeout：该参数用来设置路由转发请求的时候，创建请求连接的超时时间。当ribbon.ConnectTimeout的配置值小于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，若出现路由请求连接超时，会自动进行重试路由请求，如果重试依然失败，Zuul会抛出异常。如果ribbon.ConnectTimeout的配置值大于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，不会进行请求重试，直接抛出异常。
<br>3、ribbon.ReadTimeout：该参数用来设置路由转发请求的超时时间。它的处理与ribbon.ConnectTimeout相似，当ribbon.ReadTimeout的配置值小于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，若出现路由请求连接超时，会自动进行重试路由请求，如果重试依然失败，Zuul会抛出异常。如果ribbon.ReadTimeout的配置值大于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，不会进行请求重试，直接抛出异常。




