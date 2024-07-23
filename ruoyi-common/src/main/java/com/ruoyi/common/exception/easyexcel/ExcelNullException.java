package com.ruoyi.common.exception.easyexcel;

import lombok.Getter;

import java.util.List;

/**
 * Excel导入包含异常数据
 */
@Getter
public class ExcelNullException extends RuntimeException {

    protected List<Integer> nullList;

    public ExcelNullException(List<Integer> nullList) {
        this.nullList = nullList;
    }

}
