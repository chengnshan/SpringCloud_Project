package com.cxp.springcloudribbon.service.impl;

import com.cxp.springcloudribbon.pojo.UserInfo;
import com.cxp.springcloudribbon.service.RibbonService;
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
public class RibbonServiceImpl implements RibbonService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String requestRibbon(String url, UserInfo userInfo) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("contentType","application/json;charset=UTF-8");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<UserInfo> httpEntity = new HttpEntity<UserInfo>(userInfo,httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }
        return null;
    }
}
