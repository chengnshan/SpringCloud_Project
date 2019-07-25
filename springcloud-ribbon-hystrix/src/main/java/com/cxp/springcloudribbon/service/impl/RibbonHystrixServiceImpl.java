package com.cxp.springcloudribbon.service.impl;

import com.cxp.springcloudribbon.pojo.UserInfo;
import com.cxp.springcloudribbon.service.RibbonHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author 程
 * @date 2019/7/24 下午1:26
 */
@Service
@Slf4j
public class RibbonHystrixServiceImpl implements RibbonHystrixService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String requestRibbon(String url, UserInfo userInfo) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("contentType","application/json;charset=UTF-8");
    //    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<UserInfo> httpEntity = new HttpEntity<UserInfo>(userInfo,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }
        return null;
    }

}
