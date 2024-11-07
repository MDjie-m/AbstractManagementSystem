package com.ruoyi.framework.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Version v1.0
 * @Author zhou
 * @Date 2024/8/15
 * @Description
 */
@ConfigurationProperties(prefix = "login.gitee")
public class GiteeLoginProperties {
    public static final String DEFAULT_CLIENT_ID = "f775e3385de07bfc81d685a440aab11ccf4d34920ec05caebca40b0e930ef458";
    public static final String DEFAULT_CLIENT_SECRET = "36e36924e85029e474b1ad4d92ea519d29defd699743bd660afc9ee9129e1eda";

    public static final String DEFAULT_REDIRECT_URI = "http://localhost:81/callback";
    private String clientId = DEFAULT_CLIENT_ID;
    private String clientSecret = DEFAULT_CLIENT_SECRET;
    private String redirectUri = DEFAULT_REDIRECT_URI;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
