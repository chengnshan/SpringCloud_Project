package com.cxp.springcloudhystrix.collapser.h2database;

import com.cxp.springcloudhystrix.pojo.UserInfo_H2Database;
import com.cxp.springcloudhystrix.service.CollapserH2Service;
import com.cxp.springcloudhystrix.service.CollapserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

/**
 *  请求合并时执行批量方法实现
 * @author 程
 * @date 2019/7/28 下午6:18
 */
public class UserBatchH2Command extends HystrixCommand<List<UserInfo_H2Database>> {

    CollapserH2Service collapserH2Service;
    List<Integer> userIds;

    public UserBatchH2Command(CollapserH2Service collapserH2Service, List<Integer> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCommand")));
        this.userIds = userIds;
        this.collapserH2Service = collapserH2Service;
    }

    @Override
    protected List<UserInfo_H2Database> run() throws Exception {
        return collapserH2Service.batchUserInfos(userIds);
    }

}
