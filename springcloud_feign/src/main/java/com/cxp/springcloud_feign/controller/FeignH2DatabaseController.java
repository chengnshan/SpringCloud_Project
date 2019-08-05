package com.cxp.springcloud_feign.controller;

import com.cxp.springcloud_feign.feign_interface.ProducerH2DatabaseServcice;
import com.cxp.springcloud_feign.pojo.UserInfo_H2Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 程
 * @date 2019/7/24 下午7:00
 */
@RestController
public class FeignH2DatabaseController {

    @Autowired
    private ProducerH2DatabaseServcice producerH2DatabaseServcice;

    @RequestMapping(value = "/requestProducerH2ByFeign")
    public String requestProducerH2ByFeign(){
        UserInfo_H2Database userInfo = new UserInfo_H2Database(33,"李世民","abc123","男","唐太宗",new Date(),"");
        userInfo.setJobs("唐朝皇帝");
        String producer = producerH2DatabaseServcice.requestProducerObj(userInfo);
        return producer;
    }

    @RequestMapping(value = "/requestProducerH2Rest/{id}")
    public UserInfo_H2Database requestProducerH2Rest(@PathVariable(value = "id") Integer id){
        UserInfo_H2Database userInfo = producerH2DatabaseServcice.reqeustProducerRest(id);
        return userInfo;
    }

    @RequestMapping(value = "/reqeustProducerH2Param")
    public List<UserInfo_H2Database> reqeustProducerH2Param(String userName, String passWord){
        List<UserInfo_H2Database> userInfos = producerH2DatabaseServcice.reqeustProducerParam(userName,passWord);
        return userInfos;
    }

    @RequestMapping(value = "/reqeustProducerH2ByMap")
    public List<UserInfo_H2Database> reqeustProducerH2ByMap(@RequestParam Map<String,Object> map){
        List<UserInfo_H2Database> userInfos = producerH2DatabaseServcice.reqeustProducerByMap(map);
        return userInfos;
    }
}
