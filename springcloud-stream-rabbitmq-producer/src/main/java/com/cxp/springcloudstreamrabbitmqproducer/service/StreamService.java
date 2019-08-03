package com.cxp.springcloudstreamrabbitmqproducer.service;

import com.cxp.springcloudstreamrabbitmqproducer.pojo.Person;
import com.cxp.springcloudstreamrabbitmqproducer.pojo.StudentInfo;
import com.cxp.springcloudstreamrabbitmqproducer.streanconfig.OutputInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageHeaderAccessor;
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

    public void sendMessage(String channel,Integer age, String type) {
        String message = "Send Message Manually,From Channel:" + channel+",age = "+ age;
        log.info("Send Message from channel:" + channel);
            switch (channel) {
            case OutputInterface.OUTPUT1:
                channel1.send(MessageBuilder.withPayload(message).build());
                return;
            case OutputInterface.OUTPUT2:
                MessageHeaderAccessor accessor = new MessageHeaderAccessor();
                if (StudentInfo.class.getSimpleName().equals(type)){
                    StudentInfo studentInfo = new StudentInfo(age,"studentInfo",age);
                    accessor.setHeader("output-object","studentInfo");
                    channel2.send(MessageBuilder.withPayload(studentInfo).setHeaders(accessor).build());
                }else {
                    Person person = new Person("Producer", age);
                    accessor.setHeader("output-object","person");
                    channel2.send(MessageBuilder.withPayload(person).setHeaders(accessor).build());
                }

                return;
            default:
                log.info("参数错误: " + channel);
                return;
        }
    }
}
