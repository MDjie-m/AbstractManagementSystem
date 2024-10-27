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
 * 班级业务对象 classes
 *
 * @author nbacheng
 * @date 2024-10-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ClassesBo extends BaseEntity {

    /**
     * 班级ID
     */
    @NotNull(message = "班级ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 年级ID
     */
    @NotNull(message = "年级ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long gradeId;

    /**
     * 班级名称
     */
    @NotBlank(message = "班级名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String className;

    /**
     * 班主任
     */
    @NotNull(message = "班主任不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long headTeacherId;

    /**
     * 年段id
     */
    @NotNull(message = "年段id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long gradeSegmentId;

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
