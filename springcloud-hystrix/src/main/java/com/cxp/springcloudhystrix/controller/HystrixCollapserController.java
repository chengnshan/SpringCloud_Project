package com.cxp.springcloudhystrix.controller;

import com.cxp.springcloudhystrix.collapser.UserCollapseCommand;
import com.cxp.springcloudhystrix.pojo.UserInfo;
import com.cxp.springcloudhystrix.service.CollapserService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author 程
 * @date 2019/7/28 下午4:05
 */
@RestController
public class HystrixCollapserController {

    @Autowired
    private CollapserService collapserService;

    @RequestMapping("/testAnnoCollapser")
    public void requestProducerCollapser() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Future<UserInfo> u1 = collapserService.annoGetUserInfoById(1);
        Future<UserInfo> u2 = collapserService.annoGetUserInfoById(2);
        Future<UserInfo> u3 = collapserService.annoGetUserInfoById(3);

        UserInfo userInfo1 = u1.get();
        UserInfo userInfo2 = u2.get();
        UserInfo userInfo3 = u3.get();
        TimeUnit.SECONDS.sleep(2);
        Future<UserInfo> u4 = collapserService.annoGetUserInfoById(4);
        UserInfo userInfo4 = u4.get();

        System.out.println("u1>>>"+userInfo1);
        System.out.println("u2>>>"+userInfo2);
        System.out.println("u3>>>"+userInfo3);
        System.out.println("u4>>>"+userInfo4);

        context.close();
    }

    @RequestMapping("/testConfigCollapser")
    public void requestProducerCollapserConfig() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        UserCollapseCommand uc1 = new UserCollapseCommand(collapserService,1);
        UserCollapseCommand uc2 = new UserCollapseCommand(collapserService,2);
        UserCollapseCommand uc3 = new UserCollapseCommand(collapserService,3);
        UserCollapseCommand uc4 = new UserCollapseCommand(collapserService,4);

        Future<UserInfo> q1 = uc1.queue();
        Future<UserInfo> q2 = uc2.queue();
        Future<UserInfo> q3 = uc3.queue();

        UserInfo userInfo1 = q1.get();
        UserInfo userInfo2 = q2.get();
        UserInfo userInfo3 = q3.get();
        TimeUnit.SECONDS.sleep(2);

        Future<UserInfo> q4 = uc4.queue();
        UserInfo userInfo4 = q4.get();

        System.out.println("u1>>>"+userInfo1);
        System.out.println("u2>>>"+userInfo2);
        System.out.println("u3>>>"+userInfo3);
        System.out.println("u4>>>"+userInfo4);

        context.close();
    }
}
