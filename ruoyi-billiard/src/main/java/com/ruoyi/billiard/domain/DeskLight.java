package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 灯光对象 t_desk_light
 * 
 * @author ruoyi
 * @date 2024-10-12
 */
@TableName("t_desk_light")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeskLight extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */

    @TableId("light_id")
    private Long lightId;

    /** 总营收 */
    @Excel(name = "总营收")

    @TableField("open")
    private Boolean open;

    /** 台桌编号 */
    @Excel(name = "台桌编号")

    @TableField("desk_num")
    private Integer deskNum;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;










}
