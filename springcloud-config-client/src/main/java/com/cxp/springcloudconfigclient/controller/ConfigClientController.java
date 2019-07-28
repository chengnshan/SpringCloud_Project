package com.cxp.springcloudconfigclient.controller;

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

    @Value(value = "${config.client.name}")
    private String configClientName;

    @Value(value = "${custom.name}")
    private String customName;

    @Value(value = "${spring.application.name}")
    private String saName;

    @GetMapping("/getProfile")
    public String getProfile() {
        return "configClientName : "+this.configClientName;
    }

    public String getConfigClientName() {
        return configClientName;
    }

    public void setConfigClientName(String configClientName) {
        this.configClientName = configClientName;
    }

    @GetMapping("/customProperties")
    public String customProperties(){
        return customName;
    }

    @GetMapping("/customPropertiesCloud")
    public String customPropertiesCloud(){
        return saName;
    }
}
