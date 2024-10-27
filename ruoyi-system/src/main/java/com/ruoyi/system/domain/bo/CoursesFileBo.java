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
 * 课程相关文件业务对象 courses_file
 *
 * @author nbacheng
 * @date 2024-10-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CoursesFileBo extends BaseEntity {

    /**
     * 文件ID
     */
    @NotNull(message = "文件ID不能为空", groups = { EditGroup.class })
    private Long fileId;

    /**
     * 文件名称
     */
    @NotBlank(message = "文件名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileName;

    /**
     * 文件地址
     */
    @NotBlank(message = "文件地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileUrl;

    /**
     * 文件大小
     */
    //@NotBlank(message = "文件大小不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileSize;

    /**
     * 文件类型
     */
    //@NotBlank(message = "文件类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileType;

    /**
     * 应用分类  0课程体系 1课程效果 2材料包展示 3课堂总价 4教案 5视频
     */
    //@NotNull(message = "应用分类  0课程体系 1课程效果 2材料包展示 3课堂总价 4教案 5视频不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer useType;

    /**
     * 应用分类名
     */
    //@NotBlank(message = "应用分类名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeName;

    /**
     * 排序
     */
    //@NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer sort;

    /**
     * 课程ID
     */
    //@NotNull(message = "课程ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long courseId;

    /**
     * 目录ID
     */
    //@NotNull(message = "目录ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long menuId;

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
