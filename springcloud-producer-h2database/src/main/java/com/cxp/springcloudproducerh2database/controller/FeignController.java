package com.cxp.springcloudproducerh2database.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxp.springcloudproducerh2database.config.ServerConfig;
import com.cxp.springcloudproducerh2database.mapper.UserInfoMapper;
import com.cxp.springcloudproducerh2database.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        log.info("feignTestRest in param: "+ id);
        UserInfo userInfoById = userInfoMapper.selectById(id);
        return userInfoById;
    }

    @RequestMapping(value = "/feignTestParam")
    public List<UserInfo> feignTestParam(@RequestParam(value = "userName") String userName,
                                         String passWord){
        log.info("feignTestParam in param1: "+ userName +",param2 : "+passWord);

        List<UserInfo> userInfos = userInfoMapper.selectList(
                new QueryWrapper<UserInfo>()
                        .lambda()
                        .eq(UserInfo::getUsername,userName)
                        .eq(UserInfo::getPassword,passWord));
        return userInfos;
    }

    @RequestMapping(value = "/getUserInfoByMap")
    public List<UserInfo> getUserInfoByMap(@RequestParam Map<String,Object> map){
        log.info("getUserInfoByMap in param1: "+ map);
        List<UserInfo> userInfoList = userInfoMapper.selectByMap(map);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userInfoList;
    }
}
