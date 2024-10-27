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
 * 年级视图对象 grades
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class GradesVo {

    private static final long serialVersionUID = 1L;

    /**
     * 年级ID
     */
    @ExcelProperty(value = "年级ID")
    private Long id;

    /**
     * 年级名称（如一年级、二年级等）
     */
    @ExcelProperty(value = "年级名称", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "如=一年级、二年级等")
    private String gradeName;

    /**
     * 年段ID
     */
    @ExcelProperty(value = "年段ID")
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
