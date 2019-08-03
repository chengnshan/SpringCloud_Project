package com.cxp.springcloudstreamrabbitmqconsumer.consumeconfig;

import com.cxp.springcloudstreamrabbitmqconsumer.pojo.Person;
import com.cxp.springcloudstreamrabbitmqconsumer.pojo.StudentInfo;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
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
            @Headers Map<String,Object> header,
            @Header(AmqpHeaders.CHANNEL) Channel channel) throws IOException {
        log.info("ReceiveMessageFrom INPUT1, message: {}, Queue:{}", payload.getPayload(), partition);
        Long deliveryTag = (Long) header.get(AmqpHeaders.DELIVERY_TAG);
        log.info("receiveMessageFromChannel1 deliveryTag: "+deliveryTag);
        Channel channel1 = (Channel) header.get(AmqpHeaders.CHANNEL);
        //手动确认消息
        channel.basicNack(deliveryTag,false,false);
    }

    /**
     * condition注释的参数中的SpEL表达式指定，并针对每条消息进行评估。与条件匹配的所有处理程序都在同一个线程中调用，并且不必假设调用发生的顺序
     * @param payload
     * @param partition
     * @param header
     */
    @StreamListener(value = InputInterface.INPUT2,condition = "headers['output-object']=='person'")
    public void receiveMessageFromChannel2(
            @Payload Message<Person> payload,
            @Header(AmqpHeaders.CONSUMER_QUEUE) String partition,
            @Headers Map<String,Object> header) throws IOException {
        log.info("ReceiveMessageFrom INPUT2, message: {}, Queue:{}", payload, partition);
        Person payload1 = payload.getPayload();
        if (payload!= null && payload1.getAge() % 2 ==0){
            throw new RuntimeException("Message consumer failed!");
        }
        Channel channel1 = (Channel) header.get(AmqpHeaders.CHANNEL);
        channel1.basicNack((Long) header.get(AmqpHeaders.DELIVERY_TAG),false,false);
    }

    @StreamListener(value = InputInterface.INPUT2,condition = "headers['output-object']=='studentInfo'")
    public void receiveMessageFromChannel3(
            @Payload Message<StudentInfo> payload,
            @Header(AmqpHeaders.CONSUMER_QUEUE) String partition,
            @Headers Map<String,Object> header) throws IOException {
        log.info("ReceiveMessageFrom INPUT2, message: {}, Queue:{}", payload, partition);
        StudentInfo studentInfo = payload.getPayload();
        System.out.println(studentInfo);
        Channel channel1 = (Channel) header.get(AmqpHeaders.CHANNEL);
        channel1.basicNack((Long) header.get(AmqpHeaders.DELIVERY_TAG),false,false);
    }
}
