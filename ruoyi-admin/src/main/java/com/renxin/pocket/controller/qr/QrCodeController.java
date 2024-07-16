package com.renxin.pocket.controller.qr;


import com.qcloud.cos.model.UploadResult;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.utils.QrCodeUtil;
import com.renxin.common.utils.cos.COSClientFactory;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/consulted/gauge/qr")
@Api(value = "测评结果模块", tags = {"二维码工具"})
public class QrCodeController {

    /**
    生成二维码文件
     */
    @PostMapping("/getCode")
    public AjaxResult getCode(@RequestBody Map<String, Object> map) throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String qrCodeParam = map.get("qrCodeParam").toString();
        Map<String , String> res = new HashMap<>();
        String qrCodePath = new QrCodeUtil().getCode(qrCodeParam);
        File file  = new File(qrCodePath);
        InputStream in = new FileInputStream(file);
        UploadResult upload = COSClientFactory.upload(in, uuid+".png", "");
        String key = upload.getKey();
        String url = COSClientFactory.getObjUrl(key, "");
        AjaxResult ajax = AjaxResult.success();
        ajax.put("url", url);
//        res.put("images_url", "http://wx.ssgpsy.com"+"/prod-api" + qrCodePath);
        res.put("images_url", url);
        return AjaxResult.success(res);
    }

    /**
     生成二维码
     */
    @PostMapping("/getBase64Code")
    public AjaxResult getBase64Code(@RequestBody Map<String, Object> map) throws IOException {
        String qrCodeParam = map.get("qrCodeParam").toString();
        Map<String , String> res = new HashMap<>();
        String base64Code = new QrCodeUtil().getBase64Code(qrCodeParam);
        res.put("images_url", "data:image/png;base64," + base64Code);
        return AjaxResult.success(res);
    }

}
