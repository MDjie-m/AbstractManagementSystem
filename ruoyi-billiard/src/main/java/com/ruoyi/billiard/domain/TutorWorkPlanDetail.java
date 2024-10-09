package com.ruoyi.billiard.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.billiard.domain.vo.IAdd;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 教练排班计划详细对象 t_tutor_work_plan_detail
 * 
 * @author ruoyi
 * @date 2024-10-01
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

    @NotNull(groups = IAdd.class,message = "计划类型不能为空")
    @TableField("plan_type")
    private Integer planType;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    @NotNull(groups = IAdd.class,message = "开始时间不能为空")
    @TableField("start_time")
    private LocalDateTime startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    @NotNull(groups = IAdd.class,message = "结束时间不能为空")
    @TableField("end_time")
    private LocalDateTime endTime;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;


    @NotNull(groups = IAdd.class,message = "教练不能为空")
    @TableField("tutor_id")
    private Long tutorId;

    @TableField("store_id")
    private Long storeId;







}
