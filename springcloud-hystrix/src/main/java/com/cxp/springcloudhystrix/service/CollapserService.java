package com.cxp.springcloudhystrix.service;

import com.cxp.springcloudhystrix.pojo.UserInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 *  注解 实现 Hystrix 请求合并
 * @author 程
 * @date 2019/7/28 下午3:48
 */
@Service
public class CollapserService {

    @Autowired
    private RestTemplate restTemplate;

    public UserInfo getUserInfoById(Integer id){
        UserInfo userInfo = restTemplate.getForObject(
                "http://springcloud-producer/producer/collapserById/{1}",
                UserInfo.class, id);
        return userInfo;
    }

    public List<UserInfo> batchUserInfos(List<Integer> ids){
        System.out.println("batchUserInfos----"+ids+"Thread.currentThread().getName():" +
                Thread.currentThread().getName());
        UserInfo[] userInfos = restTemplate.getForObject("http://springcloud-producer/producer/collapserMethod?ids={1}",
                UserInfo[].class, StringUtils.join(ids, ","));
        return Arrays.asList(userInfos);
    }

    @HystrixCollapser(batchMethod = "annoBatchUserInfos",collapserProperties = {
            //timerDelayInMilliseconds 触发批处理的延迟，也可以为创建批处理的时间＋该值，默认10
            @HystrixProperty(name="timerDelayInMilliseconds",value = "200"),
            //maxRequestsInBatch 单次批处理的最大请求数，达到该数量触发批处理，默认Integer.MAX_VALUE
            @HystrixProperty(name="maxRequestsInBatch",value = "10")
    })
    public Future<UserInfo> annoGetUserInfoById(Integer id){
        System.out.println("执行单个获取的方法  ==  "+ id);
        return null;
    }

    @HystrixCommand
    public List<UserInfo> annoBatchUserInfos(List<Integer> ids){
        System.out.println("发送请求。。。参数为："+ids.toString()+Thread.currentThread().getName());
        UserInfo[] userInfos = restTemplate.getForObject("http://springcloud-producer/producer/collapserMethod?ids={1}",
                UserInfo[].class, StringUtils.join(ids, ","));
        System.out.println(Arrays.toString(userInfos));
        return Arrays.asList(userInfos);
    }
}
