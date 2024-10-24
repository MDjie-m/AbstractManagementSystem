package com.ruoyi.billiard.controller.wxapp;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.config.YingShiYunConfig;
import com.ruoyi.common.config.YingShiYunEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RequestMapping("api/mini-app")
public class MiniAppVideoPlayerController {

    @Autowired
    private YingShiYunConfig yingShiYunConfig;

    @Autowired
    TokenService tokenService;

    @RequestMapping("/player")
    @Anonymous
    public String test3(Model model, @RequestParam String serialNum, @RequestParam String title,@RequestParam("t") String jwtToken) {
        tokenService.verifyToken(jwtToken);
        YingShiYunEntity token = yingShiYunConfig.returnAppTokenData();
        model.addAttribute("serialNum", serialNum);
        model.addAttribute("title", title);
        model.addAttribute("accessToken", token.getAccessToken());
        return "player";
    }
}
