package com.cxp.springcloudhystrix.controller.h2database;

import com.cxp.springcloudhystrix.feignclien.FeignRequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author cheng
 * @data 2019/8/6 15:50
 */
@RestController
public class HystrixRequestHeaderController {
    
    @Autowired
    private FeignRequestHeader requestHeader;

    @RequestMapping(value = "/testFeignRequestHeader")
    public String testFeignRequestHeader(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(name);
            while (headers.hasMoreElements()){
                String value = headers.nextElement();
                System.out.println(name+"  ===  "+value);
            }
        }
        String result = requestHeader.testFeignRequestHeader();
        return result;
    }
}
