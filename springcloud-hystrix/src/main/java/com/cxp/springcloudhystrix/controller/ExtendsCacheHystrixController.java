package com.cxp.springcloudhystrix.controller;

import com.cxp.springcloudhystrix.config.MyCacheHystrixCommand;
import com.cxp.springcloudhystrix.pojo.UserInfo;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 程
 * @date 2019/7/25 下午8:17
 */
@RestController
public class ExtendsCacheHystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/extendsCacheHystrix")
    public String extendsCacheHystrix(){
        String url = "http://SPRINGCLOUD-PRODUCER/producer/feignTestRest/1";
        // Hystrix的缓存实现，这功能有点鸡肋
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        //获取当前执行的方法名称
        String methodName = new Exception().getStackTrace()[0].getMethodName();
        System.out.println(methodName);

        MyCacheHystrixCommand command1 = new MyCacheHystrixCommand("feignTestRest",restTemplate,url,1);
        UserInfo result1 = command1.execute();

        MyCacheHystrixCommand command2 = new MyCacheHystrixCommand("feignTestRest",restTemplate,url,1);
        UserInfo result2 = command2.execute();

        System.out.println("first request result is: " + result1 + " ,and secend request result is: " + result2);

        context.close();

        return methodName;
    }
}
