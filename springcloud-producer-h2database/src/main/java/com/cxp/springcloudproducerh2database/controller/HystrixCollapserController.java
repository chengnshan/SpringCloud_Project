package com.cxp.springcloudproducerh2database.controller;

import com.cxp.springcloudproducerh2database.mapper.UserInfoMapper;
import com.cxp.springcloudproducerh2database.pojo.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 程
 * @date 2019/7/28 下午3:54
 */
@RestController
public class HystrixCollapserController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/collapserById/{id}")
    public UserInfo collapserById(@PathVariable("id") Integer id){
        UserInfo userInfo = userInfoMapper.selectById(id);
        return userInfo;
    }

    @RequestMapping("/collapserMethod")
    public List<UserInfo> collapserMethod(String ids){
        System.out.println("ids>>>>>>>>>>>>>>>>>>>>>" + ids);

        List<UserInfo> list = new ArrayList<>();
        String[] arrId = ids.split(",");
        UserInfo userInfo = null;
        for (String str : arrId){
            if (StringUtils.isBlank(str)){
                continue;
            }
            userInfo = userInfoMapper.selectById(Integer.valueOf(str));
            list.add(userInfo);
        }
        return list;
    }

}
