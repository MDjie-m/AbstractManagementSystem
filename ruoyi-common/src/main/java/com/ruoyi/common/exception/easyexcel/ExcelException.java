package com.ruoyi.common.exception.easyexcel;

import lombok.Getter;


/**
 * Excel导入包含异常数据
 */
@Getter
public class ExcelException extends RuntimeException {

    protected String string;

    public ExcelException(String list) {
        this.string = list;
    }
}