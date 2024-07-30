package com.renxin.course.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("课程列表（客户端使用）")
public class CourseDetailVO extends CourseListVO implements Serializable {

    /** 课程详情，富文本内容 */
    @ApiModelProperty("课程详情")
    private String detail;


    @ApiModelProperty("章节列表")
    private List sectionList;

}
