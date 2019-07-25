package com.cxp.springcloud_feign.feign_interface;

import com.cxp.springcloud_feign.config.FeignLogConfig;
import com.cxp.springcloud_feign.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 程
 * @date 2019/7/24 下午6:51
 */
@FeignClient(value = "springcloud-producer",configuration = {FeignLogConfig.class})
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

    /**
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/producer/getUserInfoByMap")
    public List<UserInfo> reqeustProducerByMap(@RequestParam Map<String,Object> map);
}
