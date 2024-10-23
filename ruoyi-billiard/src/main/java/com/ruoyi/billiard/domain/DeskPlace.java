package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 台桌区域对象 t_desk_place
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@TableName("t_desk_place")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeskPlace extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("desk_place_id")
    private Long deskPlaceId;

    /** 区域名称 */
    @Excel(name = "区域名称")

    @TableField("name")
    private String name;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 排序 */
    @Excel(name = "排序")

    @TableField("sort")
    private Integer sort;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;









}
