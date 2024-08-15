package com.renxin.course.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("课程列表展示（客户端使用）")
public class CourseListVO implements Serializable {

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 课程名称 */
    @ApiModelProperty("课程名称")
    private String name;

    /** 课程类型，取值根据课程类型表 */
    @ApiModelProperty("课程类型")
    private Integer type;

    /** 付费方式 */
    @ApiModelProperty("付费方式")
    private Integer payType;

    /** 课程作者 */
    @ApiModelProperty("课程作者")
    private String author;

    /** 咨询师账号 */
    @ApiModelProperty("咨询师账号")
    private String userName;

    /** 课程图片 */
    @ApiModelProperty("课程图片")
    private String url;

    /** 课程列表图标 */
    @ApiModelProperty("课程列表图标")
    private String iconUrl;

    /** 课程价格 */
    @ApiModelProperty("课程价格")
    private BigDecimal price;



    /** 课程时长 */
    @ApiModelProperty("课程时长（秒）,章节时长之和")
    private Integer totalDuration;

    @ApiModelProperty("章节数量")
    private Integer sectionNum;


    /** 课程学习人数 */
    @ApiModelProperty("课程学习人数")
    private Integer studyNum;



}
