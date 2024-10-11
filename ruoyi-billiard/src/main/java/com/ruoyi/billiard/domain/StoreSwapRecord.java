package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.domain.vo.IAdd;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

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
    private BigDecimal total = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    /**
     * 现金
     */
    @Excel(name = "现金")

    @TableField("cash_total")
    private BigDecimal cashTotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    /**
     * 台桌费用
     */
    @Excel(name = "台桌费用")

    @TableField("desk_total")
    private BigDecimal deskTotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    /**
     * 教练费用
     */
    @Excel(name = "教练费用")

    @TableField("tutor_total")
    private BigDecimal tutorTotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    /**
     * 教练费用
     */
    @Excel(name = "教练费用")

    @TableField("goods_total")
    private BigDecimal goodsTotal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    /**
     * 抹零金额
     */
    @Excel(name = "抹零金额")

    @TableField("total_wipe_zero")
    private BigDecimal totalWipeZero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    /**
     * 挂单单数
     */
    @Excel(name = "挂单单数")

    @TableField("suspend_order_count")
    private Long suspendOrderCount = 0L;

    /**
     * 挂单金额
     */
    @Excel(name = "挂单金额")

    @TableField("suspend_order_amount")
    private BigDecimal suspendOrderAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);

    @TableField("not_settled_order_count")
    private Long notSettledOrderCount = 0L;


    @TableField("not_settled_order_amount")
    private BigDecimal notSettledOrderAmount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);



    /**
     * 班次
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "班次", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("schedule_day")
    @NotNull(groups = IAdd.class, message = "请选择班次")
    private LocalDate scheduleDay;

    /**
     * 门店
     */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

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

    @TableField(exist = false)
    private String user;


    @Override
    @Length(groups = IAdd.class, max = 200, message = "备注内容超出200个字")
    public String getRemark() {
        return super.getRemark();
    }
}
