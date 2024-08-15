package com.renxin.psychology.vo;

import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author renxin
 * @date 2023-06-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PsyConsultOrderItemVO extends BaseValObj implements Serializable
{

    private static final long serialVersionUID = 4916488575621974615L;

    /** 订单号 */
    private Long orderId;

    /** 咨询师-结算用 */
    private Long consultId;

    /** 排班 */
    private Long workId;

    private Integer time;

    /** 咨询时段 */
    private String timeStart;

    /** 咨询时段 */
    private String timeEnd;

    /** 天 */
    private String day;

    /** 周 */
    private String week;

    /** 状态  0待办  1已完成  2付费人请假   3收费人请假 */
    private String status;

    private String realTime;

}
