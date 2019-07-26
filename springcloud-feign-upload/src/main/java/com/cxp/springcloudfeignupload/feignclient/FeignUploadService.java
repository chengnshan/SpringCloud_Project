package com.cxp.springcloudfeignupload.feignclient;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 程
 * @date 2019/7/26 下午2:07
 */
@FeignClient(value = "springcloud-producer",configuration = FeignUploadService.MultipartSupportConfig.class)
public interface FeignUploadService {

    /**
     * feign上传文件
     * @param file
     * @return
     */
    @RequestMapping(value = "/producer/feignUploadFile"
            ,method = RequestMethod.POST
            ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String handleFileUpload(@RequestPart(value = "file")MultipartFile file);

    /**
     * 多个文件上传
     * @param files
     * @return
     */
    @RequestMapping(value = "/producer/feignMultiFileUpload"
            ,method = RequestMethod.POST
            ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String handleMultiFileUpload(@RequestPart(value = "files")List<MultipartFile> files);

    @Configuration
    class MultipartSupportConfig{

        @Bean
        public Encoder feignFormEncoder(){
            return new SpringFormEncoder();
        }
    }
}
