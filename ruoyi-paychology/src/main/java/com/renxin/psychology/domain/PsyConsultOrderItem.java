package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BasePlusEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author renxin
 * @date 2023-06-26
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("psy_consult_order_item")
public class PsyConsultOrderItem extends BasePlusEntity implements Serializable
{

    private static final long serialVersionUID = 363008424382081539L;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 咨询师-结算用 */
    private Long consultId;

    /** 排班 */
    @Excel(name = "排班")
    private Long workId;

    private Integer time;

    /** 咨询时段 */
    @Excel(name = "咨询时段")
    private String timeStart;

    /** 咨询时段 */
    @Excel(name = "咨询时段")
    private String timeEnd;

    /** 天 */
    @Excel(name = "天")
    private String day;

    /** 周 */
    @Excel(name = "周")
    private String week;

    @Excel(name = "实际咨询时间")
    private String realTime;

    /** 状态  0待办  1已完成  2付费人请假   3收费人请假 */
    @Excel(name = "状态", readConverterExp = "0=待咨询,1=咨询完成")
    private String status;
}
