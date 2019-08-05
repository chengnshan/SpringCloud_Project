package com.cxp.springcloudproducerh2database.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author 程
 * @date 2019/7/26 下午2:20
 */
@RestController
@Slf4j
public class FeignUploadController {

    @PostMapping(value = "/feignUploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String feignUploadFile(@RequestPart(value = "file") MultipartFile file) {
        log.info("handleFileUpload : file = "+file!= null ? file.getOriginalFilename():"空文件");
        if (file!= null){
            String filename = file.getOriginalFilename();
            String fileSuffix = filename.substring(filename.lastIndexOf("."), filename.length());
            if (fileSuffix.equals(".txt")){
                InputStream inputStream = null;
                BufferedReader bufferedReader = null;
                try {
                    inputStream = file.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine()) != null){
                        sb.append(line);
                    }
                    log.info(String.format("文件内容：%s ",sb.toString()));

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream!= null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return file.getOriginalFilename();
        }
        return null;
    }

    @PostMapping(value = "/feignMultiFileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String feignMultiFileUpload(@RequestPart(value = "files") List<MultipartFile> files) {

        return null;
    }
}
