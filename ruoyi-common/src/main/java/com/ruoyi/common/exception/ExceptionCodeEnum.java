package com.ruoyi.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionCodeEnum {
    // 数据操作错误定义
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    PRAM_NOT_MATCH(400, "参数不正确"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "未登录或token过期，请登录！"),
    FORBIDDEN(403, "没有相关权限"),
    NOT_FOUND(404, "没有找到相关数据"),
    ERROR(500, "系统异常"),
    CHECK_STOCK_ERROR(501, "库存盘点异常"),
            ;

    private final Integer code;
    private final String message;

    ExceptionCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }


}
