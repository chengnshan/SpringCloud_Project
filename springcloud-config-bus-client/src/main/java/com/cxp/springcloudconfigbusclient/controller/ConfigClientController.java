package com.cxp.springcloudconfigbusclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程
 * @date 2019/7/26 下午7:04
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value(value = "${config.bus.client.name}")
    private String configBusClientName;

    @GetMapping("/getProfile")
    public String getProfile() {
        return "configClientName : "+this.configBusClientName;
    }

    public String getConfigClientName() {
        return configBusClientName;
    }

    public void setConfigClientName(String configClientName) {
        this.configBusClientName = configClientName;
    }

}
