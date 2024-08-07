package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品对象 sys_product
 * 
 * @author xgg
 * @date 2024-07-23
 */
public class SysProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编号 */
    private String productId;

    /** 供应商id */
    private String supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 一级分类 */
    private String primaryCategory;

    /** 一级分类名称 */
    @Excel(name = "一级分类名称")
    private String primaryCategoryName;

    /** 二级分类 */
    private String secondaryCategory;

    /** 二级分类名称 */
    @Excel(name = "二级分类名称")
    private String secondaryCategoryName;

    /** 三级分类 */
    private String tertiaryCategory;

    /** 三级分类名称 */
    @Excel(name = "三级分类名称")
    private String tertiaryCategoryName;

    /** 四级分类 */
    private String quaternaryCategory;

    /** 四级分类名称 */
    @Excel(name = "四级分类名称")
    private String quaternaryCategoryName;

    /** 五级分类 */
    private String fifthCategory;

    /** 五级分类名称 */
    @Excel(name = "五级分类名称")
    private String fifthCategoryName;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 0-国产 1-进口 */
    @Excel(name = "进口/国产", readConverterExp = "0=国产,1=进口")
    private Integer domesticImportedType;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String productModel;

    /** 是否可报价：0：不可报价，1：可报价 */
    @Excel(name = "是否可报价", readConverterExp = "0=不可报价,1=可报价")
    private Integer quotationFlag;

    /** 内部一级分类，cn_primary_category */
    private String cnPrimaryCategory;

    /** 内部一级分类名称，cn_primary_category_name */
    @Excel(name = "国产一级分类名称")
    private String cnPrimaryCategoryName;

    /** 内部二级分类，cn_secondary_category */
    private String cnSecondaryCategory;

    /** 内部二级分类名称，cn_secondary_category_name */
    @Excel(name = "国产二级分类名称")
    private String cnSecondaryCategoryName;

    /** 内部三级分类，cn_tertiary_category */
    private String cnTertiaryCategory;

    /** 内部三级分类名称，cn_tertiary_category_name */
    @Excel(name = "国产三级分类名称")
    private String cnTertiaryCategoryName;

    /** 内部四级分类，cn_quaternary_category */
    private String cnQuaternaryCategory;

    /** 内部四级分类名称，cn_quaternary_category_name */
    @Excel(name = "国产四级分类名称")
    private String cnQuaternaryCategoryName;

    /** 标签id，以'/'区分，tag_id */
    private String tagId;

    /** 标签名称，以'/'区分，tag_name */
    private String tagName;

    /** 预留字段1 */
    private String futureField1;

    /** 预留字段2 */
    private String futureField2;

    /** 预留字段3 */
    private String futureField3;

    public void setProductId(String productId) 
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setSupplierId(String supplierId) 
    {
        this.supplierId = supplierId;
    }

    public String getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
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
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setDomesticImportedType(Integer domesticImportedType) 
    {
        this.domesticImportedType = domesticImportedType;
    }

    public Integer getDomesticImportedType() 
    {
        return domesticImportedType;
    }
    public void setProductModel(String productModel) 
    {
        this.productModel = productModel;
    }

    public String getProductModel() 
    {
        return productModel;
    }
    public void setQuotationFlag(Integer quotationFlag) 
    {
        this.quotationFlag = quotationFlag;
    }

    public String getCnPrimaryCategory() {
        return cnPrimaryCategory;
    }

    public void setCnPrimaryCategory(String cnPrimaryCategory) {
        this.cnPrimaryCategory = cnPrimaryCategory;
    }

    public String getCnPrimaryCategoryName() {
        return cnPrimaryCategoryName;
    }

    public void setCnPrimaryCategoryName(String cnPrimaryCategoryName) {
        this.cnPrimaryCategoryName = cnPrimaryCategoryName;
    }

    public String getCnSecondaryCategory() {
        return cnSecondaryCategory;
    }

    public void setCnSecondaryCategory(String cnSecondaryCategory) {
        this.cnSecondaryCategory = cnSecondaryCategory;
    }

    public String getCnSecondaryCategoryName() {
        return cnSecondaryCategoryName;
    }

    public void setCnSecondaryCategoryName(String cnSecondaryCategoryName) {
        this.cnSecondaryCategoryName = cnSecondaryCategoryName;
    }

    public String getCnTertiaryCategory() {
        return cnTertiaryCategory;
    }

    public void setCnTertiaryCategory(String cnTertiaryCategory) {
        this.cnTertiaryCategory = cnTertiaryCategory;
    }

    public String getCnTertiaryCategoryName() {
        return cnTertiaryCategoryName;
    }

    public void setCnTertiaryCategoryName(String cnTertiaryCategoryName) {
        this.cnTertiaryCategoryName = cnTertiaryCategoryName;
    }

    public String getCnQuaternaryCategory() {
        return cnQuaternaryCategory;
    }

    public void setCnQuaternaryCategory(String cnQuaternaryCategory) {
        this.cnQuaternaryCategory = cnQuaternaryCategory;
    }

    public String getCnQuaternaryCategoryName() {
        return cnQuaternaryCategoryName;
    }

    public void setCnQuaternaryCategoryName(String cnQuaternaryCategoryName) {
        this.cnQuaternaryCategoryName = cnQuaternaryCategoryName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getQuotationFlag()
    {
        return quotationFlag;
    }
    public void setFutureField1(String futureField1) 
    {
        this.futureField1 = futureField1;
    }

    public String getFutureField1() 
    {
        return futureField1;
    }
    public void setFutureField2(String futureField2) 
    {
        this.futureField2 = futureField2;
    }

    public String getFutureField2() 
    {
        return futureField2;
    }
    public void setFutureField3(String futureField3) 
    {
        this.futureField3 = futureField3;
    }

    public String getFutureField3() 
    {
        return futureField3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
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
            .append("productName", getProductName())
            .append("domesticImportedType", getDomesticImportedType())
            .append("productModel", getProductModel())
            .append("quotationFlag", getQuotationFlag())
            .append("futureField1", getFutureField1())
            .append("futureField2", getFutureField2())
            .append("futureField3", getFutureField3())
            .toString();
    }
}
