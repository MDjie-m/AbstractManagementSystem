package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 桌子设备关联关系对象 t_desk_device_relation
 * 
 * @author ruoyi
 * @date 2024-09-07
 */
@TableName("t_desk_device_relation")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeskDeviceRelation extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("desk_device_relation_id")
    private Long deskDeviceRelationId;

    /** 桌子id */
    @Excel(name = "桌子id")

    @TableField("desk_id")
    private Long deskId;

    /** 设备id */
    @Excel(name = "设备id")

    @TableField("device_id")
    private Long deviceId;






}
