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
 * 学校视图对象 schools
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class SchoolsVo {

    private static final long serialVersionUID = 1L;

    /**
     * 学校ID
     */
    @ExcelProperty(value = "学校ID")
    private Long id;

    /**
     * 学校名称
     */
    @ExcelProperty(value = "学校名称")
    private String schoolName;

    /**
     * 学校地址
     */
    @ExcelProperty(value = "学校地址")
    private String address;

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
