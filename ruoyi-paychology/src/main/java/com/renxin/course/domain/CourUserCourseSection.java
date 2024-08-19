package com.renxin.course.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 用户-课程-章节关系对象 cour_user_course_section
 * 
 * @author renxin
 * @date 2023-03-15
 */
@Data
public class CourUserCourseSection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 用户编号 */
    @Excel(name = "用户ID")
    private Long userId;

    //用户类型  1管理端  2咨询者  3来访者
    private Integer userType;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;

    /** 章节编号 */
    @Excel(name = "章节编号")
    private Long sectionId;

    /** 上次结束时间（单位：秒） */
    @Excel(name = "上次结束时间", readConverterExp = "单=位：秒")
    private Integer endTime;

    /** 完成状态（0-未完成， 1-已完成） */
    @Excel(name = "完成状态", readConverterExp = "0=-未完成，,1=-已完成")
    private Integer finishStatus;
    
    /** 笔记 */
    private String note;
    /** 笔记时间 */
    private String noteTime;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("courseId", getCourseId())
            .append("sectionId", getSectionId())
            .append("endTime", getEndTime())
            .append("finishStatus", getFinishStatus())
            .toString();
    }
}
