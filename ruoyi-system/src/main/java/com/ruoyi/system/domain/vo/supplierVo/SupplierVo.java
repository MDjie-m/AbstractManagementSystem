package com.ruoyi.system.domain.vo.supplierVo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.enums.DesensitizedType;

import java.util.Date;

/**
 * 查询供应商
 */
public class SupplierVo {
    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 页数
     */
    private Integer pageSize;

    /**
     * 供应商id
     */
    private String supplierId;

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
     * 主营产品id
     */
    private String mainProductId;

    /**
     * 主营产品名称
     */
    private String mainProduct;

    /**
     * 注册编号
     */
    private String registrationNo;

    /**
     * 在华注册编号
     */
    private String registrationNoInChina;

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
     * 法人
     */
    @Sensitive(desensitizedType = DesensitizedType.USERNAME)
    private String legalPerson;

    /**
     * 法人电话
     */
    @Sensitive(desensitizedType = DesensitizedType.PHONE)
    private String legalPersonTelephone;

    /**
     *  法人电子邮件
     */
    @Sensitive(desensitizedType = DesensitizedType.EMAIL)
    private String legalPersonEmail;

    /**
     * 负责人
     */
    @Sensitive(desensitizedType = DesensitizedType.USERNAME)
    private String principalName;

    /**
     * 负责人电话
     */
    @Sensitive(desensitizedType = DesensitizedType.PHONE)
    private String principalTelephone;

    /**
     * 负责人电子邮件
     */
    @Sensitive(desensitizedType = DesensitizedType.EMAIL)
    private String principalEmail;

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
     * 考察情况id
     */
    private String inspectionId;

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
     * 入驻时间
     */
    @DateTimeFormat("yyyy-mm-dd")
    private Date entryDate;

    /**
     * 备注
     */
    @ExcelIgnore
    private String remark;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

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

    public String getMainProductId() {
        return mainProductId;
    }

    public void setMainProductId(String mainProductId) {
        this.mainProductId = mainProductId;
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getRegistrationNoInChina() {
        return registrationNoInChina;
    }

    public void setRegistrationNoInChina(String registrationNoInChina) {
        this.registrationNoInChina = registrationNoInChina;
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

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonTelephone() {
        return legalPersonTelephone;
    }

    public void setLegalPersonTelephone(String legalPersonTelephone) {
        this.legalPersonTelephone = legalPersonTelephone;
    }

    public String getLegalPersonEmail() {
        return legalPersonEmail;
    }

    public void setLegalPersonEmail(String legalPersonEmail) {
        this.legalPersonEmail = legalPersonEmail;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalTelephone() {
        return principalTelephone;
    }

    public void setPrincipalTelephone(String principalTelephone) {
        this.principalTelephone = principalTelephone;
    }

    public String getPrincipalEmail() {
        return principalEmail;
    }

    public void setPrincipalEmail(String principalEmail) {
        this.principalEmail = principalEmail;
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

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }
}
