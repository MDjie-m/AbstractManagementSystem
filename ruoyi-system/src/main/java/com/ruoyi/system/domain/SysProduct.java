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

    /** 产品参数 */
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
    private Integer sample;

    /** 产品规格 */
    private String specifications;

    /** 产品库存 */
    private Integer inventory;

    /** 产品状态(0-期货,1-现货) */
    private Integer status;

    /** 生产日期”yyyy-mm-dd” */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    /** 保质期 */
    private String shelfLife;

    /** 产品产地 */
    private String origin;

    /** 储存方式0:-冷冻1-冷鲜 */
    private Integer storageMode;

    /** 仓库地址 */
    private String warehouseAddress;

    /** 是否主营产品：0-不是，1-是 */
    private Integer mainProduct;

    /** 删除状态：0-未删除，1-已删除 */
    private int delFlag;

    /** 报价状态0未报/1 已报 */
    private Integer quoteStatus;

    /** 询价状态0 未询/1 已询 */
    private Integer inquiryStatus;

    /** 采购员id外键，用户表 */
    private Long buyerId;

    /** 询价清单标识0 不常/1 经常 */
    private Integer inquiryListFlag;

    /** 报价清单标识0 不常或经常都有/1 只有经常 */
    private Integer quoteListFlag;

    /** 备注 */
    private String remark;

    /** 品牌或者厂号 */
    private String mark;

    /** 单价 */
    private Double unitprice;

    /** 单价的单位 */
    private String unitpriceUnit;

    /** 起批数量 */
    private Integer startBatch;

    /** 起批数量的单位或者库存的单位都是这个 */
    private String batchUnit;

    /** 预留字段1 */
    private String futureField1;

    /** 预留字段2 */
    private String futureField2;

    /** 预留字段3 */
    private String futureField3;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getPrimaryCategoryName() {
        return primaryCategoryName;
    }

    public void setPrimaryCategoryName(String primaryCategoryName) {
        this.primaryCategoryName = primaryCategoryName;
    }

    public String getSecondaryCategory() {
        return secondaryCategory;
    }

    public void setSecondaryCategory(String secondaryCategory) {
        this.secondaryCategory = secondaryCategory;
    }

    public String getSecondaryCategoryName() {
        return secondaryCategoryName;
    }

    public void setSecondaryCategoryName(String secondaryCategoryName) {
        this.secondaryCategoryName = secondaryCategoryName;
    }

    public String getTertiaryCategory() {
        return tertiaryCategory;
    }

    public void setTertiaryCategory(String tertiaryCategory) {
        this.tertiaryCategory = tertiaryCategory;
    }

    public String getTertiaryCategoryName() {
        return tertiaryCategoryName;
    }

    public void setTertiaryCategoryName(String tertiaryCategoryName) {
        this.tertiaryCategoryName = tertiaryCategoryName;
    }

    public String getQuaternaryCategory() {
        return quaternaryCategory;
    }

    public void setQuaternaryCategory(String quaternaryCategory) {
        this.quaternaryCategory = quaternaryCategory;
    }

    public String getQuaternaryCategoryName() {
        return quaternaryCategoryName;
    }

    public void setQuaternaryCategoryName(String quaternaryCategoryName) {
        this.quaternaryCategoryName = quaternaryCategoryName;
    }

    public String getFifthCategory() {
        return fifthCategory;
    }

    public void setFifthCategory(String fifthCategory) {
        this.fifthCategory = fifthCategory;
    }

    public String getFifthCategoryName() {
        return fifthCategoryName;
    }

    public void setFifthCategoryName(String fifthCategoryName) {
        this.fifthCategoryName = fifthCategoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getDomesticImportedType() {
        return domesticImportedType;
    }

    public void setDomesticImportedType(Integer domesticImportedType) {
        this.domesticImportedType = domesticImportedType;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Integer getQuotationFlag() {
        return quotationFlag;
    }

    public void setQuotationFlag(Integer quotationFlag) {
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

    public Integer getSample() {
        return sample;
    }

    public void setSample(Integer sample) {
        this.sample = sample;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(Integer storageMode) {
        this.storageMode = storageMode;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Integer getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(Integer mainProduct) {
        this.mainProduct = mainProduct;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(Integer quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public Integer getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(Integer inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getInquiryListFlag() {
        return inquiryListFlag;
    }

    public void setInquiryListFlag(Integer inquiryListFlag) {
        this.inquiryListFlag = inquiryListFlag;
    }

    public Integer getQuoteListFlag() {
        return quoteListFlag;
    }

    public void setQuoteListFlag(Integer quoteListFlag) {
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    public String getUnitpriceUnit() {
        return unitpriceUnit;
    }

    public void setUnitpriceUnit(String unitpriceUnit) {
        this.unitpriceUnit = unitpriceUnit;
    }

    public Integer getStartBatch() {
        return startBatch;
    }

    public void setStartBatch(Integer startBatch) {
        this.startBatch = startBatch;
    }

    public String getBatchUnit() {
        return batchUnit;
    }

    public void setBatchUnit(String batchUnit) {
        this.batchUnit = batchUnit;
    }
}
