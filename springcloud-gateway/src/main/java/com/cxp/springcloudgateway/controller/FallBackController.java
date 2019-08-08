package com.cxp.springcloudgateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-08 13:48
 */
@Controller
public class FallBackController {

    @RequestMapping(value = "producer-h2database-fallback")
    @ResponseBody
    public String producer_h2database_fallback(ServerHttpRequest request){
        HttpHeaders headers = request.getHeaders();
        return "producer_h2database_fallback====";
    }

    @RequestMapping(value = "producer-fallback")
    @ResponseBody
    public String producer_fallback(ServerHttpRequest request){
        HttpHeaders headers = request.getHeaders();
        return "producer_fallback=====";
    }
}
