package com.renxin.gauge.domain;

import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 测评标签对象 psy_gauge_label
 * 
 * @author renxin
 * @date 2022-10-18
 */
@Data
public class PsyGaugeLabel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 量表id */
//    @Excel(name = "量表id")
    private Long gaugeId;

    private Integer sort;

    @Excel(name = "量表名称")
    private String gaugeName;

    /** 量表标签(0-精选测评，1-热门推荐) */
    @Excel(name = "量表标签(0-精选测评，1-热门推荐)")
    private Integer label;
}
