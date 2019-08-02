package com.cxp.springcloudstreamrabbitmqproducer.streanconfig;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * 绑定消息通道, 在spring cloud stream中, 我们通过@input, @output注解来定义消息通道,
 * 改接口可以被@EnableBinding注解的value参数来指定, 从而在应用启动的时候实现对定义消息通道的绑定
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 14:03
 */
@Component
public interface OutputInterface {

    String OUTPUT1 = "output1";
    String OUTPUT2 = "output2";

    @Output(value = OUTPUT1)
    MessageChannel output1();

    @Output(value = OUTPUT2)
    MessageChannel output2();

}
