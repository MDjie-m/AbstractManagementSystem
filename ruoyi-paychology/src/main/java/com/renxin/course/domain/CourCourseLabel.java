package com.renxin.course.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * label对象 cour_course_label
 * 
 * @author renxin
 * @date 2023-03-16
 */
@Data
public class CourCourseLabel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 课程编号 */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 课程标签 */
    @Excel(name = "课程标签")
    private Integer courseLabel;

}
