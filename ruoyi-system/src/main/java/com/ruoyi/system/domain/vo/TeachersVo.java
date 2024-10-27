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
 * 教师视图对象 teachers
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class TeachersVo {

    private static final long serialVersionUID = 1L;

    /**
     * 教师ID
     */
    @ExcelProperty(value = "教师ID")
    private Long id;

    /**
     * 学校ID
     */
    @ExcelProperty(value = "学校ID")
    private Long schoolId;

    /**
     * 教师姓名
     */
    @ExcelProperty(value = "教师姓名")
    private String teacherName;

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
