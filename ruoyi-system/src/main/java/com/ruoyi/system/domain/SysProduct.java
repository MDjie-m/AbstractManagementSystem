package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 产品对象 sys_product
 * 
 * @author tyc
 * @date 2024-08-16
 */
public class SysProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编号 */
    private String productId;

    /** 供应商id */
    private String supplierId;

    /** 供应商名称 */
    private String supplierName;

    /** 一级分类 */
    private String primaryCategory;

    /** 一级分类名称 */
    private String primaryCategoryName;

    /** 二级分类 */
    private String secondaryCategory;

    /** 二级分类名称 */
    private String secondaryCategoryName;

    /** 三级分类 */
    private String tertiaryCategory;

    /** 三级分类名称 */
    private String tertiaryCategoryName;

    /** 四级分类 */
    private String quaternaryCategory;

    /** 四级分类名称 */
    private String quaternaryCategoryName;

    /** 五级分类 */
    private String fifthCategory;

    /** 五级分类名称 */
    private String fifthCategoryName;

    /** 产品名称 */
    private String productName;

    /** 0-国产 1-进口 */
    private Integer domesticImportedType;

    /** 产品型号 */
    private String productModel;

    /** 是否可报价：0：不可报价，1：可报价 */
    private Integer quotationFlag;

    /** 内部一级分类，cn_primary_category */
    private String cnPrimaryCategory;

    /** 内部一级分类名称，cn_primary_category_name */
    private String cnPrimaryCategoryName;

    /** 内部二级分类，cn_secondary_category */
    private String cnSecondaryCategory;

    /** 内部二级分类名称，cn_secondary_category_name */
    private String cnSecondaryCategoryName;

    /** 内部三级分类，cn_tertiary_category */
    private String cnTertiaryCategory;

    /** 内部三级分类名称，cn_tertiary_category_name */
    private String cnTertiaryCategoryName;

    /** 内部四级分类，cn_quaternary_category */
    private String cnQuaternaryCategory;

    /** 内部四级分类名称，cn_quaternary_category_name */
    private String cnQuaternaryCategoryName;

    /** 五级分类 */
    private String cnFifthCategory;

    /** 五级分类名称 */
    private String cnFifthCategoryName;

    /** 标签id，以'/'区分，tag_id */
    private String tagId;

    /** 标签名称，以'/'区分，tag_name */
    private String tagName;

    /** 产品图片，以英文都好分隔 */
    private String productImage;

    /** 产品视频 */
    private String productVideo;

    /** 产品样品：0支持，1不支持 */
    private Long sample;

    /** 产品规格 */
    private String specifications;

    /** 产品库存 */
    private String inventory;

    /** 产品状态(0-期货,1-现货) */
    private int status;

    /** 生产日期”yyyy-mm-dd” */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    /** 保质期 */
    private String shelfLife;

    /** 产品产地 */
    private String origin;

    /** 储存方式0:-冷冻1-冷鲜 */
    private int storageMode;

    /** 仓库地址 */
    private String warehouse_address;

    /** 是否主营产品：0-不是，1-是 */
    private int mainProduct;

    /** 删除状态：0-未删除，1-已删除 */
    private int delFlag;

    /** 报价状态0未报/1 已报 */
    private int quoteStatus;

    /** 询价状态0 未询/1 已询 */
    private int inquiryStatus;

    /** 采购员id外键，用户表 */
    private String buyerId;

    /** 询价清单标识0 不常/1 经常 */
    private int inquiryListFlag;

    /** 报价清单标识0 不常/1 经常 */
    private int quoteListFlag;

    /** 备注 */
    private String remark;

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

    public String getCnFifthCategory() {
        return cnFifthCategory;
    }

    public void setCnFifthCategory(String cnFifthCategory) {
        this.cnFifthCategory = cnFifthCategory;
    }

    public String getCnFifthCategoryName() {
        return cnFifthCategoryName;
    }

    public void setCnFifthCategoryName(String cnFifthCategoryName) {
        this.cnFifthCategoryName = cnFifthCategoryName;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductVideo() {
        return productVideo;
    }

    public void setProductVideo(String productVideo) {
        this.productVideo = productVideo;
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

    public Long getSample() {
        return sample;
    }

    public void setSample(Long sample) {
        this.sample = sample;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(int storageMode) {
        this.storageMode = storageMode;
    }

    public String getWarehouse_address() {
        return warehouse_address;
    }

    public void setWarehouse_address(String warehouse_address) {
        this.warehouse_address = warehouse_address;
    }

    public int getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(int mainProduct) {
        this.mainProduct = mainProduct;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(int quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public int getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(int inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public int getInquiryListFlag() {
        return inquiryListFlag;
    }

    public void setInquiryListFlag(int inquiryListFlag) {
        this.inquiryListFlag = inquiryListFlag;
    }

    public int getQuoteListFlag() {
        return quoteListFlag;
    }

    public void setQuoteListFlag(int quoteListFlag) {
        this.quoteListFlag = quoteListFlag;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysProduct{" +
                "productId='" + productId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", primaryCategory='" + primaryCategory + '\'' +
                ", primaryCategoryName='" + primaryCategoryName + '\'' +
                ", secondaryCategory='" + secondaryCategory + '\'' +
                ", secondaryCategoryName='" + secondaryCategoryName + '\'' +
                ", tertiaryCategory='" + tertiaryCategory + '\'' +
                ", tertiaryCategoryName='" + tertiaryCategoryName + '\'' +
                ", quaternaryCategory='" + quaternaryCategory + '\'' +
                ", quaternaryCategoryName='" + quaternaryCategoryName + '\'' +
                ", fifthCategory='" + fifthCategory + '\'' +
                ", fifthCategoryName='" + fifthCategoryName + '\'' +
                ", productName='" + productName + '\'' +
                ", domesticImportedType=" + domesticImportedType +
                ", productModel='" + productModel + '\'' +
                ", quotationFlag=" + quotationFlag +
                ", cnPrimaryCategory='" + cnPrimaryCategory + '\'' +
                ", cnPrimaryCategoryName='" + cnPrimaryCategoryName + '\'' +
                ", cnSecondaryCategory='" + cnSecondaryCategory + '\'' +
                ", cnSecondaryCategoryName='" + cnSecondaryCategoryName + '\'' +
                ", cnTertiaryCategory='" + cnTertiaryCategory + '\'' +
                ", cnTertiaryCategoryName='" + cnTertiaryCategoryName + '\'' +
                ", cnQuaternaryCategory='" + cnQuaternaryCategory + '\'' +
                ", cnQuaternaryCategoryName='" + cnQuaternaryCategoryName + '\'' +
                ", cnFifthCategory='" + cnFifthCategory + '\'' +
                ", cnFifthCategoryName='" + cnFifthCategoryName + '\'' +
                ", tagId='" + tagId + '\'' +
                ", tagName='" + tagName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productVideo='" + productVideo + '\'' +
                ", sample=" + sample +
                ", specifications='" + specifications + '\'' +
                ", inventory='" + inventory + '\'' +
                ", status=" + status +
                ", productionDate=" + productionDate +
                ", shelfLife='" + shelfLife + '\'' +
                ", origin='" + origin + '\'' +
                ", storageMode=" + storageMode +
                ", warehouse_address='" + warehouse_address + '\'' +
                ", mainProduct=" + mainProduct +
                ", delFlag=" + delFlag +
                ", quoteStatus=" + quoteStatus +
                ", inquiryStatus=" + inquiryStatus +
                ", buyerId='" + buyerId + '\'' +
                ", inquiryListFlag=" + inquiryListFlag +
                ", quoteListFlag=" + quoteListFlag +
                ", remark='" + remark + '\'' +
                '}';
    }
}
