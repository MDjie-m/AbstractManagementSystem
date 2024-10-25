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
 * 年段视图对象 grade_segment
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class GradeSegmentVo {

    private static final long serialVersionUID = 1L;

    /**
     * 年段ID
     */
    @ExcelProperty(value = "年段ID")
    private Long id;

    /**
     * 学校ID
     */
    @ExcelProperty(value = "学校ID")
    private Long schoolId;

    /**
     * 年段（如小学、初中、高中）
     */
    @ExcelProperty(value = "年段", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "如=小学、初中、高中")
    private String segmentName;

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
