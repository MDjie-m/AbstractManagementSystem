package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.util.List;

/**
 * 咨询师成长套餐对象 psy_consultant_package
 * 
 * @author renxin
 * @date 2024-06-26
 */
@NoArgsConstructor
@TableName("psy_consultant_package")
@Data
public class PsyConsultantPackage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 套餐主键 */
    private Long packageId;

    /** 套餐名称 */
    @Excel(name = "套餐名称")
    private String productName;

    /** 套餐产品图片地址 */
    @Excel(name = "套餐产品图片地址")
    private String productPicUrl;

    /** 头部图片 */
    @Excel(name = "头部图片")
    private String headPicUrl;
    
    /** 详情图片地址 */
    @Excel(name = "详情图片地址")
    private String detailPicUrl;
    
    /** 团队督导券张数 */
    private Integer teamSupNum;
    /** 个人督导券张数 */
    private Integer personSupNum;
    /** 个人体验券张数 */
    private Integer personExpNum;
    /** 课程券张数 */
    private Integer courseNum;
    //团队督导券id
    private Long teamSupCouponTemplateId;
    private Long personSupCouponTemplateId;
    private Long personExpCouponTemplateId;
    private Long courseCouponTemplateId;
    //团队督导券名称
    @TableField(exist = false)
    private String teamSupCouponTemplateName;
    @TableField(exist = false)
    private String personSupCouponTemplateName;
    @TableField(exist = false)
    private String personExpCouponTemplateName;
    @TableField(exist = false)
    private String courseCouponTemplateName;
    @TableField(exist = false)
    private List<Long> packageIdList;
    /** 套餐价格*/
    private BigDecimal price;

    /** 0:有效 1:失效 */
    @Excel(name = "0:有效 1:失效")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    @TableLogic
    private String delFlag;
    private String blurb;//简介
    
    

  
}
