SpringCloud
===========
一、Feign简介
-----------
http://tool.chinaz.com/tools/unicode.aspx 
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

feign调用session丢失解决方案:<br/>
    最近在做项目的时候发现，微服务使用feign相互之间调用时，存在session丢失的问题。例如，使用Feign调用某个远程API，这个远程API需要传递一个鉴权信息，我们可以把cookie里面的session信息放到Header里面，这个Header是动态的，跟你的HttpRequest相关，我们选择编写一个拦截器来实现Header的传递，也就是需要实现RequestInterceptor接口，具体代码如下：

        @Configuration  
        @EnableFeignClients(basePackages = "com.xxx.xxx.client")  
        public class FeignClientsConfigurationCustom implements RequestInterceptor {  
        @Override  
        public void apply(RequestTemplate template) {  
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  
    if (requestAttributes == null) {  
      return;  
    }  

    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();  
    Enumeration<String> headerNames = request.getHeaderNames();  
    if (headerNames != null) {  
      while (headerNames.hasMoreElements()) {  
        String name = headerNames.nextElement();  
        Enumeration<String> values = request.getHeaders(name);  
        while (values.hasMoreElements()) {  
          String value = values.nextElement();  
          template.header(name, value);  
         }  
       }  
      }  
     }  
    } 
    
经过测试，上面的解决方案可以正常的使用；但是，当引入Hystrix熔断策略时，出现了一个新的问题；

    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
获取不到request信息，从而无法传递session信息，最终发现RequestContextHolder.getRequestAttributes()该方法是从ThreadLocal变量里面取得对应信息的，这就找到问题原因了，由于Hystrix熔断机制导致的。 
Hystrix有隔离策略：THREAD以及SEMAPHORE，当隔离策略为 THREAD 时，是没办法拿到 ThreadLocal 中的值的。






四、Zuul
-------


由于Spring Cloud Zuul包含了对Hystrix和Ribbon的依赖，所以Zuul拥有线程隔离和断路器的自我保护功能，以及对客户端的负载均衡能力。但是，需要注意在使用path和url的映射关系来配置路由规则时，对于路由转发的请求不会采用HystrixCommand来包装，所以这类路由请求没有线程隔离和断路器的保护以及负载均衡的能力。所以使用Zuul的时候尽量使用path和serviceId的方式配置。
在使用Zuul时可以配置Hystrix和Ribbon的参数来调整路由各种超时时间等配置。<br>
1、`hystrix.command.default.execution.isolation.thread.timeoutMilliseconds`：该参数用来设置API网关中路由转发请求的HystrixCommand超时时间，单位为毫秒。当路由转发请求的命令执行时间超过该配置值后，Hystrix会将该执行命令标记为TIMEOUT并抛出异常。<br>
2、`ribbon.ConnectTimeout`：该参数用来设置路由转发请求的时候，创建请求连接的超时时间。当ribbon.ConnectTimeout的配置值小于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，若出现路由请求连接超时，会自动进行重试路由请求，如果重试依然失败，Zuul会抛出异常。如果ribbon.ConnectTimeout的配置值大于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，不会进行请求重试，直接抛出异常。<br>
3、`ribbon.ReadTimeout`：该参数用来设置路由转发请求的超时时间。它的处理与ribbon.ConnectTimeout相似，当ribbon.ReadTimeout的配置值小于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，若出现路由请求连接超时，会自动进行重试路由请求，如果重试依然失败，Zuul会抛出异常。如果ribbon.ReadTimeout的配置值大于hystrix.command.default.execution.isolation.thread.timeoutMilliseconds配置值的时候，不会进行请求重试，直接抛出异常。<br>



五、Spring Cloud Gateway
-------
**Spring Cloud Gateway 是什么?** <br/>
~~~~
** Spring Cloud Gateway 基于 Spring Boot 2， 是 Spring Cloud 的 全新 项目，<br> 
该项 目 提供 了 一个 构建 在 Spring 生态 之上 的 API 网关， 包括 Spring 5、 Spring Boot 2 和 Project Reactor。 
** Spring Cloud Gateway 旨在 提供 一种 简单 而 有效 的 途径 来 转发 请求， 并为 它们 提供 横 切 关注 点， 例如： 安全性、 监控/ 指标 和 弹性。
~~~~

**Spring Cloud Gateway 特性**
~~~~
    基于 Java 8 编码；
    基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建
    支持动态路由，能够匹配任何请求属性上的路由。
    支持 内置 到 Spring Handler 映射 中的 路 由 匹配；
    支持 基于 HTTP 请求 的 路 由 匹配（ Path、 Method、 Header、 Host 等）；
    集成了Hystrix断路器
    过滤器 作用于 匹配 的 路 由；
    过滤器可以修改 HTTP 请求和HTTP 响应（ 增加/ 修改 头部、 增加/ 修改 请求 参数、 改写 请求 路径 等）；
    支持 Spring Cloud DiscoveryClient 配置路由，与服务发现与注册配合使用。
    支持限流
    支持地址重写
~~~~




