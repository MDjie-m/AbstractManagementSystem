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
 * 学生对象 students
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("students")
public class Students extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 学生ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 班级ID
     */
    private Long classId;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 学号
     */
    private String studentNo;
    /**
     * 入学时间
     */
    private Date enrolledTime;
    /**
     * 性别
     */
    private String sex;
    /**
     * 学校ID
     */
    private Long schoolId;
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
