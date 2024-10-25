package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.enums.StockChangeType;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 库存日志对象 t_stock_log
 * 
 * @author ruoyi
 * @date 2024-09-09
 */
@TableName("t_stock_log")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockLog extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品编码 */

    @TableId(value = "stock_log_id",type = IdType.AUTO)
    private Long stockLogId;

    /** 库存id */
    @Excel(name = "库存id")

    @TableField("stock_id")
    private Long stockId;

    /** 变化数量(有+-符号) */
    @Excel(name = "变化数量(有+-符号)")

    @TableField("change_count")
    @NotNull(message = "数量不能为空")
    private Long changeCount;

    /** 变化类型：0=入库，1=出库，2=盘点 */
    @Excel(name = "变化类型：0=入库，1=出库，2=盘点")

    @TableField("change_type")
    @NotNull(message = "入库类型不能为空")
    private StockChangeType changeType;

    /** 变化前数量 */
    @Excel(name = "变化前数量")

    @TableField("before_count")
    private Long beforeCount;

    /** 变化前后当前数量 */
    @Excel(name = "变化前后当前数量")

    @TableField("current_count")
    private Long currentCount;


    @TableField(exist = false)
    @NotNull(message = "商品不能为空")
    private Long goodsId;
    @TableField(exist = false)
    private String goodsName;

    @TableField(exist = false)

    private Long storeId;


    @TableField(exist = false)
    private Long orderId;

    @TableField(exist = false)
    private String msg;
}
