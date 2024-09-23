package com.ruoyi.billiard.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 门店班次对象 t_store_schedule
 * 
 * @author zhoukeu
 * @date 2024-09-22
 */
@TableName("t_store_schedule")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreSchedule extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("store_schedule_id")
    private Long storeScheduleId;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 开始时间 */
    @JsonFormat(pattern = "HH:mm")
    @Excel(name = "开始时间", width = 30, dateFormat = "HH:mm")

    @TableField("start_time")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "HH:mm")
    @Excel(name = "结束时间", width = 30, dateFormat = "HH:mm")

    @TableField("end_time")
    private Date endTime;

    /** -1,0,+1 */
    @Excel(name = "-1,0,+1")

    @TableField("start_time_offset_day")
    private Integer startTimeOffsetDay;

    /** -1,0,+1 */
    @Excel(name = "-1,0,+1")

    @TableField("end_time_offset_day")
    private Integer endTimeOffsetDay;

    /** 哪一天 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "哪一天", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("day")
    private Date day;

    /** 创建者Id */

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */

    @TableField("update_by_id")
    private Long updateById;


    @TableField(exist = false)
    private String storeName;

    @TableField(exist = false)
    private String startTimeStr;

    @TableField(exist = false)
    private String endTimeStr;


    //'是否是默认系统班次'
    @TableField("default_schedule")
    private Boolean defaultSchedule;






}
