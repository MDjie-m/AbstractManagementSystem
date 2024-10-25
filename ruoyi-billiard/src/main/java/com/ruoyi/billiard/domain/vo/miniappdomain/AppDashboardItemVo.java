package com.ruoyi.billiard.domain.vo.miniappdomain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppDashboardItemVo<T> {

    private String label;
    private T value;
    private String tip;
    private String valueSuffix;
}
