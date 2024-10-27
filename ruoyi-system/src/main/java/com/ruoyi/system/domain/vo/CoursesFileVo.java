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
 * 课程相关文件视图对象 courses_file
 *
 * @author nbacheng
 * @date 2024-10-25
 */
@Data
@ExcelIgnoreUnannotated
public class CoursesFileVo {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @ExcelProperty(value = "文件ID")
    private Long fileId;

    /**
     * 文件名称
     */
    @ExcelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件地址
     */
    @ExcelProperty(value = "文件地址")
    private String fileUrl;

    /**
     * 文件大小
     */
    @ExcelProperty(value = "文件大小")
    private String fileSize;

    /**
     * 文件类型
     */
    @ExcelProperty(value = "文件类型")
    private String fileType;

    /**
     * 应用分类  0课程体系 1课程效果 2材料包展示 3课堂总价 4教案 5视频
     */
    @ExcelProperty(value = "应用分类")
    private Integer useType;

    /**
     * 应用分类名
     */
    @ExcelProperty(value = "应用分类名")
    private String typeName;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Integer sort;

    /**
     * 课程ID
     */
    @ExcelProperty(value = "课程ID")
    private Long courseId;

    /**
     * 目录ID
     */
    @ExcelProperty(value = "目录ID")
    private Long menuId;

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


}
