package com.renxin.course.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("课程")
public class CourseVO implements Serializable {

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

    /** 课程详情，富文本内容 */
    @ApiModelProperty("课程详情")
    private String detail;

    /** 课程完成情况，0-未完成 1-已完成 */
    @ApiModelProperty("课程完成情况")
    private Integer finishStatus;

    /** 课程已学习时长 */
    @ApiModelProperty("课程已学习时长（秒）")
    private Integer studyDuration;

    /** 课程学习人数 */
    @ApiModelProperty("课程学习人数")
    private Integer studyNum;

    /** 课程是否购买, 0-未购买， 1-已购买 */
    @ApiModelProperty("是否购买")
    private Integer isBuy;

    @ApiModelProperty("章节列表")
    private List sectionList;

}
