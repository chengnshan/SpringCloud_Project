package com.cxp.springcloudgateway.filter;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.function.Function;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-07 17:20
 */
@Configuration
@Log4j2
public class RequestConfig {

    @Autowired
    private RequestBodyFilter requestBodyFilter;
    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    private static final String PATH = "/producer-h2database/**";
    private static final String URI = "lb://springcloud-producer-h2database";

    /**
     * 类似于在配置文件中配置如下:
     *
     *
     *  route1 是get请求，get请求使用readBody会报错
     *  route2 是post请求，Content-Type是application/x-www-form-urlencoded，readbody为String.class
     *  route3 是post请求，Content-Type是application/json,readbody为Object.class
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        Function<GatewayFilterSpec, UriSpec> fn = gatewayFilterSpec->{
            gatewayFilterSpec.filter(tokenAuthenticationFilter.apply(tokenAuthenticationFilter.newConfig()));
            gatewayFilterSpec.stripPrefix(1);
            gatewayFilterSpec.prefixPath("/producer-h2database");
            //设置hystrix
            gatewayFilterSpec.hystrix(config->{
                config.setName("Hystrix");
                HystrixObservableCommand.Setter setter = HystrixObservableCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("producerh2database"));
                setter.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(true).withExecutionTimeoutInMilliseconds(10000));
                config.setSetter(setter);
                config.setFallbackUri("forward:/producer-h2database-fallback");
            });
            return gatewayFilterSpec;
        };

        RouteLocatorBuilder.Builder serviceProvider =
                routes
                        .route("route1",
                                r -> r
                                        .method(HttpMethod.GET)
                                        .and()
                                        .path(PATH)
                                        .filters(fn)
                                        .uri(URI))
                        .route("route2",
                        r ->
                            r.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                            .and()
                            .method(HttpMethod.POST)
                            .and()
                            .readBody(String.class,readBody->{
                                log.info("request method POST, Content-Type is application/x-www-form-urlencoded, body  is:{}",
                                        readBody);
                                return true;
                            })
                            .and()
                            .path(PATH)
                            .filters(fn)
                            .uri(URI))

                .route("route3",r->
                        r.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .and()
                        .method(HttpMethod.POST)
                        .and()
                        .readBody(Object.class, readBody -> {
                            log.info("request method POST, Content-Type is application/json, body  is:{}", readBody);
                            return true;
                        })
                        .and()
                        .path(PATH)
                        .filters(fn)
                        .uri(URI)
                );
        RouteLocator routeLocator = serviceProvider.build();
        log.info("custom RouteLocator is loading ... {}", routeLocator);
        return routeLocator;
    }
}
