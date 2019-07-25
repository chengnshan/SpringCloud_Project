package com.cxp.springcloudribbon.controller;

import com.cxp.springcloudribbon.pojo.UserInfo;
import com.cxp.springcloudribbon.service.RibbonHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 程
 * @date 2019/7/24 下午1:29
 */
@Controller
@Slf4j
public class RibbonHystrixController {

    @Resource
    private RibbonHystrixService ribbonHystrixService;

    @RequestMapping(value = "/ribbonHystrixTestOne")
    @ResponseBody
    @HystrixCommand(commandKey ="ribbonHystrixTestOne",fallbackMethod = "requestFailOne")
    public String ribbonHystrixTestOne(@RequestBody UserInfo userInfo){
        String ribbon = ribbonHystrixService.requestRibbon("http://SPRINGCLOUD-PRODUCER/producer/ribbonHystrixTest", userInfo);
        return ribbon;
    }

    public String requestFailOne(UserInfo userInfo){
        log.info("进入请求失败方法requestFailOne ！");
        return "request Fail !";
    }

    @RequestMapping(value = "/ribbonHystrixTestTwo")
    @ResponseBody
    @HystrixCommand(commandKey ="ribbonHystrixTestTwo",fallbackMethod = "requestFailTwo")
    public String ribbonHystrixTestTwo(@RequestBody UserInfo userInfo){
        String ribbon = ribbonHystrixService.requestRibbon("http://SPRINGCLOUD-PRODUCER/producer/ribbonHystrixTest", userInfo);
        return ribbon;
    }

    public String requestFailTwo(UserInfo userInfo){
        log.info("进入请求失败方法requestFailTwo ！");
        return "request Fail !";
    }
}
