package com.ruoyi.web.controller.wechat;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.SysWeChatService;
import com.ruoyi.system.domain.wechat.WxBindingReq;
import com.ruoyi.system.domain.wechat.WxCodeReq;
import com.ruoyi.system.domain.wechat.WxLoginReq;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WxController.java
 * @Author Zhengyurui
 * @Description TODO
 * @createTime 2025年01月05日 16:03:00
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private SysWeChatService sysWeChatService;

    @ApiOperation(value = "微信登录，获取授权地址", notes = "返回微信登录授权地址")
    @PostMapping(value = "/wx/login")
    public AjaxResult wxLogin(@RequestBody WxLoginReq req) {
        return AjaxResult.success(sysWeChatService.wxLogin(req));
    }

    @ApiOperation(value = "微信登录，获取登录信息", notes = "返回用登录信息")
    @PostMapping(value = "/wx/code")
    public AjaxResult wxCode(@RequestBody WxCodeReq req) throws WxErrorException {
        return AjaxResult.success(sysWeChatService.wxCode(req));
    }

    @ApiOperation(value = "微信登录，绑定用户信息", notes = "返回用登录信息")
    @PostMapping(value = "/wx/binding")
    public AjaxResult wxBinding(@RequestBody WxBindingReq req) {
        return AjaxResult.success(sysWeChatService.wxBinding(req));
    }
}
