package com.renxin.course.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 课程banner配置对象 cour_course_banner_config
 * 
 * @author renxin
 * @date 2023-03-14
 */
@Data
public class CourCourseBannerConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** banner图片地址 */
    @Excel(name = "banner图片地址")
    private String bannerUrl;

    /** 跳转url */
    @Excel(name = "跳转url")
    private String linkUrl;

    /** banner分类(0-首页一级banner页，1-限时福利，2-精选好课) */
    @Excel(name = "banner分类(0-首页一级banner页，1-限时福利，2-精选好课)")
    private Long bannerType;

}
