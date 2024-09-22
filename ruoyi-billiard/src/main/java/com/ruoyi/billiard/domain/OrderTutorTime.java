package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.domain.vo.IBuy;
import lombok.*;
import com.ruoyi.common.annotation.Excel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 订单计时对象 t_order_tutor_time
 * 
 * @author zhoukeu
 * @date 2024-09-13
 */
@TableName("t_order_tutor_time")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTutorTime extends BaseFee
{
    private static final long serialVersionUID = 1L;

    /** 球桌编码 */

    @TableId("order_tutor_time_id")
    private Long orderTutorTimeId;

    /** 订单编号 */
    @Excel(name = "订单编号")

    @TableField("order_id")
    private Long orderId;

    /** 球桌编码 */
    @Excel(name = "球桌编码")

    @TableField("desk_id")
    @NotNull(  groups = IBuy.class,message = "台桌不能为空")
    private Long deskId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("start_time")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("end_time")
    private Date endTime;

    /** 类型：4=陪练,5=教学 */
    @Excel(name = "类型：4=陪练,5=教学")

    @TableField("type")
    @NotNull(  groups = IBuy.class,message = "课程类型不能为空")
    private Integer type;

    /** 总时间分钟（开始时间去掉秒，结束时间多一秒加1分钟算） */
    @Excel(name = "总时间分钟", readConverterExp = "开=始时间去掉秒，结束时间多一秒加1分钟算")

    @TableField("total_time")
    private Integer totalTime;

    /** 价格/分钟 */
    @Excel(name = "价格/分钟")

    @TableField("price")
    private BigDecimal price;

    /** 助教总费用 */
    @Excel(name = "助教总费用")

    @TableField("total_amount_due")
    private BigDecimal totalAmountDue;

    @TableField("discount_value")
    private BigDecimal discountValue;

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

    @TableField("tutor_id")
    @NotNull(  groups = IBuy.class,message = "教练不能为空")
    private Long  tutorId;

    /**
     * 状态:1=计费中，2=暂停，3=已结束
     */
    @TableField("status")
    private Integer  status;
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

    @TableField(exist = false)
    private StoreTutor storeTutor;
}
