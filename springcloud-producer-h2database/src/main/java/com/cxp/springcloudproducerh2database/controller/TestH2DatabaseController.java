package com.cxp.springcloudproducerh2database.controller;

import com.cxp.springcloudproducerh2database.mapper.UserInfoMapper;
import com.cxp.springcloudproducerh2database.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cheng
 * @data 2019/8/5 10:09
 */
@RestController
public class TestH2DatabaseController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "testH2DatabaseMethod")
    public List<UserInfo> testH2DatabaseMethod(@RequestParam Map<String,Object> map){
        System.out.println("testH2DatabaseMethod in param : "+map);
        Map<String, Object> columnMap=new HashMap<>();
        columnMap.put("username","Jone");
        List<UserInfo> userInfos = userInfoMapper.selectByMap(columnMap);
        return userInfos;
    }
}
