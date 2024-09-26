package com.ruoyi.billiard.domain.vo.miniappdomain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: zhoukeu
 * @dateCreated: Created in 2024-09-21:20
 * @className: HomeReportVoConsumeDetail 消费统计
 */
@Builder
@Data
public class HomeReportVoConsumeDetail  implements Serializable {

    /** 消费类型 */
    private String consumeName;

    /** 消费金额 */
    private BigDecimal consumeAmount;

    /** 消费笔数 */
    private Integer consumeCount;

    /** 消费占比 */
    private BigDecimal consumePercent;

    /** 消费明细 */
    private List<?> consumeDetail;
}
