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
 * @date 2019/7/24 下午7:04
 */
@RestController
@Slf4j
public class FeignController {

    @Autowired
    private ServerConfig serverConfig;

    @RequestMapping(value = "/feignTest")
    public String feignTest(@RequestBody UserInfo userInfo){
        log.info("feignTest in param: "+ userInfo);
        return String.format("feignTest 收到信息!我是端口： %s",serverConfig.getServerPort());
    }
}
