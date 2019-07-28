package com.cxp.springcloudhystrix.collapser;

import com.cxp.springcloudhystrix.pojo.UserInfo;
import com.cxp.springcloudhystrix.service.CollapserService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 从HystrixCollapser抽象类的定义中可以看到，它指定了三个不同的类型：
 *      BatchReturnType：合并后批量请求的返回类型
 *      ResponseType：单个请求返回的类型
 *      RequestArgumentType：请求参数类型
 *
 * @author 程
 * @date 2019/7/28 下午5:41
 */
public class UserCollapseCommand extends HystrixCollapser<List<UserInfo>, UserInfo, Integer> {

    private CollapserService collapserService;
    private Integer userId;

    public UserCollapseCommand(CollapserService collapserService, Integer userId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand"))
        //timerDelayInMilliseconds 触发批处理的延迟，也可以为创建批处理的时间＋该值，默认10
        .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100))
        //maxRequestsInBatch 单次批处理的最大请求数，达到该数量触发批处理，默认Integer.MAX_VALUE
        .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withMaxRequestsInBatch(100)));
        this.collapserService = collapserService;
        this.userId = userId;
    }
    /**
     * 该函数用来定义获取请求参数的方法
     * @return
     */
    @Override
    public Integer getRequestArgument() {
        return userId;
    }

    /**
     * 合并请求产生批量命令的具体实现方法。
     * @param collapsedRequests 参数则代表了每个被合并的请求
     * @return
     */
    @Override
    protected HystrixCommand<List<UserInfo>> createCommand(Collection<CollapsedRequest<UserInfo, Integer>> collapsedRequests) {
        List<Integer> userIds = new ArrayList<>(collapsedRequests.size());
        userIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(collapserService,userIds);
    }

    /**
     * 批量命令结果返回后的处理，这里需要实现将批量结果拆分并传递给合并前的各个原子请求命令的逻辑
     * @param batchResponse
     * @param collapsedRequests
     */
    @Override
    protected void mapResponseToRequests(List<UserInfo> batchResponse, Collection<CollapsedRequest<UserInfo, Integer>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<UserInfo, Integer> collapsedRequest:collapsedRequests){
            UserInfo userInfo = batchResponse.get(count++);
            collapsedRequest.setResponse(userInfo);
        }
    }
}
