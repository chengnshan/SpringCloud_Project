package com.cxp.springcloudribbon.controller;

import com.cxp.springcloudribbon.pojo.UserInfo;
import com.cxp.springcloudribbon.service.RibbonService;
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
public class RibbonController {

    @Resource
    private RibbonService ribbonService;

    @RequestMapping(value = "/requestProducer")
    @ResponseBody
    public String requestProducer(@RequestBody UserInfo userInfo){
        String ribbon = ribbonService.requestRibbon("http://SPRINGCLOUD-PRODUCER/producer/ribbonTest", userInfo);
        return ribbon;
    }
}
