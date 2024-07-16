package com.renxin.psychology.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;

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

    /** 详情图片地址 */
    @Excel(name = "详情图片地址")
    private String detailPicUrl;
    
    /** 团队督导次数 */
    private Integer teamSupNum;
    /** 个人督导次数 */
    private Integer personSupNum;
    /** 个人体验次数 */
    private Integer personExpNum;
    /** 课程次数 */
    private Integer courseNum;
    /** 套餐价格*/
    private BigDecimal price;

    /** 0:有效 1:失效 */
    @Excel(name = "0:有效 1:失效")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

  
}
