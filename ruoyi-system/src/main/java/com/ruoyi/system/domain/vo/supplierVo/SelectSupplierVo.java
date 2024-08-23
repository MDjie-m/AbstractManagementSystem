package com.ruoyi.system.domain.vo.supplierVo;

import com.alibaba.excel.annotation.format.DateTimeFormat;

import java.util.Date;

/**
 * 供应商筛选Vo
 */
public class SelectSupplierVo {

    /**
     * 标签：0：供应商、商家，1：供应商，2.商家
     */
    private Integer label;

    /**
     * 性质：0-厂家、贸易商，1-厂家，2-贸易商
     */
    private Integer nature;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 地区
     */
    private String area;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 经营类别,多条一二级分类，以 “，”隔开
     */
    private String businessCategory;

    /**
     * 主营类别,仅有一条一二级分类
     */
    private String mainCategory;

    /**
     * 企业名称（中文）
     */
    private String supplierNameCn;

    /**
     * 企业名称（英文）
     */
    private String supplierNameEn;

    /**
     * 企业名称（本国语）
     */
    private String supplierNameOwn;

    /**
     * 注册时间
     */
    @DateTimeFormat("yyyy-mm-dd")
    private Date registrationTime;

    /**
     * 注册时间有效期
     */
    @DateTimeFormat("yyyy-mm-dd")
    private Date registrationTimeValidityPeriod;

    /**
     * 分类：0：国产，1：进口
     */
    private Integer classification;

    /**
     * 考察评级:A、B、C、D、E
     */
    private String rate;

    /**
     * 审核评级:A、B、C、D、E
     */
    private String reviewRating;

    /**
     * 审核状态:0：待审核:1：已通过:2：未通过
     */
    private Integer auditStatus;

    /**
     * 考察状态：0候选，1：待考察，2：已考察
     */
    private Integer inspectionStatus;

    /**
     * 考察审核状态：0：待审核:1：已通过:2：未通过
     */
    private Integer inspectionAuditStatus;

    /**
     * 数据来源：0：供应商入驻，1：系统新增，2：批量导入
     */
    private Integer dataSources;

    /**
     * 起止入驻时间
     */
    @DateTimeFormat("yyyy-mm-dd")
    private Date startEntryDate;

    /**
     * 止入驻时间
     */
    @DateTimeFormat("yyyy-mm-dd")
    private Date endEntryDate;

    /**
     * 内部一级分类
     */
    private String cnPrimaryCategory;

    /**
     * 内部二级分类
     */
    private String cnSecondaryCategory;

    /**
     * 内部三级分类
     */
    private String cnTertiaryCategory;

    /**
     * 内部四级分类
     */
    private String cnQuaternaryCategory;

    /**
     * 内部五级分类
     */
    private String cnFifthCategory;

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSupplierNameCn() {
        return supplierNameCn;
    }

    public void setSupplierNameCn(String supplierNameCn) {
        this.supplierNameCn = supplierNameCn;
    }

    public String getSupplierNameEn() {
        return supplierNameEn;
    }

    public void setSupplierNameEn(String supplierNameEn) {
        this.supplierNameEn = supplierNameEn;
    }

    public String getSupplierNameOwn() {
        return supplierNameOwn;
    }

    public void setSupplierNameOwn(String supplierNameOwn) {
        this.supplierNameOwn = supplierNameOwn;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getRegistrationTimeValidityPeriod() {
        return registrationTimeValidityPeriod;
    }

    public void setRegistrationTimeValidityPeriod(Date registrationTimeValidityPeriod) {
        this.registrationTimeValidityPeriod = registrationTimeValidityPeriod;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(Integer inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public Integer getInspectionAuditStatus() {
        return inspectionAuditStatus;
    }

    public void setInspectionAuditStatus(Integer inspectionAuditStatus) {
        this.inspectionAuditStatus = inspectionAuditStatus;
    }

    public Integer getDataSources() {
        return dataSources;
    }

    public void setDataSources(Integer dataSources) {
        this.dataSources = dataSources;
    }

    public Date getStartEntryDate() {
        return startEntryDate;
    }

    public void setStartEntryDate(Date startEntryDate) {
        this.startEntryDate = startEntryDate;
    }

    public String getCnPrimaryCategory() {
        return cnPrimaryCategory;
    }

    public void setCnPrimaryCategory(String cnPrimaryCategory) {
        this.cnPrimaryCategory = cnPrimaryCategory;
    }

    public String getCnSecondaryCategory() {
        return cnSecondaryCategory;
    }

    public void setCnSecondaryCategory(String cnSecondaryCategory) {
        this.cnSecondaryCategory = cnSecondaryCategory;
    }

    public String getCnTertiaryCategory() {
        return cnTertiaryCategory;
    }

    public void setCnTertiaryCategory(String cnTertiaryCategory) {
        this.cnTertiaryCategory = cnTertiaryCategory;
    }

    public String getCnQuaternaryCategory() {
        return cnQuaternaryCategory;
    }

    public void setCnQuaternaryCategory(String cnQuaternaryCategory) {
        this.cnQuaternaryCategory = cnQuaternaryCategory;
    }

    public String getCnFifthCategory() {
        return cnFifthCategory;
    }

    public void setCnFifthCategory(String cnFifthCategory) {
        this.cnFifthCategory = cnFifthCategory;
    }

    public Date getEndEntryDate() {
        return endEntryDate;
    }

    public void setEndEntryDate(Date endEntryDate) {
        this.endEntryDate = endEntryDate;
    }
}
