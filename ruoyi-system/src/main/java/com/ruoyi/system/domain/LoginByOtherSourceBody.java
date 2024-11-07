package com.ruoyi.system.domain;

/**
 * @Version v1.0
 * @Author zhou
 * @Date 2024/8/15
 * @Description 接收第三方登录的请求体
 */
public class LoginByOtherSourceBody {
    private String code;
    private String source;
    private String uuid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
