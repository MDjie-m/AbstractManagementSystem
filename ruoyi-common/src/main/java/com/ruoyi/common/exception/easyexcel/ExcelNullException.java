package com.ruoyi.common.exception.easyexcel;

import lombok.Getter;

import java.util.List;

/**
 * Excel导入包含异常数据
 */
@Getter
public class ExcelNullException extends RuntimeException {

    protected List<String> list;

    public ExcelNullException(List<String> list) {
        this.list = list;
    }

}
