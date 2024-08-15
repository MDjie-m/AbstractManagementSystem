package com.renxin.gauge.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 咨询订单对象 psy_consulting_order
 * 
 * @author yangchuang
 * @date 2023-01-29
 */
@Data
public class PsyConsultingOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 咨询师id */
    @Excel(name = "咨询师id")
    private Integer consultantId;

    /** 0不限 1面对面 2视频 3语音 */
    @Excel(name = "0不限 1面对面 2视频 3语音")
    private Integer type;

    /** 咨询时长 */
    @Excel(name = "咨询时长")
    private Integer consultingTime;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subscribeTime;

    /** 应付费用 */
    @Excel(name = "应付费用")
    private Long price;

    /** 订单状态0待预约1代付款 2代咨询 3已完成 */
    @Excel(name = "订单状态0待预约1代付款 2代咨询 3已完成")
    private Integer status;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNum;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

}
