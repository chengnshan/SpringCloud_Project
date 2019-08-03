package com.cxp.springcloudstreamrabbitmqconsumer.consumeconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-02 15:51
 */
@Slf4j
//@Component
public class ReceiverErrorHandler {

    /**
     * 自定义错误处理逻辑,服务降级,消费失败,直接返回信息
     * 默认是 destination + group - index .errors
     * @param message
     */
    @ServiceActivator(inputChannel = "Theme-People-Girl.Group-Girl-0.errors")
    public void error0(Message<?> message){
        log.info("Message consumer failed, call fallback!");
    }

    @ServiceActivator(inputChannel = "Theme-People-Girl.Group-Girl-1.errors")
    public void error1(Message<?> message){
        log.info("Message consumer failed, call fallback!");
    }
}
