package com.cxp.springcloudhystrix.feignclien;

import com.cxp.springcloudhystrix.config.request_config.FeignLogConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "springcloud-producer-h2database",configuration = {FeignLogConfig.class})
public interface FeignRequestHeader {

    @RequestMapping(value = "/producer-h2database/transferRequestHeader",method = RequestMethod.POST)
    public String testFeignRequestHeader();
}
