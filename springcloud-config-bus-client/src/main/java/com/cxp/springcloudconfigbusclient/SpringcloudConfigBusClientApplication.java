package com.cxp.springcloudconfigbusclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudConfigBusClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConfigBusClientApplication.class, args);
    }

}
