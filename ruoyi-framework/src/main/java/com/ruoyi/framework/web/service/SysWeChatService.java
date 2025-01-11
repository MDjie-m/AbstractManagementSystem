package com.ruoyi.framework.web.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.config.weChat.WeChatConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.wechat.LoginAuthTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.JsonUtil;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.domain.wechat.BaseWxBiz;
import com.ruoyi.system.domain.wechat.WxBindingReq;
import com.ruoyi.system.domain.wechat.WxCodeReq;
import com.ruoyi.system.domain.wechat.WxLoginReq;
import com.ruoyi.system.domain.wechat.resp.UsersLoginResp;
import com.ruoyi.system.domain.wechat.resp.WxCodeResp;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpMapConfigImpl;
import me.chanjar.weixin.open.api.impl.WxOpenOAuth2ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SysWeChatServiceImpl.java
 * @Author Zhengyurui
 * @Description TODO
 * @createTime 2025年01月05日 16:45:00
 */
@Service
@Slf4j
public class SysWeChatService {

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private BaseWxBiz baseWxBiz;


    public String wxLogin(WxLoginReq req) {
        WeChatConfig weChatConfig = configService.selectWeChatConfig();
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.PC.getCode())) {
            if (!weChatConfig.getWxPcLoginEnable().equals("1")) {
                throw new ServiceException("网页应用登录没开启");
            }
            WxOAuth2Service wxOAuth2Service = new WxOpenOAuth2ServiceImpl(weChatConfig.getWxPcLoginAppId(), weChatConfig.getWxPcLoginAppSecret());
            String authorizationUrl = wxOAuth2Service.buildAuthorizationUrl(req.getRedirectUrl(), WxConsts.QrConnectScope.SNSAPI_LOGIN, LoginAuthTypeEnum.PC.name());
            return authorizationUrl;
        }
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MP.getCode())) {
            if (!weChatConfig.getWxMpLoginEnable().equals("1")) {
                throw new ServiceException("公众号登录没开启");
            }
            WxMpService wxMpService = new WxMpServiceImpl();
            WxMpMapConfigImpl mpMapConfig = new WxMpMapConfigImpl();
            mpMapConfig.setAppId(weChatConfig.getWxMpLoginAppId());
            mpMapConfig.setSecret(weChatConfig.getWxMpLoginAppSecret());
            wxMpService.setWxMpConfigStorage(mpMapConfig);
            String authorizationUrl = wxMpService.getOAuth2Service().buildAuthorizationUrl(req.getRedirectUrl(), WxConsts.OAuth2Scope.SNSAPI_USERINFO, LoginAuthTypeEnum.MP.name());
            return authorizationUrl;
        }
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MA.getCode())) {
            if (!weChatConfig.getWxMaLoginEnable().equals("1")) {
                throw new ServiceException("小程序登录没开启");
            }
            // todo 微信小程序登录
        }
        log.error("登录类型暂没支持={}", JsonUtil.toJsonString(req));
        return "登录类型暂没支持";
    }

    public WxCodeResp wxCode(WxCodeReq req) throws WxErrorException {
        WeChatConfig weChatConfig = configService.selectWeChatConfig();
        WxCodeResp codeResp = new WxCodeResp();
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.PC.getCode())) {
            // 网页应用
            codeResp.setUserInfo(baseWxBiz.getAuthInfo(weChatConfig.getWxPcLoginAppId(), weChatConfig.getWxPcLoginAppSecret(), req.getCode()));
        } else if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MP.getCode())) {
            // 公众号
            codeResp.setUserInfo(baseWxBiz.getAuthInfo(weChatConfig.getWxMpLoginAppId(), weChatConfig.getWxMpLoginAppSecret(), req.getCode()));
        } else if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MA.getCode())) {
            // 小程序
            WxMaService wxMaService = new WxMaServiceImpl();
            WxMaDefaultConfigImpl wxMaConfig = new WxMaDefaultConfigImpl();
            wxMaConfig.setAppid(weChatConfig.getWxMaLoginAppId());
            wxMaConfig.setSecret(weChatConfig.getWxMaLoginAppSecret());
            wxMaService.setWxMaConfig(wxMaConfig);
        }

        // 根据unionId或openId查询用户信息
        SysUser user = userService.getByUnionIdOrOpenId(codeResp.getUserInfo().getUnionId(), codeResp.getUserInfo().getOpenid());
        if (ObjectUtil.isNull(user)) {
            // 没绑定
            codeResp.setBindingStatus(false);
            // 缓存用户信息
            if (StringUtils.hasText(codeResp.getUserInfo().getUnionId())) {
                redisCache.setCacheObject(Constants.RedisPre.WX_USER + codeResp.getUserInfo().getUnionId(), codeResp.getUserInfo(), 1, TimeUnit.DAYS);
            } else {
                redisCache.setCacheObject(Constants.RedisPre.WX_USER + codeResp.getUserInfo().getOpenid(), codeResp.getUserInfo(), 1, TimeUnit.DAYS);
            }
            return codeResp;
        }

        // 已经绑定
        codeResp.setBindingStatus(true);
        // 登录
        UsersLoginResp loginResp = login(user);
        codeResp.setMobile(loginResp.getMobile());
        codeResp.setToken(loginResp.getToken());
        return codeResp;
    }

    public UsersLoginResp wxBinding(WxBindingReq req) {
        if (!StringUtils.hasText(req.getMobile())) {
            throw new ServiceException("手机号不能为空");
        }
        // 验证码校验
        String redisCode = redisCache.getCacheObject(Constants.RedisPre.CODE + req.getMobile());
        if (!StringUtils.hasText(redisCode)) {
            throw new ServiceException("验证码已经过期");
        }
        if (!req.getCode().equals(redisCode)) {
            throw new ServiceException("验证码不正确");
        }
        // 删除验证码缓存
        redisCache.deleteObject(Constants.RedisPre.CODE + req.getMobile());

        // 手机号重复校验
        SysUser user = userService.getByPhoneNumber(req.getMobile());
        if (ObjectUtil.isNotNull(user)) {
            if (StringUtils.hasText(user.getUnionId()) || StringUtils.hasText(user.getOpenId())) {
                throw new ServiceException("该手机号已绑定，请更换其他手机号");
            }
            SysUser newUser = new SysUser();
            newUser.setUserId(user.getUserId());
            newUser.setUnionId(req.getUnionId());
            newUser.setOpenId(req.getOpenId());
            userService.updateUser(newUser);

            return login(user);
        }

        // 用户注册
        RegisterBody registerBody = new RegisterBody();
        registerBody.setUsername(req.getMobile());
        registerBody.setPassword(IdUtil.fastUUID());
        registerBody.setCode(req.getCode());
        registerBody.setUnionId(req.getUnionId());
        registerBody.setOpenId(req.getOpenId());

        String msg = registerService.register(registerBody);
        if (com.ruoyi.common.utils.StringUtils.isNotEmpty(msg)) {
            throw new ServiceException(msg);
        }
        user = userService.getByPhoneNumber(req.getMobile());

        return login(user);
    }


    private UsersLoginResp login(SysUser user) {
        // 记录登录信息
        user.setLoginIp(IpUtils.getIpAddr());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(user);
        // 生成token
        LoginUser loginUser = new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
        String token = tokenService.createToken(loginUser);
        return new UsersLoginResp().setToken(token).setMobile(user.getPhonenumber());
    }


}
