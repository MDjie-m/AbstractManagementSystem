package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.math.BigDecimal;

/**
 * 球桌价格对象 t_desk_price
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@TableName("t_desk_price")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeskPrice extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("desk_price_id")
    private Long deskPriceId;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌 */
    @Excel(name = "球桌类型：0=中式，1=美式，2=斯诺克，3=棋牌")

    @TableField("desk_type")
    private Integer deskType;

    /** 价格 */
    @Excel(name = "价格")

    @TableField("price")
    private BigDecimal price;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;

    @TableField(exist = false)
    private String storeName;









}
