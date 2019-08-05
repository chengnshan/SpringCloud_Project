package com.cxp.springcloudproducerh2database.controller;

import com.cxp.springcloudproducerh2database.pojo.UserInfo;
import org.apache.commons.lang.StringUtils;
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

    @RequestMapping("/collapserById/{id}")
    public UserInfo collapserById(@PathVariable("id") Integer id){
        UserInfo userInfo = switchIdGetUserInfo(id);
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
            userInfo = switchIdGetUserInfo(Integer.valueOf(str));
            list.add(userInfo);
        }
        return list;
    }

    private UserInfo switchIdGetUserInfo(Integer id){
        UserInfo userInfo = null;
        switch (id){
            case 1:
                userInfo = new UserInfo(1,"user1","user1","男1","",new Date(),"");
                break;
            case 2:
                userInfo =new UserInfo(2,"user2","user2","男2","",new Date(),"");
                break;
            case 3:
                userInfo = new UserInfo(3,"user3","user3","女3","",new Date(),"");
                break;
            case 4:
                userInfo =new UserInfo(4,"user4","user4","女4","",new Date(),"");
                break;
             default:
                userInfo = new UserInfo();
        }
        return userInfo;
    }
}
