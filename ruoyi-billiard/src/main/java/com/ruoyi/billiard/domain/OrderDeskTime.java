package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 订单计时对象 t_order_desk_time
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@TableName("t_order_desk_time")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeskTime extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 球桌编码 */

    @TableId("order_desk_time_id")
    private Long orderDeskTimeId;

    /** 订单编号 */
    @Excel(name = "订单编号")

    @TableField("order_id")
    private Long orderId;

    /** 球桌编码 */
    @Excel(name = "球桌编码")

    @TableField("desk_id")
    private Long deskId;

    /** 转桌之前的Id */
    @Excel(name = "转桌之前的Id")

    @TableField("from_desk_id")
    private Long fromDeskId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("start_time")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("end_time")
    private Date endTime;

    /** 总时间分钟（开始时间去掉秒，结束时间多一秒加1分钟算） */
    @Excel(name = "总时间分钟", readConverterExp = "开=始时间去掉秒，结束时间多一秒加1分钟算")

    @TableField("total_time")
    private Integer totalTime;

    /** 价格/分钟 */
    @Excel(name = "价格/分钟")

    @TableField("price")
    private BigDecimal price;

    /** 应付总金额  */
    @Excel(name = "应付总金额 ")

    @TableField("total_amount_due")
    private BigDecimal totalAmountDue;

    /** 折扣金额 */
    @Excel(name = "折扣金额")

    @TableField("total_discount_amount")
    private BigDecimal totalDiscountAmount;

    /** 实际支付金额 */
    @Excel(name = "实际支付金额")

    @TableField("total_amount")
    private BigDecimal totalAmount;

    /** 实际赠送支付金额 */
    @Excel(name = "实际赠送支付金额")

    @TableField("total_give_amount")
    private BigDecimal totalGiveAmount;

    /** 当前折扣 */
    @Excel(name = "当前折扣")

    @TableField("discount_value")
    private BigDecimal discountValue;

    /** 抹零金额 */
    @Excel(name = "抹零金额")

    @TableField("total_wipe_zero")
    private BigDecimal totalWipeZero;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;

    @TableField(exist = false)
    private StoreDesk storeDesk;

    /**
     * 状态:1=计费中，2=暂停，3=已结束
     */
    @TableField("status")
    private Integer  status;
















}
