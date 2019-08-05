package com.cxp.springcloudribbon.service;

import com.cxp.springcloudribbon.pojo.UserInfo;
import com.cxp.springcloudribbon.pojo.UserInfo_H2Database;

/**
 * @author 程
 * @date 2019/7/24 下午1:27
 */
public interface RibbonService {

    default void interfaceMethod(){
        System.out.println("这是一个默认方法!");
    }

    public String requestRibbon(String url, UserInfo userInfo);

    public String requestRibbonH2(String url, UserInfo_H2Database userInfo);
}
