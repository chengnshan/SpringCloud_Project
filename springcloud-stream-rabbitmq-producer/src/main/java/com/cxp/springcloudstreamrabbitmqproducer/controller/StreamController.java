package com.cxp.springcloudstreamrabbitmqproducer.controller;

import com.cxp.springcloudstreamrabbitmqproducer.service.StreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 15:34
 */
@Slf4j
@RestController
public class StreamController {

    @Autowired
    private StreamService streamService;

    @GetMapping("/sendMessageByChannalName")
    public void sendMessage(@RequestParam(value = "channel",required = true)String channel,
                            @RequestParam(value = "age",required = true) Integer age ,
                            String type) {
        streamService.sendMessage(channel, age, type);
    }

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Theme-People.*'");
        System.out.println(expression.getValue());
    }
}
