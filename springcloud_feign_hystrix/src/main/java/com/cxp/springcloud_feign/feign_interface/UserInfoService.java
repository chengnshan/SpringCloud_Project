package com.cxp.springcloud_feign.feign_interface;

import com.cxp.springcloud_feign.config.FeignLogConfig;
import com.cxp.springcloud_feign.pojo.UserInfo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 程
 * @date 2019/7/25 下午5:34
 */
@FeignClient(value = "springcloud-producer",configuration = {FeignLogConfig.class}
        ,fallbackFactory = UserInfoService.UserInfoServiceHystrix.class)
public interface UserInfoService {

    /**
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/producer/getUserInfoByMap")
    public List<UserInfo> reqeustProducerByMap(@RequestParam Map<String,Object> map);


    @Component
    @Slf4j
    class UserInfoServiceHystrix implements FallbackFactory<UserInfoService> {

        @Override
        public UserInfoService create(Throwable cause) {
            log.info("fallback; reason was: {}", cause.getMessage());
            return new UserInfoService() {
                @Override
                public List<UserInfo> reqeustProducerByMap(Map<String, Object> map) {
                    log.info("UserInfoServiceHystrix实现FallbackFactory接口来实现熔断!");
                    return new ArrayList<>();
                }
            };
        }
    }
}
