package com.ruoyi.billiard.enums;

import com.ruoyi.common.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-23:06
 * @className: ReportTimeType 报表时间类型枚举
 */
@Getter
@AllArgsConstructor
public enum ReportTimeType implements IEnum {

    DAY(0, "当天"),
    WEEK(1, "周"),
    MONTH(2, "月"),
    YEAR(3, "年"),
    CUSTOM(4, "自定义");

    private final Integer value;
    private final String desc;
}
