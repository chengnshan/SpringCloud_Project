package com.cxp.springcloudhystrix.controller.h2database;

import com.cxp.springcloudhystrix.collapser.h2database.UserCollapseH2Command;
import com.cxp.springcloudhystrix.pojo.UserInfo_H2Database;
import com.cxp.springcloudhystrix.service.CollapserH2Service;
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
public class HystrixCollapserH2Controller {

    @Autowired
    private CollapserH2Service collapserH2Service;

    @RequestMapping("/testAnnoCollapserH2")
    public void requestProducerH2Collapser() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Future<UserInfo_H2Database> u1 = collapserH2Service.annoGetUserInfoById(1);
        Future<UserInfo_H2Database> u2 = collapserH2Service.annoGetUserInfoById(2);
        Future<UserInfo_H2Database> u3 = collapserH2Service.annoGetUserInfoById(3);

        UserInfo_H2Database userInfo1 = u1.get();
        UserInfo_H2Database userInfo2 = u2.get();
        UserInfo_H2Database userInfo3 = u3.get();
        TimeUnit.SECONDS.sleep(2);
        Future<UserInfo_H2Database> u4 = collapserH2Service.annoGetUserInfoById(4);
        UserInfo_H2Database userInfo4 = u4.get();

        System.out.println("u1>>>"+userInfo1);
        System.out.println("u2>>>"+userInfo2);
        System.out.println("u3>>>"+userInfo3);
        System.out.println("u4>>>"+userInfo4);

        context.close();
    }

    @RequestMapping("/testConfigCollapserH2")
    public void requestProducerH2CollapserConfig() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        UserCollapseH2Command uc1 = new UserCollapseH2Command(collapserH2Service,1);
        UserCollapseH2Command uc2 = new UserCollapseH2Command(collapserH2Service,2);
        UserCollapseH2Command uc3 = new UserCollapseH2Command(collapserH2Service,3);
        UserCollapseH2Command uc4 = new UserCollapseH2Command(collapserH2Service,4);

        Future<UserInfo_H2Database> q1 = uc1.queue();
        Future<UserInfo_H2Database> q2 = uc2.queue();
        Future<UserInfo_H2Database> q3 = uc3.queue();

        UserInfo_H2Database userInfo1 = q1.get();
        UserInfo_H2Database userInfo2 = q2.get();
        UserInfo_H2Database userInfo3 = q3.get();
        TimeUnit.SECONDS.sleep(2);

        Future<UserInfo_H2Database> q4 = uc4.queue();
        UserInfo_H2Database userInfo4 = q4.get();

        System.out.println("u1>>>"+userInfo1);
        System.out.println("u2>>>"+userInfo2);
        System.out.println("u3>>>"+userInfo3);
        System.out.println("u4>>>"+userInfo4);

        context.close();
    }
}
