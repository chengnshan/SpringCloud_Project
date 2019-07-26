package com.cxp.springcloudhystrix.config;

import com.cxp.springcloudhystrix.pojo.UserInfo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * 继承方式
 *      开启请求缓存 与 清除请求缓存 HystrixCommand
 * @author 程
 * @date 2019/7/25 上午11:20
 */
public class MyCacheHystrixCommand extends HystrixCommand<UserInfo> {

    private RestTemplate restTemplate;
    private static Integer id;
    private String url;
    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("feignTestRest");

    public MyCacheHystrixCommand(Setter setter){
        super(setter);
    }

    public MyCacheHystrixCommand(String commandGroupKey
            ,RestTemplate restTemplate,String url,Integer id){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(commandGroupKey))
                    .andCommandKey(GETTER_KEY));
        this.restTemplate = restTemplate;
        this.url = url;
        MyCacheHystrixCommand.id = id;
    }

    @Override
    protected UserInfo run() throws Exception {
        UserInfo userInfo = restTemplate.postForObject(url, null, UserInfo.class);
        return userInfo;
    }

    @Override
    protected UserInfo getFallback() {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName("服务不可用,请求失败!");
        userInfo.setBirthday(new Date());
        return userInfo;
    }

    /**
     * 开启请求缓存，只需重载getCacheKey方法
     * 好处：    1.减少请求数、降低并发
     *          2.同一用户上下文数据一致
     *          3.这个方法会在run()和contruct()方法之前执行，减少线程开支
     * @return
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    /**
     * 清理缓存
     * 开启请求缓存之后，我们在读的过程中没有问题，但是我们如果是写，那么我们继续读之前的缓存了
     * 我们需要把之前的cache清掉
     * 说明 ：   1.其中getInstance方法中的第一个参数的key名称要与实际相同
     *          2.clear方法中的cacheKey要与getCacheKey方法生成的key方法相同
     *          3.注意我们用了commandKey是test,大家要注意之后new这个Command的时候要指定相同的commandKey,否则会清除不成功
     *
     */
    public static void flushRequestCache(Long id){
        HystrixRequestCache
                .getInstance(GETTER_KEY,HystrixConcurrencyStrategyDefault.getInstance())
                .clear(String.valueOf(id));
    }

    public static Integer getId() {
        return id;
    }
}
