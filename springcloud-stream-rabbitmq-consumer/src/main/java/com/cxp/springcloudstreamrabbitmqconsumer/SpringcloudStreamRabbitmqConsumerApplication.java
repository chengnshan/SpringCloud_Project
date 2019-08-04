package com.cxp.springcloudstreamrabbitmqconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudStreamRabbitmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudStreamRabbitmqConsumerApplication.class, args);
    }

}
