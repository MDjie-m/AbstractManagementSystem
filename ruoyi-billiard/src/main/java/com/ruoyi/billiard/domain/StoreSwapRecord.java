package com.ruoyi.billiard.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 交班记录对象 t_store_swap_record
 *
 * @author ruoyi
 * @date 2024-10-10
 */
@TableName("t_store_swap_record")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreSwapRecord extends MyBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */

    @TableId("swap_record_id")
    private Long swapRecordId;

    /**
     * 总营收
     */
    @Excel(name = "总营收")

    @TableField("total")
    private String total;

    /**
     * 现金
     */
    @Excel(name = "现金")

    @TableField("cash_total")
    private String cashTotal;

    /**
     * 台桌费用
     */
    @Excel(name = "台桌费用")

    @TableField("desk_total")
    private Long deskTotal;

    /**
     * 教练费用
     */
    @Excel(name = "教练费用")

    @TableField("tutor_total")
    private Long tutorTotal;

    /**
     * 教练费用
     */
    @Excel(name = "教练费用")

    @TableField("goods_total")
    private Long goodsTotal;

    /**
     * 抹零金额
     */
    @Excel(name = "抹零金额")

    @TableField("total_wipe_zero")
    private Long totalWipeZero;

    /**
     * 挂单单数
     */
    @Excel(name = "挂单单数")

    @TableField("suspend_order_count")
    private Long suspendOrderCount;

    /**
     * 挂单金额
     */
    @Excel(name = "挂单金额")

    @TableField("suspend_order_amount")
    private Long suspendOrderAmount;

    /**
     * 班次
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "班次", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("schedule_day")
    private LocalDate scheduleDay;

    /**
     * 门店
     */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /**
     * 登录账户id
     */
    @Excel(name = "登录账户id")

    @TableField("login_user_id")
    private Long loginUserId;

    /**
     * 创建者Id
     */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /**
     * 更新者Id
     */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;


    @TableField(exist = false)
    private LocalDateTime startTime;


    @TableField(exist = false)
    private LocalDateTime endTime;


}
