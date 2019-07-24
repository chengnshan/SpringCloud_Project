package com.cxp.springcloudproducer.controller;

import com.cxp.springcloudproducer.config.ServerConfig;
import com.cxp.springcloudproducer.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程
 * @date 2019/7/24 下午1:20
 */
@RestController
@Slf4j
public class RibbonController {

    @Autowired
    private ServerConfig serverConfig;

    @RequestMapping(value = "/ribbonTest")
    public String ribbonTest(@RequestBody UserInfo userInfo){
        log.info("ribbonTest in param: "+ userInfo);
        return String.format("ribbonTest 收到信息!我是端口： %s",serverConfig.getServerPort());
    }
}
