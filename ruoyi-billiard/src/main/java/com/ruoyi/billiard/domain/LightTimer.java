package com.ruoyi.billiard.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 灯光计时器对象 t_light_timer
 * 
 * @author ruoyi
 * @date 2024-09-19
 */
@TableName("t_light_timer")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LightTimer extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("light_timer_id")
    private Long lightTimerId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("start_time")
    @NotNull(message = "开始时间不能为空")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "结束时间不能为空")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("end_time")
    private Date endTime;

    /** 桌子id */
    @Excel(name = "桌子id")
    @NotNull(message = "台桌不能为空")
    @TableField("desk_id")
    private Long deskId;



    @TableField("store_id")
    private Long storeId;

    /** 订单id */
    @Excel(name = "订单id")

    @TableField("order_id")
    private Long orderId;
    @NotNull(message = "定时类型不能为空")
    /** 定光定时类型：0=零时灯,1=计费灯 */
    @Excel(name = "定光定时类型：0=零时灯,1=计费灯")

    @TableField("light_type")
    private Integer lightType;

    /** 是否有效 */
    @Excel(name = "是否有效")

    @TableField("enable")
    private Boolean enable;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;




    @TableField(exist = false)
    private Integer deskNum;








}
