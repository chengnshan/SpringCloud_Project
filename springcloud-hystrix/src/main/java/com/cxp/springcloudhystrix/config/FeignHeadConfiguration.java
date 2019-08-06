package com.cxp.springcloudhystrix.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author cheng
 * @data 2019/8/6 15:43
 */
@Configuration
@Slf4j
public class FeignHeadConfiguration {

    /**
     * 最近在做项目的时候发现，微服务使用feign相互之间调用时，存在session丢失的问题。例如，使用Feign调用某个远程API，这个
     * 远程API需要传递一个鉴权信息，我们可以把cookie里面的session信息放到Header里面，这个Header是动态的，跟你的HttpRequest
     * 相关，我们选择编写一个拦截器来实现Header的传递，也就是需要实现RequestInterceptor接口，具体代码如下:
     *
     * 经过测试，上面的解决方案可以正常的使用；但是，当引入Hystrix熔断策略时，出现了一个新的问题；
     * 获取不到request信息，从而无法传递session信息，最终发现RequestContextHolder.getRequestAttributes()该方法是从
     * ThreadLocal变量里面取得对应信息的，这就找到问题原因了，由于Hystrix熔断机制导致的。
     * Hystrix有隔离策略：THREAD以及SEMAPHORE，当隔离策略为 THREAD 时，是没办法拿到 ThreadLocal 中的值的。
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate->{
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        Enumeration<String> values = request.getHeaders(name);
                        while (values.hasMoreElements()) {
                            String value = values.nextElement();
                            requestTemplate.header(name, value);
                        }
                    }
                }
            }
        };
    }
}
