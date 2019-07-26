package com.cxp.springcloudfeignupload.controller;

import com.cxp.springcloudfeignupload.feignclient.FeignUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author 程
 * @date 2019/7/26 下午2:11
 */
@RestController
@Slf4j
public class FeignUploadController {

    @Autowired
    private FeignUploadService feignUploadService;

    @RequestMapping(value = "/feignUploadFile")
    public void feignUploadFile(){
        //获取类加载的根路径
        URL resource = this.getClass().getResource("/");

        File file = new File(resource.getPath()+"/feignUpload.txt");
        FileItem fileItem = new DiskFileItemFactory().createItem(
                "file", MediaType.TEXT_PLAIN_VALUE, true, file.getName());

        try{
            FileInputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = fileItem.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }
        MultipartFile multi = new CommonsMultipartFile(fileItem);

        log.info(feignUploadService.handleFileUpload(multi));
    }

    @RequestMapping(value = "/feignMultiUploadFiles")
    public void feignMultiUploadFiles(){

    }
}
