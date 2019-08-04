package com.cxp.springcloudstreamkafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudStreamKafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudStreamKafkaProducerApplication.class, args);
    }

}
