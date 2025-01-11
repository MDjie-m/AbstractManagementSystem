package com.ruoyi.common.config.weChat;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName WeChatConfig.java
 * @Author Zhengyurui
 * @Description TODO
 * @createTime 2025年01月05日 17:09:00
 */
@Data
@Accessors(chain = true)
public class WeChatConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公钥
     */
    private String rsaLoginPublicKey;

    /**
     * 私钥
     */
    private String rsaLoginPrivateKey;

    /**
     * 微信登录(网站应用)
     */
    private String wxPcLoginEnable;

    /**
     * 网站应用App ID
     */
    private String wxPcLoginAppId;

    /**
     * 网站应用App Secret
     */
    private String wxPcLoginAppSecret;

    /**
     * 微信登录(公众号)
     */
    private String wxMpLoginEnable;

    /**
     * 公众号App ID
     */
    private String wxMpLoginAppId;

    /**
     * 公众号App Secret
     */
    private String wxMpLoginAppSecret;

    /**
     * 微信登录(小程序)
     */
    private String wxMaLoginEnable;

    /**
     * 小程序App ID
     */
    private String wxMaLoginAppId;

    /**
     * 小程序App Secret
     */
    private String wxMaLoginAppSecret;
}
