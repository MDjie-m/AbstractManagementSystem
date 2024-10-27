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
 * 学生视图对象 students
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class StudentsVo {

    private static final long serialVersionUID = 1L;

    /**
     * 学生ID
     */
    @ExcelProperty(value = "学生ID")
    private Long id;

    /**
     * 班级ID
     */
    @ExcelProperty(value = "班级ID")
    private Long classId;

    /**
     * 学生姓名
     */
    @ExcelProperty(value = "学生姓名")
    private String studentName;

    /**
     * 学号
     */
    @ExcelProperty(value = "学号")
    private String studentNo;

    /**
     * 入学时间
     */
    @ExcelProperty(value = "入学时间")
    private Date enrolledTime;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别")
    private String sex;

    /**
     * 学校ID
     */
    @ExcelProperty(value = "学校ID")
    private Long schoolId;

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
