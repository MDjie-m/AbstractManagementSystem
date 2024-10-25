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
 * 课程对象 courses
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("courses")
public class Courses extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 课程ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程简介
     */
    private String courseDescription;
    /**
     * 课程封面
     */
    private String courseCover;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 0标识未删除，1表示已删除
     */
    @TableLogic
    private Integer delFlag;

}
