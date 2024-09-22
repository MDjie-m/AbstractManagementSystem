package com.ruoyi.billiard.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 台球桌比分对象 t_order_desk_score
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
@TableName("t_order_desk_score")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeskScore extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("order_desk_score_id")
    private Long orderDeskScoreId;

    /** 订单id */
    @Excel(name = "订单id")

    @TableField("order_id")
    private Long orderId;

    /** 桌子id */
    @Excel(name = "桌子id")

    @TableField("desk_id")
    private Long deskId;

    /** 比分A */
    @Excel(name = "比分A")

    @TableField("score_a")
    private Integer scoreA;

    /** 比分B */
    @Excel(name = "比分B")

    @TableField("score_b")
    private Integer scoreB;

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

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;












}
