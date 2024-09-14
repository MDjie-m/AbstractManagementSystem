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
public class OrderTutorTime extends MyBaseEntity
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
    private Long deskId;

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

    /** 类型：4=陪练,5=教学 */
    @Excel(name = "类型：4=陪练,5=教学")

    @TableField("type")
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

    @TableField("total_amount")
    private BigDecimal totalAmount;

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














}
