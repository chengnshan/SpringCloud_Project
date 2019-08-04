package com.cxp.springcloudfeignupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class SpringcloudFeignUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudFeignUploadApplication.class, args);
    }

}
