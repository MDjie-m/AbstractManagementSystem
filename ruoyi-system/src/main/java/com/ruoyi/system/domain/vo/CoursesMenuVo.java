package com.ruoyi.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 课程目录视图对象 courses_menu
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class CoursesMenuVo {

    private static final long serialVersionUID = 1L;

    /**
     * 目录ID
     */
    @ExcelProperty(value = "目录ID")
    private Long id;

    /**
     * 目录名称
     */
    @ExcelProperty(value = "目录名称")
    private String memuName;

    /**
     * 课程id
     */
    @ExcelProperty(value = "课程id")
    private Long courseId;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateDate;


}
