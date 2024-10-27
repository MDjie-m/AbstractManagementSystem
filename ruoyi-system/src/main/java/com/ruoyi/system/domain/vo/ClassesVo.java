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
 * 班级视图对象 classes
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class ClassesVo {

    private static final long serialVersionUID = 1L;

    /**
     * 班级ID
     */
    @ExcelProperty(value = "班级ID")
    private Long id;

    /**
     * 年级ID
     */
    @ExcelProperty(value = "年级ID")
    private Long gradeId;

    /**
     * 班级名称
     */
    @ExcelProperty(value = "班级名称")
    private String className;

    /**
     * 班主任
     */
    @ExcelProperty(value = "班主任")
    private Long headTeacherId;

    /**
     * 年段id
     */
    @ExcelProperty(value = "年段id")
    private Long gradeSegmentId;

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
