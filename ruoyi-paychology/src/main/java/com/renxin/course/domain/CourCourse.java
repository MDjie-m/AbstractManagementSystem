package com.renxin.course.domain;

import java.math.BigDecimal;
import java.util.List;

import com.renxin.course.vo.SectionVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 课程对象 cour_course
 * 
 * @author renxin
 * @date 2023-03-14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程ID */
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String name;

    /** 课程类型，取值根据课程类型表 */
    @Excel(name = "课程类型")
    private Integer type;

    /** 服务对象  1.普通用户  2.咨询师 */
    @Excel(name = "服务对象")
    private Integer serviceTo;


    /** 课程付费方式（0-付费，1-免费） */
    @Excel(name = "课程付费方式")
    private Integer payType;

    /** 课程作者 */
    @Excel(name = "课程作者")
    private String author;

    /** 咨询师账号 */
    @Excel(name = "咨询师账号")
    private String userName;

    /** 课程图片 */
    @Excel(name = "课程图片")
    private String url;

    /** 课程列表图标、购买图标 */
    @Excel(name = "课程列表图标")
    private String iconUrl;

    /** 课程价格 */
    @Excel(name = "课程价格")
    private BigDecimal price;

    /** 课程详情，富文本内容 */
    @Excel(name = "课程详情，富文本内容")
    private String detail;

    /** 是否上架（0-未上架，1-已上架）*/
    @Excel(name = "是否上架")
    private Integer onSale;
    
    private List<Long> idList;
    private String ids;
    
    private Integer studyNum;//学习人数
    private Integer sectionNum;//章节数量
    private Integer totalDuration;//总时长
    private Integer completedSectionNum;//已学完的课程数
    private Integer totalLearnTime;//学习总时长
    
    private Long userId;
    private Integer userType;//1.来访者  2.咨询师

    //章节清单
    private List<CourSection> sectionList;
    
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("author", getAuthor())
            .append("userName", getUserName())
            .append("url", getUrl())
            .append("price", getPrice())
            .append("detail", getDetail())
            .append("onSale", getOnSale())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
