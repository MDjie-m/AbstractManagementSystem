package com.ruoyi.web.controller.login;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.framework.config.properties.GiteeLoginProperties;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.system.domain.LoginByOtherSourceBody;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Version v1.0
 * @Author zhou
 * @Date 2024/8/15
 * @Description
 * 第三方认证的本质就是后端想从第三方手里取得当前登录用户的信息
 * 主要步骤：
 * 1. 前端发起请求，想要通过指定第三方登录
 * 2. 后端收到请求后就向第三方也发起授权请求，并获得授权地址（后端的uuid自动生成是为了防止csrf攻击）
 * 3. 前端收到uuid存入到cookie中，并重定向到授权地址
 * 4. 前端认证完毕后，第三方又会让浏览器携带“授权码”以及uuid重定向到后端预先设置好的回调地址
 * 5. 前端会在当前回调的页面携带uuid以及“授权码”向后端发起请求
 * 6. 后端接收到uuid以及“授权码”就能再次向第三方发起请求获取当前用户信息
 */
@RestController
public class GiteeLoginController {
    @Resource
    private SysLoginService loginService;

    @Autowired
    private GiteeLoginProperties giteeLoginProperties;

    @GetMapping("/PreLoginByGitee")
    public AjaxResult PreLoginByGitee() {
        AjaxResult ajax = AjaxResult.success();
        AuthRequest authRequest = new AuthGiteeRequest(AuthConfig.builder()
                .clientId(giteeLoginProperties.getClientId()) // 标识是哪个应用发起请求
                .clientSecret(giteeLoginProperties.getClientSecret())
                .redirectUri(giteeLoginProperties.getRedirectUri())
                .build());
        String uuid = IdUtils.fastUUID();
        // 向gitee发起用户需授权请求
        String authorizeUrl = authRequest.authorize(uuid);

        ajax.put("authorizeUrl", authorizeUrl); // 此url就是gitee的授权地址，需要前端跳转到该地址
        ajax.put("uuid", uuid); // 此随机参数是为了防止csrf攻击，需要前端传入
        return ajax;
    }



    @PostMapping("/loginByGitee")
    public AjaxResult loginByGitee(@RequestBody LoginByOtherSourceBody loginByOtherSourceBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = loginService
                .loginByOtherSource(loginByOtherSourceBody.getCode(), loginByOtherSourceBody.getSource(), loginByOtherSourceBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}
