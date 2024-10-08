package com.ruoyi.billiard.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 教练打卡对象 t_tutor_punch_in
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
@TableName("t_tutor_punch_in")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorPunchIn extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("tutor_punch_in_id")
    private Long tutorPunchInId;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("start_time")
    private LocalDateTime startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("end_time")
    private LocalDateTime endTime;

    /** 哪一天 */
    //班次
    @TableField("schedule_day")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDay;


    /** 助教 */
    @Excel(name = "助教")

    @TableField("tutor_id")
    private Long tutorId;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;











}
