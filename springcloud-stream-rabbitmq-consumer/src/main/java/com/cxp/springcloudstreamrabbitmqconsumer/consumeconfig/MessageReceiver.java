package com.cxp.springcloudstreamrabbitmqconsumer.consumeconfig;

import com.cxp.springcloudstreamrabbitmqconsumer.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 14:19
 */
@Slf4j
@EnableBinding(value = {InputInterface.class})
public class MessageReceiver {

    /**
     * 监听:@StreamListener接收的参数是要处理的通道（channel）的名，所注解的方法就是处理从通道获取到的数据的方法。
     * 方法的参数就是获取到的数据
     * 消息头:@Header和@Headers的区别就是一个是获取单个属性，需要指明哪个属性，一个是获取全部属性。
     * 注意：如果@StreamListener注解的方法有返回值，那么必须使用@SendTo注解指明返回的值写入哪个通道
     * @param payload
     * @param partition
     */
    @StreamListener(value = InputInterface.INPUT1)
    public void receiveMessageFromChannel1(
            @Payload Message<String> payload,
            @Header(AmqpHeaders.CONSUMER_QUEUE) String partition,
            @Headers Map<String,Object> header){
        log.info("ReceiveMessageFrom INPUT1, message: {}, Queue:{}", payload.getPayload(), partition);
    }

    @StreamListener(InputInterface.INPUT2)
    public void receiveMessageFromChannel2(
            Person payload,
            @Header(AmqpHeaders.CONSUMER_QUEUE) String partition){
        log.info("ReceiveMessageFrom INPUT2, message: {}, Queue:{}", payload, partition);
    }

}
