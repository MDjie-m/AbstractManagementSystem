package com.renxin.course.vo;

import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class LabelVO extends BaseEntity {

    /** ID */
    private Long id;

    /** 课程编号 */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程标签 */
    @Excel(name = "课程标签")
    private Integer courseLabel;
}
