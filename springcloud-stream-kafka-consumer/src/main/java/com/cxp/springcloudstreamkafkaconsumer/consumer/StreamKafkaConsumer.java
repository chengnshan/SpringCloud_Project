package com.cxp.springcloudstreamkafkaconsumer.consumer;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-03 18:25
 */
@Component
public class StreamKafkaConsumer {

    @StreamListener(value = Sink.INPUT)
    public void receiverMsg(@Payload Object obj,
                            @Headers Map<String,Object> headers){

    }
}
