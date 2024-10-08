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
 * 教练排班计划对象 t_tutor_work_plan
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
@TableName("t_tutor_work_plan")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorWorkPlan extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("tutor_work_plan_id")
    private Long tutorWorkPlanId;

    /** 教练id */
    @Excel(name = "教练id")

    @TableField("tutor_id")
    private Long tutorId;

    @TableField("store_id")
    private Long storeId;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")

    @TableField("day")
    private Date day;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;



    @TableField("count")
    private Integer count;




}
