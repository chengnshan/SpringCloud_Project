package com.cxp.springcloud_feign.controller;

import com.cxp.springcloud_feign.feign_interface.ProducerServcice;
import com.cxp.springcloud_feign.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 程
 * @date 2019/7/24 下午7:00
 */
@RestController
public class FeignController {

    @Autowired
    private ProducerServcice producerServcice;

    @RequestMapping(value = "")
    public String requestProducerByFeign(){
        UserInfo userInfo = new UserInfo(33,"李世民","abc123","男","唐太宗",new Date());
        userInfo.setJobs("唐朝皇帝");
        String producer = producerServcice.requestProducer(userInfo);
        return producer;
    }
}
