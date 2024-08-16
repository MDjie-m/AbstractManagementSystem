package com.renxin.course.domain.dto;

import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseQueryDTO extends BaseEntity {
    private String userName;
    private String nameValue;
    private Integer typeValue;
    private Integer payTypeValue;
    private String authorValue;
    private BigDecimal lowPrice;
    private BigDecimal highPrice;

    private Integer onSaleValue;
    
    /** 服务对象  1.普通用户  2.咨询师 */
    private Integer serviceTo;
}
