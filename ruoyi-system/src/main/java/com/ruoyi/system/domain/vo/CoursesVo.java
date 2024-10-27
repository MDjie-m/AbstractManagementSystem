package com.ruoyi.system.domain.vo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.system.domain.CoursesFile;
import com.ruoyi.system.domain.CoursesMenu;
import com.ruoyi.system.domain.bo.CoursesFileBo;
import lombok.Data;
import java.util.Date;
import java.util.List;


/**
 * 课程视图对象 courses
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class CoursesVo {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ExcelProperty(value = "课程ID")
    private Long id;

    /**
     * 课程名称
     */
    @ExcelProperty(value = "课程名称")
    private String courseName;

    /**
     * 课程简介
     */
    @ExcelProperty(value = "课程简介")
    private String courseDescription;

    /**
     * 课程封面
     */
    @ExcelProperty(value = "课程封面")
    private String courseCover;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    private String createBy;

    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    private String updateBy;

    private List<CoursesFile> systemFiles;
    private List<CoursesFile> effectFiles;
    private List<CoursesFile> packageFiles;
    private List<CoursesMenu> menuList;

}
