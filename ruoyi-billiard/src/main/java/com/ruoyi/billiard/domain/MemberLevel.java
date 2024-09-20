package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 门店会员等级对象 t_member_level
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@TableName("t_member_level")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLevel extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("member_level_id")
    private Long memberLevelId;

    /** 会员等级 */
    @Excel(name = "会员等级")

    @TableField("level_name")
    private String levelName;

    /** 折扣力度 95折就填写95 */
    @Excel(name = "折扣力度 95折就填写95")

    @TableField("discount")
    private BigDecimal discount;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

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

    @TableField(exist = false)
    private List<Integer> discountRange;







}
