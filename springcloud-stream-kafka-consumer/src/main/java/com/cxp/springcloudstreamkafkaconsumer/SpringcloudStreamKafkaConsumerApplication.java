package com.cxp.springcloudstreamkafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudStreamKafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudStreamKafkaConsumerApplication.class, args);
    }

}
