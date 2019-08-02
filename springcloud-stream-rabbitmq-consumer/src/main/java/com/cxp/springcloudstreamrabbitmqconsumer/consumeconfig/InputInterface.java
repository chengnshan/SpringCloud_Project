package com.cxp.springcloudstreamrabbitmqconsumer.consumeconfig;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 14:19
 */
@Component
public interface InputInterface {

    /**
     * // 输入通道名称
     */
    String INPUT1 = "input1";
    String INPUT2 = "input2";

    @Input(INPUT1)
    SubscribableChannel input1();

    @Input(INPUT2)
    SubscribableChannel input2();

}
