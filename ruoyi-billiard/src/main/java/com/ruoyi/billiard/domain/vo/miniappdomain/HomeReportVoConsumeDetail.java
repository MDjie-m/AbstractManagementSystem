package com.ruoyi.billiard.domain.vo.miniappdomain;

import com.ruoyi.billiard.enums.OrderType;
import com.ruoyi.common.annotation.Excel;
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
    @Excel(name = "消费类型名称")
    private String consumeName;

    /** 消费金额 */
    @Excel(name = "消费金额")
    private BigDecimal consumeAmount;

    /** 消费笔数 */
    @Excel(name = "消费笔数")
    private Integer consumeCount;

    /** 消费占比 */
    @Excel(name = "消费占比", suffix = "%")
    private BigDecimal consumePercent;

    /** 消费明细 */
    private List<?> consumeDetail;

    /** 订单类型 */
    private OrderType orderType;
}
