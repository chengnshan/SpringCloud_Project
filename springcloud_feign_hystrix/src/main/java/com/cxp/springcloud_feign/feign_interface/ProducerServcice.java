package com.cxp.springcloud_feign.feign_interface;

import com.cxp.springcloud_feign.config.FeignLogConfig;
import com.cxp.springcloud_feign.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 程
 * @date 2019/7/24 下午6:51
 */
@FeignClient(value = "springcloud-producer",configuration = {FeignLogConfig.class}
            ,fallback = ProducerServcice.ProducerServciceHystrix.class)
public interface ProducerServcice {

    /**
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/producer/feignTest",method = RequestMethod.POST)
    public String requestProducerObj(@RequestBody UserInfo userInfo);


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/producer/feignTestRest/{id}",method = RequestMethod.GET)
    public UserInfo reqeustProducerRest(@PathVariable(value = "id") Integer id);

    /**
     *
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/producer/feignTestParam",method = RequestMethod.POST)
    public List<UserInfo> reqeustProducerParam(@RequestParam(value = "userName") String userName,
                                               @RequestParam(value = "passWord") String passWord);

    @Component
    @Slf4j
    class ProducerServciceHystrix implements ProducerServcice {

        @Override
        public String requestProducerObj(UserInfo userInfo) {
            log.info("ProducerServciceHystrix实现ProducerServcice接口,方法requestProducerObj,来实现熔断!");
            return "sorry 服务不可用";
        }

        @Override
        public UserInfo reqeustProducerRest(Integer id) {
            log.info("ProducerServciceHystrix实现ProducerServcice接口,reqeustProducerRest,来实现熔断!");
            return new UserInfo();
        }

        @Override
        public List<UserInfo> reqeustProducerParam(String userName, String passWord) {
            log.info("ProducerServciceHystrix实现ProducerServcice接口来实现熔断!");
            return new ArrayList<>();
        }

    }
}
