package com.cxp.springcloudproducerh2database.controller;

import com.cxp.springcloudproducerh2database.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * @author 程
 * @date 2019/7/25 下午12:54
 */
@RestController
@Slf4j
public class HystrixController {

    @RequestMapping(value = "/ribbonHystrixTest")
    public UserInfo ribbonHystrixTest(@RequestBody UserInfo userInfo){
        log.info("ribbonHystrixTest in param: "+ userInfo);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @RequestMapping(value = "/transferRequestHeader")
    public String transferRequestHeader(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(name);
            while (headers.hasMoreElements()){
                String value = headers.nextElement();
                System.out.println(name+"  ===  "+value);
            }
        }
        return null;
    }
}
