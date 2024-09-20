package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.math.BigDecimal;

/**
 * 等级折扣范围对象 t_level_discount_permission
 * 
 * @author zhoukeu
 * @date 2024-09-20
 */
@TableName("t_level_discount_permission")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LevelDiscountPermission extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("level_discount_permission_id")
    private Long levelDiscountPermissionId;

    /** 等级id */
    @Excel(name = "等级id")

    @TableField("member_level_id")
    private Long memberLevelId;

    /** 打折类型：{1=球桌费用， 3=商品购买,4=陪练费用，5=教学费用}  */
    @Excel(name = "打折类型：{1=球桌费用， 3=商品购买,4=陪练费用，5=教学费用} ")

    @TableField("value")
    private Integer value;

    /** 折扣力度 */
    @Excel(name = "折扣力度")

    @TableField("discount")
    private BigDecimal discount;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;









}
