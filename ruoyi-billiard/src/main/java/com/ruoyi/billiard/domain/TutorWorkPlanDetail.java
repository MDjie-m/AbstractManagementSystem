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
 * 教练排班计划详细对象 t_tutor_work_plan_detail
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@TableName("t_tutor_work_plan_detail")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorWorkPlanDetail extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("tutor_work_plan_detail_id")
    private Long tutorWorkPlanDetailId;

    /** 计划id */
    @Excel(name = "计划id")

    @TableField("tutor_work_plan_id")
    private Long tutorWorkPlanId;

    /** 计划类型：4=陪练,5=教学 */
    @Excel(name = "计划类型：4=陪练,5=教学")

    @TableField("plan_type")
    private Integer planType;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("start_time")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("end_time")
    private Date endTime;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;










}
