package com.renxin.advert.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 页面广告对象 psy_advert
 * 
 * @author renxin
 * @date 2024-08-16
 */
@Data
public class PsyAdvert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 广告编号(手输) */
    private String advertNo;

    /** 广告标题 */
    @Excel(name = "广告标题")
    private String advertTitle;

    /** 广告描述 */
    @Excel(name = "广告描述")
    private String advertText;

    /** 所属页面 */
    @Excel(name = "所属页面")
    private String pageFor;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String dataType;

    /** 批量查询url */
    @Excel(name = "批量查询url")
    private String queryUrl;

    /** 面向用户类型  1.来访者   2.咨询师 */
    @Excel(name = "面向用户类型  1.来访者   2.咨询师")
    private Integer serviceTo;

    /** 广告主图 */
    @Excel(name = "广告主图")
    private String advertImg;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;
    
    //条目清单
    private List<PsyAdvertItem> itemList;
    private String ids;

}
