package com.cxp.springcloudstreamrabbitmqproducer.service;

import com.cxp.springcloudstreamrabbitmqproducer.pojo.Person;
import com.cxp.springcloudstreamrabbitmqproducer.streanconfig.OutputInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 15:31
 */
@Service
@Slf4j
@EnableBinding(value = {OutputInterface.class})
public class StreamService {

    @Autowired
    @Qualifier(OutputInterface.OUTPUT1)
    MessageChannel channel1;

    @Autowired
    @Qualifier(OutputInterface.OUTPUT2)
    MessageChannel channel2;

    public void sendMessage(String channel) {
        String message = "Send Message Manually,From Channel:" + channel;
        log.info("Send Message from channel:" + channel);
            switch (channel) {
            case OutputInterface.OUTPUT1:
                channel1.send(MessageBuilder.withPayload(message).build());
                return;
            case OutputInterface.OUTPUT2:
                Person person = new Person("Producer", 1);
                channel2.send(MessageBuilder.withPayload(person).setHeader("partitionKey", 0).build());
                return;
            default:
                log.info("参数错误: " + channel);
                return;
        }
    }
}
