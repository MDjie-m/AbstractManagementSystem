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
 * 教师业务对象 teachers
 *
 * @author nbacheng
 * @date 2024-10-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TeachersBo extends BaseEntity {

    /**
     * 教师ID
     */
    @NotNull(message = "教师ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 学校ID
     */
    @NotNull(message = "学校ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long schoolId;

    /**
     * 教师姓名
     */
    @NotBlank(message = "教师姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String teacherName;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date createDate;

    /**
     * 更新时间
     */
    @NotNull(message = "更新时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date updateDate;


}
