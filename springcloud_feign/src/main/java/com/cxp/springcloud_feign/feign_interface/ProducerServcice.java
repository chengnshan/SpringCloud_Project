package com.cxp.springcloud_feign.feign_interface;

import com.cxp.springcloud_feign.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 程
 * @date 2019/7/24 下午6:51
 */
@FeignClient(value = "springcloud-producer")
public interface ProducerServcice {

    @RequestMapping(value = "/producer/feignTest",method = RequestMethod.POST)
    public String requestProducer(@RequestBody UserInfo userInfo);
}
