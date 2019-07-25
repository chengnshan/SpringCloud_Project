package com.cxp.springcloudproducer.controller;

import com.cxp.springcloudproducer.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
