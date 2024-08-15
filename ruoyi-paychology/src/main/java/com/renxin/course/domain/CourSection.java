package com.renxin.course.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 章节对象 cour_section
 * 
 * @author renxin
 * @date 2023-03-14
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourSection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 章节ID */
    private Long id;

    /** 章节编号 */
    @Excel(name = "章节编号")
    private String sectionId;

    /** 章节题目 */
    @Excel(name = "章节题目")
    private String topic;

    /** 章节时长(单位：秒) */
    @Excel(name = "章节时长(单位：秒)")
    private Integer duration;

    /** 章节类型（0-普通，1-试听） */
    @Excel(name = "章节类型", readConverterExp = "0=-普通，1-试听")
    private Integer type;

    /** 章节内容，富文本 */
    @Excel(name = "章节内容，富文本")
    private String content;

    /** 章节内容链接 */
    @Excel(name = "章节内容链接")
    private String contentUrl;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 内容类型（0-视频，1-音频） */
    @Excel(name = "内容类型", readConverterExp = "0=-视频，1-音频")
    private Integer contentType;

    
}
