package com.cxp.springcloudribbon.service;

import com.cxp.springcloudribbon.pojo.UserInfo;

/**
 * @author 程
 * @date 2019/7/24 下午1:27
 */
public interface RibbonHystrixService {

    default void interfaceMethod(){
        System.out.println("这是一个默认方法!");
    }

    public String requestRibbon(String url, UserInfo userInfo);
}
