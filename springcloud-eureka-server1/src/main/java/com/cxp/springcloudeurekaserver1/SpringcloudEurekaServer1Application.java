package com.cxp.springcloudeurekaserver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudEurekaServer1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaServer1Application.class, args);
    }

}
