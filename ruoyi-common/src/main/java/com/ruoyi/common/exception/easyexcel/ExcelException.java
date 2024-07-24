package com.ruoyi.common.exception.easyexcel;

import lombok.Getter;

/**
 * Excel异常
 * @author lyj
 * @data 2024/07/24
 */
@Getter
public class ExcelException extends RuntimeException {

    protected String string;

    public ExcelException(String string) {
        this.string = string;
    }

}
