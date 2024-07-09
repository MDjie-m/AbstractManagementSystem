package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * test学生信息对象 sys_student
 * 
 * @author ruoyi
 * @date 2024-07-09
 */
@Setter
@Getter
@ToString
@ApiModel
@TableName("sys_student")
public class SysStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "student_id",type = IdType.AUTO)
    private Long studentId;

    @ApiModelProperty(value = "学生名称")
    @Excel(name = "学生名称")
    private String studentName;

    @ApiModelProperty(value = "年龄")
    @Excel(name = "年龄")
    private Long studentAge;

    @ApiModelProperty(value = "爱好（0代码 1音乐 2电影）")
    @Excel(name = "爱好", readConverterExp = "0=代码,1=音乐,2=电影")
    private String studentHobby;

    @ApiModelProperty(value = "性别（0男 1女 2未知）")
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String studentSex;

    @ApiModelProperty(value = "状态（0正常 1停用）")
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String studentStatus;

    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date studentBirthday;










}
