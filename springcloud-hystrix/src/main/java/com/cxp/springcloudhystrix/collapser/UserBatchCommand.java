package com.cxp.springcloudhystrix.collapser;

import com.cxp.springcloudhystrix.pojo.UserInfo;
import com.cxp.springcloudhystrix.service.CollapserService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

/**
 *  请求合并时执行批量方法实现
 * @author 程
 * @date 2019/7/28 下午6:18
 */
public class UserBatchCommand extends HystrixCommand<List<UserInfo>> {

    CollapserService collapserService;
    List<Integer> userIds;

    public UserBatchCommand(CollapserService collapserService, List<Integer> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCommand")));
        this.userIds = userIds;
        this.collapserService = collapserService;
    }

    @Override
    protected List<UserInfo> run() throws Exception {
        return collapserService.batchUserInfos(userIds);
    }

}
