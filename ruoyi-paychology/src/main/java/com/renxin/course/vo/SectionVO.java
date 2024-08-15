package com.renxin.course.vo;

import com.renxin.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("课程章节")
public class SectionVO implements Serializable {
    /** 章节ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 章节编号 */
    @Excel(name = "章节编号")
    @ApiModelProperty("章节编号")
    private String sectionId;

    /** 章节题目 */
    @Excel(name = "章节题目")
    @ApiModelProperty("章节题目")
    private String topic;

    /** 章节时长(单位：秒) */
    @Excel(name = "章节时长(单位：秒)")
    @ApiModelProperty("章节时长")
    private Integer duration;

    /** 章节类型（0-普通，1-试听） */
    @Excel(name = "章节类型", readConverterExp = "0=-普通，1-试听")
    @ApiModelProperty("章节类型")
    private Integer type;

    /** 章节内容，富文本 */
    @Excel(name = "章节内容，富文本")
    @ApiModelProperty("章节内容，富文本")
    private String content;

    /** 章节内容链接 */
    @Excel(name = "章节内容链接")
    @ApiModelProperty("章节内容链接")
    private String contentUrl;

    /** 课程ID */
    @Excel(name = "课程ID")
    @ApiModelProperty("课程ID")
    private Long courseId;

    /** 内容类型（0-视频，1-音频） */
    @Excel(name = "内容类型", readConverterExp = "0=-视频，1-音频")
    @ApiModelProperty("内容类型")
    private Integer contentType;

    /** 学习结束时间点 */
    @Excel(name = "学习结束时间点")
    @ApiModelProperty("学习结束时间点")
    private Integer endTime;
}
