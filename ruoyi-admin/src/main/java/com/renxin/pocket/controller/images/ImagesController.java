package com.renxin.pocket.controller.images;

import cn.hutool.core.util.StrUtil;
import com.qcloud.cos.model.UploadResult;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.ExceptionUtil;
import com.renxin.common.utils.IDhelper;
import com.renxin.common.utils.cos.COSClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @User hogan
 * @Time 2022/10/19 15:42
 * @e-mail hkcugwh@163.com
 **/
@RestController
@RequestMapping("/consulted/image")
@Slf4j
public class ImagesController {

    /**
     * 获取图片
     * @param url
     * @param response
     */
    @GetMapping("/getImage")
    public void getImage(@RequestParam(value = "url") String url, HttpServletResponse response) {
        try {
            BufferedImage image = ImageIO.read(Files.newInputStream(Paths.get(url)));
            response.setHeader("Cache-Control", "no-store ,no-cache");
            response.setContentType(ContentType.IMAGE_PNG.getMimeType());
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            log.info("getImage_IOException:{}" , ExceptionUtil.getExceptionMessage(e));
            throw new ServiceException("图片下载失败");
        }

    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    @RateLimiter
    public AjaxResult uploadFile(MultipartFile file, HttpServletRequest request) throws Exception
    {
        String module = request.getHeader("module");
        String type = request.getHeader("type");

        UploadResult upload = null;
        InputStream inputStream = null;
        try
        {
            String fileName = StrUtil.format("{}_{}_{}", type, IDhelper.getNextId(), file.getOriginalFilename());
            //  调用文件服务器方法，实现文件上传改写
            inputStream = file.getInputStream();
            upload = COSClientFactory.upload(inputStream, fileName, module);
            String key = upload.getKey();
            String url = COSClientFactory.getObjUrl(key, module);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", key);
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

}
