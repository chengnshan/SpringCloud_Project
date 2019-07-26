package com.cxp.springcloudzuul.zuulconfig;

import com.cxp.springcloudzuul.zuulfilter.AccessFilter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程
 * @date 2019/7/26 上午10:18
 */
@Configuration
public class ZuulConfig {

    @Bean
    public ZuulFilter accessFilter(){
        return new AccessFilter();
    }
}
