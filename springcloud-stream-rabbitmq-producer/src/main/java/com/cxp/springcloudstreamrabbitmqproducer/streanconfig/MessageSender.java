package com.cxp.springcloudstreamrabbitmqproducer.streanconfig;

import com.cxp.springcloudstreamrabbitmqproducer.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 14:05
 */
@Slf4j
@Component
@EnableBinding(value = {OutputInterface.class})
public class MessageSender {

    @Autowired
    OutputInterface outputInterface;

    /**
     * 第一种方法, 没有指定output的MessageChannel, 通过OutputInterface去拿具体的Channel
     * 设置partitionKey主要是为了分区用, 可以根据根据这个partitionKey来分区
     */
    @Scheduled(fixedRate = 5000)
    public void sendMessageMethod1() {
        Message message = MessageBuilder.withPayload("From sendMessageMethod1")
                .build();
        outputInterface.output1().send(message);
    }

    /**
     * 第二种方法, 直接指定output的MessageChannel
     */
    @Autowired
    @Qualifier(value = OutputInterface.OUTPUT2)
    MessageChannel output;

    int age = 0;

    @Scheduled(fixedRate = 3000)
    public void sendMessageMethod2() {
        Person p = new Person();
        p.setName("Person2");
        p.setAge(age++);

        // 创建并发送消息
        outputInterface.output2().send(MessageBuilder.withPayload(p).build());
    }
}
