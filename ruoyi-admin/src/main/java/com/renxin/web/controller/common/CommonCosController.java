package com.renxin.web.controller.common;

import com.qcloud.cos.model.UploadResult;
import com.renxin.common.utils.cos.COSClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * 通用请求处理
 * 
 * @author renxin
 */
@RestController
@RequestMapping("/consulted/cos")
public class CommonCosController
{
    private static final Logger log = LoggerFactory.getLogger(CommonCosController.class);

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public UploadResult fileDownload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
    {
        UploadResult upload = null;
        InputStream inputStream = null;
        String module;
        if (request.getHeader("module") == null) {
           module = null;
        } else {
            module = request.getHeader("module");
        }
        try {
            inputStream = file.getInputStream();
            upload = COSClientFactory.upload(inputStream, file.getOriginalFilename(), module);
        } catch (IOException e) {
            log.error("文件上传失败：{}" ,e);
        }
        return upload;
    }

    @GetMapping("/getUrl/{key}")
    public String getUrl(@PathVariable("key")String key, @RequestParam("module") String module){
        return COSClientFactory.getObjUrl(key, module);
    }

}
