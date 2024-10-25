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
 * 学生业务对象 students
 *
 * @author nbacheng
 * @date 2024-10-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentsBo extends BaseEntity {

    /**
     * 学生ID
     */
    @NotNull(message = "学生ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 班级ID
     */
    @NotNull(message = "班级ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long classId;

    /**
     * 学生姓名
     */
    @NotBlank(message = "学生姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String studentName;

    /**
     * 学号
     */
    @NotBlank(message = "学号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String studentNo;

    /**
     * 入学时间
     */
    @NotNull(message = "入学时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date enrolledTime;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sex;

    /**
     * 学校ID
     */
    @NotNull(message = "学校ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long schoolId;

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
