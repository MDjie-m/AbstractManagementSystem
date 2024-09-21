package com.ruoyi.billiard.controller.wxapp;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@RequestMapping("api/mini-app/login")
public class MiniAppLoginController {


//    /**
//     * 微信登录小程序授权登录
//     */
//    @Log(title = "微信登录小程序授权登录", businessType = BusinessType.GRANT)
//    @RequestMapping(value = "/authorize/program/login", method = RequestMethod.POST)
//    public CommonResult<LoginResponse> programLogin(@RequestParam String code, @RequestBody @Validated RegisterThirdUserRequest request){
//        return CommonResult.success(userCenterService.weChatAuthorizeProgramLogin(code, request));
//    }

}
