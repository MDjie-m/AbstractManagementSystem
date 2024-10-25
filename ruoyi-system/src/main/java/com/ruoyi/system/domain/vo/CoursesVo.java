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
    private Date createDate;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateDate;


}
