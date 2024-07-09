package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
@Data
public class BaseEntity2 implements Serializable
{
    private static final long serialVersionUID = 1L;


    /** 创建者 */
    @TableField(exist = false)
    private String createBy;

    /** 创建时间 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    @TableField(exist = false)
    private String updateBy;

    /** 更新时间 */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
