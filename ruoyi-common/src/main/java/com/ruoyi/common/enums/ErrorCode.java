package com.ruoyi.common.enums;

/**
 * @ClassNAME ErrorCode
 * @Description TODO
 * @Author czh
 * @Date 2024/10/12 23:48
 * @Version 1.0
 */

public enum ErrorCode {
    CAPTCHA_ERROR(1001, "验证码开关状态配置错误");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

