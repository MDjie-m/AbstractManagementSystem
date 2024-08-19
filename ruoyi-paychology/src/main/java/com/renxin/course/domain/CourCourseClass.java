package com.renxin.course.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 类型对象 cour_course_class
 * 
 * @author renxin
 * @date 2023-04-07
 */
@Data
public class CourCourseClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long id;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String name;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 排序 */
    @Excel(name = "图标路径")
    private String url;
    
    //服务对象  1.来访者  2.咨询师
    private Integer serviceTo;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sort", getSort())
            .toString();
    }
}
