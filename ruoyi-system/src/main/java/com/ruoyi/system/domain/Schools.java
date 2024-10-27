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
 * 学校对象 schools
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("schools")
public class Schools extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 学校ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 学校地址
     */
    private String address;
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
