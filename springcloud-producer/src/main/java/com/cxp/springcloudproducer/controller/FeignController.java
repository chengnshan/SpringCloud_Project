package com.cxp.springcloudproducer.controller;

import com.cxp.springcloudproducer.config.ServerConfig;
import com.cxp.springcloudproducer.mapper.UserInfoMapper;
import com.cxp.springcloudproducer.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 程
 * @date 2019/7/24 下午7:04
 */
@RestController
@Slf4j
public class FeignController {

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/feignTest")
    public String feignTest(@RequestBody UserInfo userInfo){
        log.info("feignTest in param: "+ userInfo);
        return String.format("feignTest 收到信息!我是端口： %s",serverConfig.getServerPort());
    }

    @RequestMapping(value = "/feignTestRest/{id}")
    public UserInfo feignTestRest(@PathVariable(value = "id") Integer id){
        log.info("feignTest in param: "+ id);
        UserInfo userInfoById = userInfoMapper.getUserInfoById(id);
        return userInfoById;
    }

    @RequestMapping(value = "/feignTestParam")
    public List<UserInfo> feignTestParam(@RequestParam(value = "userName") String userName,
                                         String passWord){
        log.info("feignTest in param1: "+ userName +",param2 : "+passWord);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassWord(passWord);
        List<UserInfo> userInfos = userInfoMapper.findUserInfoList(userInfo);
        return userInfos;
    }

    @RequestMapping(value = "/getUserInfoByMap")
    public List<UserInfo> getUserInfoByMap(@RequestParam Map<String,Object> map){
        log.info("feignTest in param1: "+ map);
        List<UserInfo> userInfoList = userInfoMapper.findUserInfoListByMap(map);
        return userInfoList;
    }
}
