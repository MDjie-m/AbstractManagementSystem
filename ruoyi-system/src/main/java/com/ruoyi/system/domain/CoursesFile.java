package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程相关文件对象 courses_file
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("yk_courses_file")
public class CoursesFile extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 文件ID
     */
    @TableId(value = "file_id")
    private Long fileId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 应用分类  0课程体系 1课程效果 2材料包展示 3课堂总价 4教案 5视频
     */
    private Integer useType;
    /**
     * 应用分类名
     */
    private String typeName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 目录ID
     */
    private Long menuId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 0标识未删除，1表示已删除
     */
    @TableLogic
    private Integer delFlag;

}
