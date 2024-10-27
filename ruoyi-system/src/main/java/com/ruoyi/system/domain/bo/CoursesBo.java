package com.ruoyi.system.domain.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.system.domain.CoursesMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程业务对象 courses
 *
 * @author nbacheng
 * @date 2024-10-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CoursesBo extends BaseEntity {

    /**
     * 课程ID
     */
    @NotNull(message = "课程ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String courseName;

    /**
     * 课程简介
     */
    //@NotBlank(message = "课程简介不能为空", groups = { AddGroup.class, EditGroup.class })
    private String courseDescription;

    /**
     * 课程封面
     */
    //@NotBlank(message = "课程封面不能为空", groups = { AddGroup.class, EditGroup.class })
    private String courseCover;

    /**
     * 创建时间
     */
    //@NotNull(message = "创建时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date createTime;

    /**
     * 更新时间
     */
    //@NotNull(message = "更新时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    private List<CoursesFileBo> systemFiles;
    private List<CoursesFileBo> effectFiles;
    private List<CoursesFileBo> packageFiles;

    private List<CoursesMenu> menuList;

}
