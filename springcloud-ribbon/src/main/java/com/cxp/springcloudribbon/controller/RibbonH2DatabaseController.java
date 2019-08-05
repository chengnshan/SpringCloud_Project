package com.cxp.springcloudribbon.controller;

import com.cxp.springcloudribbon.pojo.UserInfo_H2Database;
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
public class RibbonH2DatabaseController {

    @Resource
    private RibbonService ribbonService;

    @RequestMapping(value = "/requestProducerH2Database")
    @ResponseBody
    public String requestProducerH2Database(@RequestBody UserInfo_H2Database userInfo){
        String ribbon = ribbonService.requestRibbonH2(
                "http://SPRINGCLOUD-PRODUCER-H2DATABASE/producer-h2database/ribbonTest",
                userInfo);
        return ribbon;
    }
}
