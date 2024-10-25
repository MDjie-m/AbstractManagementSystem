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
 * 年级对象 grades
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("grades")
public class Grades extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 年级ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 年级名称（如一年级、二年级等）
     */
    private String gradeName;
    /**
     * 年段ID
     */
    private Long gradeSegmentId;
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
