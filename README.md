# SpringCloud_Project
SpringCloud

一、Feign简介

  Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。

简而言之：

1.Feign 采用的是基于接口的注解.
2.Feign 整合了ribbon.

Feign具有如下特性：
    可插拔的注解支持，包括Feign注解和JAX-RS注解;
    支持可插拔的HTTP编码器和解码器;
    支持Hystrix和它的Fallback;
    支持Ribbon的负载均衡;
    支持HTTP请求和响应的压缩。

