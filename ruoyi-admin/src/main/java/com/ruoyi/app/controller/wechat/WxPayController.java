package com.ruoyi.app.controller.wechat;

import com.ruoyi.app.controller.wechat.utils.WechatPayV3Utils;
import com.ruoyi.common.constant.RespMessageConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.dto.LoginDTO;
import com.ruoyi.common.core.domain.dto.WxPayDTO;
import com.ruoyi.framework.web.service.AppTokenService;
import com.ruoyi.web.controller.common.CommonCosController;
import com.ruoyi.wechat.service.IWxpayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson2.JSONObject;

/**
 * @User hogan
 * @Time 2022/10/20 19:40
 * @e-mail hkcugwh@163.com
 **/
@RestController
@RequestMapping("/app/wxPay")
@Slf4j
@Api(value = "WxPayController" ,tags = {"微信支付api"})
public class WxPayController {

    @Autowired
    private IWxpayService wxpayService;

    @Autowired
    public WechatPayV3Utils wechatPayV3Utils;

    /**
     * 微信支付
     */
    @ApiOperation(value = "微信支付")
    @PostMapping("pay")
    public AjaxResult pay(@RequestBody WxPayDTO wxPayDTO , HttpServletRequest request){
        LoginDTO loginUser = AppTokenService.getInstance().getLoginUser(request);
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS ,wxpayService.pay(wxPayDTO ,loginUser));
    }

    @ApiOperation(value = "微信支付回调")
    @PostMapping("callback")
    public AjaxResult payCallBack(@RequestBody WxPayDTO wxPayDTO , HttpServletRequest request){
        LoginDTO loginUser = AppTokenService.getInstance().getLoginUser(request);
        JSONObject callBackJson = wechatPayV3Utils.getCallbackData(request);
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS ,wxpayService.payCallBack(wxPayDTO ,loginUser));
    }
}

