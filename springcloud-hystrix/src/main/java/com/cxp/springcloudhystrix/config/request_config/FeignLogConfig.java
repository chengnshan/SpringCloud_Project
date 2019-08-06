package com.cxp.springcloudhystrix.config.request_config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程
 * @date 2019/7/25 上午9:39
 */
@Configuration
public class FeignLogConfig {

    @Bean
    Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }
}
