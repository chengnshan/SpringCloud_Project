package com.cxp.springcloudgateway.filter;

import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * https://blog.csdn.net/zhuyu19911016520/article/details/86488547
 *  * 全局过滤器
 *  * 所有请求都会执行
 *  * 可拦截get、post等请求做逻辑处理
 *
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-07 16:09
 */
@Component
@Slf4j
public class RequestBodyFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String strUri = serverHttpRequest.getURI().toString();
        System.out.println(" uri : " + strUri);//打印每次请求的url
        String method = serverHttpRequest.getMethodValue();
        if ("POST".equals(method)) {
            //方式一:从cachedRequestBodyObject中 获取request body参数内容
            /**
             * 通过cachedRequestBodyObject缓存字段获取request body信息，这种解决，
             * 一不会带来重复读取问题，
             * 二不会带来requestbody取不全问题。
             * 三在低版本的Spring Cloud Finchley.SR2也可以运行
             */
            Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
            log.info("request body is:{}", requestBody);

            //方式二:webFlux方式 从请求里获取request body
            /**
             * 针对这种不能重复获取的问题，网上通用解决是把request重新包装，继续传递，比如 这篇文章的解决方案。
             * 但是这种方案还会带来request body获取不完整，只能获取1024B的数据
             */
            String bodyStr = resolveBodyFromRequest(serverHttpRequest);
            //TODO 得到Post请求的请求参数后，做你想做的事

            //下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
            URI uri = serverHttpRequest.getURI();
            ServerHttpRequest request = serverHttpRequest.mutate().uri(uri).build();
            DataBuffer bodyDataBuffer = stringBuffer(bodyStr);
            Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

            request = new ServerHttpRequestDecorator(request){
                @Override
                public Flux<DataBuffer> getBody() {
                    return bodyFlux;
                }
            };
            //封装request，传给下一级
            return chain.filter(exchange.mutate().request(request).build());
        }else if ("GET".equals(method)) {
            Map requestQueryParams = serverHttpRequest.getQueryParams();
            System.out.println("Get请求的参数：" + requestQueryParams );
            //TODO 得到Get请求的请求参数后，做你想做的事

            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     *
     * 这种方法在spring-boot-starter-parent 2.0.6.RELEASE + Spring Cloud Finchley.SR2 body 中生效，
     * 但是在spring-boot-starter-parent 2.1.0.RELEASE + Spring Cloud Greenwich.M3 body 中不生效，总是为空
     *
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();

        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            /*byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            try {
                String bodyString = new String(bytes, "utf-8");
                System.out.println(bodyString);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/

            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }

    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);

        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    /**执行顺序*/
    @Override
    public int getOrder() {
        return 0;
    }
}
