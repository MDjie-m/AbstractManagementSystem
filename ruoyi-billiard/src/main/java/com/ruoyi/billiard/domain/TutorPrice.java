package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.math.BigDecimal;

/**
 * 教练价格对象 t_tutor_price
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@TableName("t_tutor_price")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorPrice extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("tutor_price_id")
    private Long tutorPriceId;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 等级(1=助教，2=教练，3=总教) */
    @Excel(name = "等级(1=助教，2=教练，3=总教)")

    @TableField("level")
    private Integer level;

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
