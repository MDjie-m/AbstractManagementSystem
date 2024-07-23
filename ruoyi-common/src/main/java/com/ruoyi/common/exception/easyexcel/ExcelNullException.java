package com.ruoyi.common.exception.easyexcel;

import lombok.Getter;

import java.util.Map;

/**
 * Excel导入包含异常数据
 */
@Getter
public class ExcelNullException extends RuntimeException {

    protected Map<Integer,String> cellData;

    public ExcelNullException(Map<Integer,String> cellData) {
        this.cellData = cellData;
    }

}
