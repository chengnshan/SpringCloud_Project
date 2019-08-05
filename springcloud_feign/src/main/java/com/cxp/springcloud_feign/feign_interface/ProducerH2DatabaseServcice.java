package com.cxp.springcloud_feign.feign_interface;

import com.cxp.springcloud_feign.config.FeignLogConfig;
import com.cxp.springcloud_feign.pojo.UserInfo_H2Database;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 程
 * @date 2019/7/24 下午6:51
 */
@FeignClient(value = "springcloud-producer-h2database",configuration = {FeignLogConfig.class})
public interface ProducerH2DatabaseServcice {

    /**
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/producer-h2database/feignTest",method = RequestMethod.POST)
    public String requestProducerObj(@RequestBody UserInfo_H2Database userInfo);


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/producer-h2database/feignTestRest/{id}",method = RequestMethod.GET)
    public UserInfo_H2Database reqeustProducerRest(@PathVariable(value = "id") Integer id);

    /**
     *
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/producer-h2database/feignTestParam",method = RequestMethod.POST)
    public List<UserInfo_H2Database> reqeustProducerParam(@RequestParam(value = "userName") String userName,
                                               @RequestParam(value = "passWord") String passWord);

    /**
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/producer-h2database/getUserInfoByMap")
    public List<UserInfo_H2Database> reqeustProducerByMap(@RequestParam Map<String, Object> map);
}
