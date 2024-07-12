package com.renxin.consulted.controller.wechat;

// 微信js-sdk相关接口

import com.renxin.common.core.domain.AjaxResult;
import com.renxin.wechat.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/consulted/wxJS")
@Slf4j
public class WxJSApiController {

    @Resource
    public WechatService wechatService;

    @PostMapping("config")
    public AjaxResult getWXJSConfig(@RequestBody Map<String, Object> map) {
        String accessToken = wechatService.getPublicAccessToken();
        if (accessToken == null) {
            return AjaxResult.error("微信JS获取token失败");
        }

        String ticket = wechatService.getPublicTicket(accessToken);
        if (ticket == null) {
            return AjaxResult.error("微信JS获取ticket失败");
        }

        if (!map.containsKey("url")) {
            return AjaxResult.error("请配置调用JS接口页面的完整URL");
        }
        String url = (String) map.get("url");
        Map<String, String> result = wechatService.makePublicSignStr(ticket, url);

        if (result.get("signature") == null) {
            return AjaxResult.error("微信JS签名失败");
        }

        return AjaxResult.success(result);
    }

}
