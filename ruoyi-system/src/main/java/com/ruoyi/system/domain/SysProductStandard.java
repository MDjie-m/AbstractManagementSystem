package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品对象 sys_product_standard
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
public class SysProductStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 平台产品id */
    private String productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 海关一级分类 */
    @Excel(name = "海关一级分类")
    private String primaryCategory;

    /** 海关一级分类名称 */
    @Excel(name = "海关一级分类名称")
    private String primaryCategoryName;

    /** 海关二级分类 */
    @Excel(name = "海关二级分类")
    private String secondaryCategory;

    /** 海关二级分类名称 */
    @Excel(name = "海关二级分类名称")
    private String secondaryCategoryName;

    /** 海关三级分类 */
    @Excel(name = "海关三级分类")
    private String tertiaryCategory;

    /** 海关三级分类名称 */
    @Excel(name = "海关三级分类名称")
    private String tertiaryCategoryName;

    /** 海关四级分类 */
    @Excel(name = "海关四级分类")
    private String quaternaryCategory;

    /** 海关四级分类名称 */
    @Excel(name = "海关四级分类名称")
    private String quaternaryCategoryName;

    /** 海关五级分类 */
    @Excel(name = "海关五级分类")
    private String fifthCategory;

    /** 海关五级分类名称 */
    @Excel(name = "海关五级分类名称")
    private String fifthCategoryName;

    /** 公司一级分类 */
    @Excel(name = "公司一级分类")
    private String cnPrimaryCategory;

    /** 公司一级分类名称 */
    @Excel(name = "公司一级分类名称")
    private String cnPrimaryCategoryName;

    /** 公司二级分类 */
    @Excel(name = "公司二级分类")
    private String cnSecondaryCategory;

    /** 公司二级分类名称 */
    @Excel(name = "公司二级分类名称")
    private String cnSecondaryCategoryName;

    /** 公司三级分类 */
    @Excel(name = "公司三级分类")
    private String cnTertiaryCategory;

    /** 公司三级分类名称 */
    @Excel(name = "公司三级分类名称")
    private String cnTertiaryCategoryName;

    /** 公司四级分类 */
    @Excel(name = "公司四级分类")
    private String cnQuaternaryCategory;

    /** 公司四级分类名称 */
    @Excel(name = "公司四级分类名称")
    private String cnQuaternaryCategoryName;

    /** 公司五级分类 */
    @Excel(name = "公司五级分类")
    private String cnFifthCategory;

    /** 公司五级分类名称 */
    @Excel(name = "公司五级分类名称")
    private String cnFifthCategoryName;

    /** 0-国产 1-进口 */
    @Excel(name = "0-国产 1-进口")
    private Long domesticImportedType;

    /** 储存方式0:-冷冻1-冷鲜 */
    @Excel(name = "储存方式0:-冷冻1-冷鲜")
    private String storageMode;

    /** 产品规格 */
    @Excel(name = "产品规格")
    private String specifications;

    /** 标签id由，隔开 */
    @Excel(name = "标签id由，隔开")
    private String tagId;

    /** 标签由，隔开 */
    @Excel(name = "标签由，隔开")
    private String tagName;

    /** 删除状态：0-未删除，1-已删除 */
    private Long delFlag;

    /** 图片地址由”,”隔开 */
    @Excel(name = "图片地址由”,”隔开")
    private String imagePath;

    /** 视频地址由”,”隔开 */
    @Excel(name = "视频地址由”,”隔开")
    private String videoPath;

    /** 产品参数 */
    @Excel(name = "产品参数")
    private String productModel;

    /** 产品状态(0-期货,1-现货) */
    @Excel(name = "产品状态(0-期货,1-现货)")
    private Long status;

    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setPrimaryCategory(String primaryCategory) 
    {
        this.primaryCategory = primaryCategory;
    }

    public String getPrimaryCategory() 
    {
        return primaryCategory;
    }
    public void setPrimaryCategoryName(String primaryCategoryName) 
    {
        this.primaryCategoryName = primaryCategoryName;
    }

    public String getPrimaryCategoryName() 
    {
        return primaryCategoryName;
    }
    public void setSecondaryCategory(String secondaryCategory) 
    {
        this.secondaryCategory = secondaryCategory;
    }

    public String getSecondaryCategory() 
    {
        return secondaryCategory;
    }
    public void setSecondaryCategoryName(String secondaryCategoryName) 
    {
        this.secondaryCategoryName = secondaryCategoryName;
    }

    public String getSecondaryCategoryName() 
    {
        return secondaryCategoryName;
    }
    public void setTertiaryCategory(String tertiaryCategory) 
    {
        this.tertiaryCategory = tertiaryCategory;
    }

    public String getTertiaryCategory() 
    {
        return tertiaryCategory;
    }
    public void setTertiaryCategoryName(String tertiaryCategoryName) 
    {
        this.tertiaryCategoryName = tertiaryCategoryName;
    }

    public String getTertiaryCategoryName() 
    {
        return tertiaryCategoryName;
    }
    public void setQuaternaryCategory(String quaternaryCategory) 
    {
        this.quaternaryCategory = quaternaryCategory;
    }

    public String getQuaternaryCategory() 
    {
        return quaternaryCategory;
    }
    public void setQuaternaryCategoryName(String quaternaryCategoryName) 
    {
        this.quaternaryCategoryName = quaternaryCategoryName;
    }

    public String getQuaternaryCategoryName() 
    {
        return quaternaryCategoryName;
    }
    public void setFifthCategory(String fifthCategory) 
    {
        this.fifthCategory = fifthCategory;
    }

    public String getFifthCategory() 
    {
        return fifthCategory;
    }
    public void setFifthCategoryName(String fifthCategoryName) 
    {
        this.fifthCategoryName = fifthCategoryName;
    }

    public String getFifthCategoryName() 
    {
        return fifthCategoryName;
    }
    public void setCnPrimaryCategory(String cnPrimaryCategory) 
    {
        this.cnPrimaryCategory = cnPrimaryCategory;
    }

    public String getCnPrimaryCategory() 
    {
        return cnPrimaryCategory;
    }
    public void setCnPrimaryCategoryName(String cnPrimaryCategoryName) 
    {
        this.cnPrimaryCategoryName = cnPrimaryCategoryName;
    }

    public String getCnPrimaryCategoryName() 
    {
        return cnPrimaryCategoryName;
    }
    public void setCnSecondaryCategory(String cnSecondaryCategory) 
    {
        this.cnSecondaryCategory = cnSecondaryCategory;
    }

    public String getCnSecondaryCategory() 
    {
        return cnSecondaryCategory;
    }
    public void setCnSecondaryCategoryName(String cnSecondaryCategoryName) 
    {
        this.cnSecondaryCategoryName = cnSecondaryCategoryName;
    }

    public String getCnSecondaryCategoryName() 
    {
        return cnSecondaryCategoryName;
    }
    public void setCnTertiaryCategory(String cnTertiaryCategory) 
    {
        this.cnTertiaryCategory = cnTertiaryCategory;
    }

    public String getCnTertiaryCategory() 
    {
        return cnTertiaryCategory;
    }
    public void setCnTertiaryCategoryName(String cnTertiaryCategoryName) 
    {
        this.cnTertiaryCategoryName = cnTertiaryCategoryName;
    }

    public String getCnTertiaryCategoryName() 
    {
        return cnTertiaryCategoryName;
    }
    public void setCnQuaternaryCategory(String cnQuaternaryCategory) 
    {
        this.cnQuaternaryCategory = cnQuaternaryCategory;
    }

    public String getCnQuaternaryCategory() 
    {
        return cnQuaternaryCategory;
    }
    public void setCnQuaternaryCategoryName(String cnQuaternaryCategoryName) 
    {
        this.cnQuaternaryCategoryName = cnQuaternaryCategoryName;
    }

    public String getCnQuaternaryCategoryName() 
    {
        return cnQuaternaryCategoryName;
    }
    public void setCnFifthCategory(String cnFifthCategory) 
    {
        this.cnFifthCategory = cnFifthCategory;
    }

    public String getCnFifthCategory() 
    {
        return cnFifthCategory;
    }
    public void setCnFifthCategoryName(String cnFifthCategoryName) 
    {
        this.cnFifthCategoryName = cnFifthCategoryName;
    }

    public String getCnFifthCategoryName() 
    {
        return cnFifthCategoryName;
    }
    public void setDomesticImportedType(Long domesticImportedType) 
    {
        this.domesticImportedType = domesticImportedType;
    }

    public Long getDomesticImportedType() 
    {
        return domesticImportedType;
    }
    public void setStorageMode(String storageMode) 
    {
        this.storageMode = storageMode;
    }

    public String getStorageMode() 
    {
        return storageMode;
    }
    public void setSpecifications(String specifications) 
    {
        this.specifications = specifications;
    }

    public String getSpecifications() 
    {
        return specifications;
    }
    public void setTagId(String tagId) 
    {
        this.tagId = tagId;
    }

    public String getTagId() 
    {
        return tagId;
    }
    public void setTagName(String tagName) 
    {
        this.tagName = tagName;
    }

    public String getTagName() 
    {
        return tagName;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }
    public void setImagePath(String imagePath) 
    {
        this.imagePath = imagePath;
    }

    public String getImagePath() 
    {
        return imagePath;
    }
    public void setVideoPath(String videoPath) 
    {
        this.videoPath = videoPath;
    }

    public String getVideoPath() 
    {
        return videoPath;
    }
    public void setProductModel(String productModel) 
    {
        this.productModel = productModel;
    }

    public String getProductModel() 
    {
        return productModel;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("primaryCategory", getPrimaryCategory())
            .append("primaryCategoryName", getPrimaryCategoryName())
            .append("secondaryCategory", getSecondaryCategory())
            .append("secondaryCategoryName", getSecondaryCategoryName())
            .append("tertiaryCategory", getTertiaryCategory())
            .append("tertiaryCategoryName", getTertiaryCategoryName())
            .append("quaternaryCategory", getQuaternaryCategory())
            .append("quaternaryCategoryName", getQuaternaryCategoryName())
            .append("fifthCategory", getFifthCategory())
            .append("fifthCategoryName", getFifthCategoryName())
            .append("cnPrimaryCategory", getCnPrimaryCategory())
            .append("cnPrimaryCategoryName", getCnPrimaryCategoryName())
            .append("cnSecondaryCategory", getCnSecondaryCategory())
            .append("cnSecondaryCategoryName", getCnSecondaryCategoryName())
            .append("cnTertiaryCategory", getCnTertiaryCategory())
            .append("cnTertiaryCategoryName", getCnTertiaryCategoryName())
            .append("cnQuaternaryCategory", getCnQuaternaryCategory())
            .append("cnQuaternaryCategoryName", getCnQuaternaryCategoryName())
            .append("cnFifthCategory", getCnFifthCategory())
            .append("cnFifthCategoryName", getCnFifthCategoryName())
            .append("domesticImportedType", getDomesticImportedType())
            .append("storageMode", getStorageMode())
            .append("specifications", getSpecifications())
            .append("tagId", getTagId())
            .append("tagName", getTagName())
            .append("delFlag", getDelFlag())
            .append("imagePath", getImagePath())
            .append("videoPath", getVideoPath())
            .append("productModel", getProductModel())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
