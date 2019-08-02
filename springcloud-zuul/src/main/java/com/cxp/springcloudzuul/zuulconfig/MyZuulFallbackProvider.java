package com.cxp.springcloudzuul.zuulconfig;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/** 自定义异常回退处理类
 * @author 程
 * @date 2019/7/26 下午12:26
 */
@Component
@Slf4j
public class MyZuulFallbackProvider implements FallbackProvider {

    /**
     * getRoute()：指明拦截你需要降级的服务名，如果需要所有调用都支持回退，则return "*"或return null。
     * @return
     */
    @Override
    public String getRoute() {
        return "springcloud-producer";
    }

    /**
     * fallbackResponse()：定制返回内容。
     * 如果请求用户服务失败，返回什么信息给消费者客户端
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.info("fallbackResponse : route :="+route+",Throwable: =" + cause.getMessage());
        return new ClientHttpResponse() {

            /**
             * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的， 不应该把api的404,500等问题抛给客户端
             * 网关和api服务集群对于客户端来说是黑盒子
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                JSONObject r = new JSONObject();
                r.put("state", "9999");
                r.put("msg", "系统错误，请求失败");
                return new ByteArrayInputStream(r.toJSONString().getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                // 和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
