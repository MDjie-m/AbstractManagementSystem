package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程目录业务对象 courses_menu
 *
 * @author nbacheng
 * @date 2024-10-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CoursesMenuBo extends BaseEntity {

    /**
     * 目录ID
     */
    @NotNull(message = "目录ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 目录名称
     */
    @NotBlank(message = "目录名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String memuName;

    /**
     * 课程id
     */
    @NotNull(message = "课程id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long courseId;

    /**
     * 排序
     */
    //@NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer sort;

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


}
