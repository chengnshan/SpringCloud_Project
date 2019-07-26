package com.cxp.springcloud_feign.controller;

import com.cxp.springcloud_feign.feign_interface.ProducerServcice;
import com.cxp.springcloud_feign.feign_interface.UserInfoService;
import com.cxp.springcloud_feign.pojo.UserInfo;
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
public class FeignController {

    @Autowired
    private ProducerServcice producerServcice;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/requestProducerByFeign")
    public String requestProducerByFeign(){
        UserInfo userInfo = new UserInfo(33,"李世民","abc123","男","唐太宗",new Date());
        userInfo.setJobs("唐朝皇帝");
        String producer = producerServcice.requestProducerObj(userInfo);
        return producer;
    }

    @RequestMapping(value = "/requestProducerRest/{id}")
    public UserInfo requestProducerRest(@PathVariable(value = "id") Integer id){
        UserInfo userInfo = producerServcice.reqeustProducerRest(id);
        return userInfo;
    }

    @RequestMapping(value = "/reqeustProducerParam")
    public List<UserInfo> reqeustProducerParam(String userName, String passWord){
        List<UserInfo> userInfos = producerServcice.reqeustProducerParam(userName,passWord);
        return userInfos;
    }

    @RequestMapping(value = "/reqeustProducerByMap")
    public List<UserInfo> reqeustProducerByMap(@RequestParam Map<String,Object> map){
        System.out.println(userInfoService);
        System.out.println(producerServcice);
        List<UserInfo> userInfos = userInfoService.reqeustProducerByMap(map);
        return userInfos;
    }
}
