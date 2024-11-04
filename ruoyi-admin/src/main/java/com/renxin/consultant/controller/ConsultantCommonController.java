package com.renxin.consultant.controller;

import cn.hutool.core.util.StrUtil;
import com.qcloud.cos.model.UploadResult;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.config.RuoYiConfig;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.IDhelper;
import com.renxin.common.utils.StringUtils;
import com.renxin.common.utils.cos.COSClientFactory;
import com.renxin.common.utils.file.FileUtils;
import com.renxin.framework.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用请求处理
 * 
 * @author renxin
 */
@RestController
@RequestMapping("/consultant/common")
public class ConsultantCommonController
{
    private static final Logger log = LoggerFactory.getLogger(ConsultantCommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    @RateLimiter
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    @RateLimiter
    public AjaxResult uploadFile(MultipartFile file, HttpServletRequest request)
    {
        String module = request.getHeader("module");
        String type = request.getHeader("type");

        UploadResult upload = null;
        InputStream inputStream = null;
        try
        {
            // 上传文件路径
//            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
//            String fileName = FileUploadUtils.upload(filePath, file);
            String fileName = StrUtil.format("{}_{}_{}", type, IDhelper.getNextId(), file.getOriginalFilename());
//            String url = serverConfig.getUrl() + fileName;
            //  调用文件服务器方法，实现文件上传改写
            inputStream = file.getInputStream();
            upload = COSClientFactory.upload(inputStream, fileName, module);
            String key = upload.getKey();
            String url = COSClientFactory.getObjUrl(key, module);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
//            ajax.put("fileName", fileName);
//            ajax.put("newFileName", FileUtils.getName(fileName));
//            ajax.put("originalFilename", file.getOriginalFilename());
            ajax.put("fileName", fileName);
            ajax.put("newFileName", key);
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new ServiceException("上传文件异常, fileName: " + file.getOriginalFilename() + ", module:" + module + ", type:" + type);
        }
    }

    /**
     * 通用上传文件删除
     */
    @PostMapping("/upload/delete")
    @RateLimiter
    public AjaxResult deleteFile(@RequestParam @NotNull String fileKey, @RequestParam @NotNull String module) throws Exception {
        try {
            COSClientFactory.deleteObject(module, fileKey);
            return AjaxResult.success("删除" + fileKey + "成功");
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    @RateLimiter
    public AjaxResult uploadFiles(List<MultipartFile> files, HttpServletRequest request) throws Exception
    {
        try
        {
            String module = request.getHeader("module");
            String type = request.getHeader("type");

            // 上传文件路径
//            String filePath = RuoYiConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
//                String fileName = FileUploadUtils.upload(filePath, file);
                String fileName = StrUtil.format("{}_{}_{}", type, IDhelper.getNextId(), file.getOriginalFilename());
//                String url = serverConfig.getUrl() + fileName;
                //  调用ods接口
                InputStream inputStream = file.getInputStream();
                UploadResult upload = COSClientFactory.upload(inputStream, fileName, module);
                String key = upload.getKey();
                String url = COSClientFactory.getObjUrl(key, module);
                urls.add(url);
                fileNames.add(url);
                newFileNames.add(key);
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    @RateLimiter
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
