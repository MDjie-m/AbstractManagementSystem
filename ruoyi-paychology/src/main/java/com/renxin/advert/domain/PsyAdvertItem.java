package com.renxin.advert.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 广告条目对象 psy_advert_item
 * 
 * @author renxin
 * @date 2024-08-16
 */
@Data
public class PsyAdvertItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 所属广告编号 */
    @Excel(name = "所属广告编号")
    private String advertNo;
    //排序号
    private Integer sortNo;

    /** 条目标题 */
    @Excel(name = "条目标题")
    private String itemTitle;

    /** 条目描述 */
    @Excel(name = "条目描述")
    private String itemText;

    /** 条目图片 */
    @Excel(name = "条目图片")
    private String itemImg;

    /** 跳转页面 */
    @Excel(name = "跳转页面")
    private String toPage;

    /** 外网url */
    @Excel(name = "外网url")
    private String toUrl;

    /** 携带参数 */
    @Excel(name = "携带参数")
    private Long param;

    /** 关联对象id */
    @Excel(name = "关联对象id")
    private String relationObjectId;

    /** $column.columnComment */
    private String relationObjectType;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

}
