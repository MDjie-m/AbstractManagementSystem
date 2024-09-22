package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 订单关系对象 t_order_relation
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
@TableName("t_order_relation")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRelation extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */

    @TableId("order_relation_id")
    private Long orderRelationId;

    /** 主单id */
    @Excel(name = "主单id")

    @TableField("main_order_id")
    private Long mainOrderId;

    /** 从单id */
    @Excel(name = "从单id")

    @TableField("sub_order_id")
    private Long subOrderId;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;








}
