package com.ruoyi.system.controller.tool;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadImgVideoController {
    @Value("${staticFile.rootPath}")
    private String rootPath;

    @Anonymous
    @PostMapping("/imgAndVideo")
    public AjaxResult handleImgAndVideo(@RequestParam("file") MultipartFile file,@RequestParam String flag,@RequestParam String id) throws Exception{
        //准备好一个URL
        String url ="";
        String directory = getDirectory(flag);
        String filename = file.getOriginalFilename();
        if (filename != null) {
            if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg")) {
                // 这是图片
                url = FileUploadUtils.upload(RuoYiConfig.getProfile()+"/"+directory+"/"+id,
                        file, MimeTypeUtils.IMAGE_EXTENSION);
            } else if (filename.endsWith(".mp4") || filename.endsWith(".avi")) {
                // 这是MP4视频
                url = FileUploadUtils.upload(RuoYiConfig.getProfile()+"/"+directory+"/"+id,
                        file, MimeTypeUtils.VIDEO_EXTENSION);
            }else {
                throw new RuntimeException("格式不正确请重新上传");
            }
        }
        // 拼接url
    url = rootPath + url;
    //把这个url加到urlList列表里面
        return AjaxResult.success().put("data",url);
}
    //根据传来的标志，判断该文件该保存在哪个业务资源文件下
    private static String getDirectory(String flag){
        //这是构建了一个目录，这个目录在执行下面的方法时如果不存在会在actualName下会自动创建，这个名字的含义是 考察_图片_视频
        switch (flag){
            case "supplier":
                return "SUPPLIER_IMAGE_VIDEO";
            case "inspect":
                return "INSPECT_IMAGE_VIDEO";
            case "product":
                return "PRODUCT_IMAGE_VIDEO";
            case "productStandard":
                return "PRODUCT_STANDARD_IMAGE_VIDEO";
            default:
                return "OTHER";
        }
    }
}
