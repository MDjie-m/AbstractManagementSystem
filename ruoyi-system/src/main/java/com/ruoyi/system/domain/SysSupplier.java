package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.annotation.EnumFiledConvert;
import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.enums.DesensitizedType;
import com.ruoyi.common.enums.converter.EasyExcelConvert;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商对象 sys_supplier
 *
 * @author lyj
 * @date 2024-07-22
 */
@ExcelIgnoreUnannotated
public class SysSupplier extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商id
     */
    @ExcelIgnore
    private String supplierId;

    /**
     * 标签：0：供应商、商家，1：供应商，2.商家
     */
    @ExcelProperty(value = "标签", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-供应商、商家,1-供应商,2-商家")
    private Integer label;

    /**
     * 性质：0-厂家、贸易商，1-厂家，2-贸易商
     */
    @ExcelProperty(value = "性质", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-厂家、贸易商,1-厂家,2-贸易商")
    private Integer nature;

    /**
     * 国家
     */
    @ExcelProperty("国家")
    private String country;

    /**
     * 省份
     */
    @ExcelProperty("省份")
    private String province;

    /**
     * 市
     */
    @ExcelProperty("市")
    private String city;

    /**
     * 地区
     */
    @ExcelProperty("地区")
    private String area;

    /**
     * 详细地址
     */
    @ExcelProperty("详细地址")
    private String detailedAddress;

    /**
     * 经营类别,多条一二级分类，以 “，”隔开
     */
    @ExcelProperty("经营类别")
    private String businessCategory;

    /**
     * 主营类别,仅有一条一二级分类
     */
    @ExcelProperty("主营类别")
    private String mainCategory;

    /**
     * 主营产品id,暂时废弃
     */
    @ExcelIgnore
    @JsonIgnore
    private String mainProductId;

    /**
     * 主营产品名称
     */
    @ExcelProperty("主营产品")
    private String mainProduct;

    /**
     * 注册编号
     */
    @ExcelProperty("注册编号")
    private String registrationNo;

    /**
     * 在华注册编号
     */
    @ExcelProperty("在华注册编号")
    private String registrationNoInChina;

    /**
     * 企业名称（中文）
     */
    @ExcelProperty("企业名称")
    private String supplierNameCn;

    /**
     * 企业名称（英文）
     */
    @ExcelProperty("企业名称（英文）")
    private String supplierNameEn;

    /**
     * 企业名称（本国语）
     */
    @ExcelProperty("企业名称（本国语）")
    private String supplierNameOwn;

    /**
     * 注册时间
     */
    @ExcelProperty("注册时间")
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date registrationTime;

    /**
     * 注册时间有效期
     */
    @ExcelProperty("注册时间有效期")
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date registrationTimeValidityPeriod;

    /**
     * 法人
     */
    @ExcelProperty("法人")
    @Sensitive(desensitizedType = DesensitizedType.USERNAME)
    private String legalPerson;

    /**
     * 法人电话
     */
    @ExcelProperty("法人电话")
    @Sensitive(desensitizedType = DesensitizedType.PHONE)
    private String legalPersonTelephone;

    /**
     *  法人电子邮件
     */
    @ExcelProperty("法人电子邮件")
    @Sensitive(desensitizedType = DesensitizedType.EMAIL)
    private String legalPersonEmail;

    /**
     * 负责人
     */
    @ExcelProperty("负责人")
    @Sensitive(desensitizedType = DesensitizedType.USERNAME)
    private String principalName;

    /**
     * 负责人电话
     */
    @ExcelProperty("负责人电话")
    @Sensitive(desensitizedType = DesensitizedType.PHONE)
    private String principalTelephone;

    /**
     * 负责人电子邮件
     */
    @ExcelProperty("负责人电子邮件")
    @Sensitive(desensitizedType = DesensitizedType.EMAIL)
    private String principalEmail;

    /**
     * 分类：0：国产，1：进口
     */
    @ExcelProperty(value = "分类", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-国产,1-进口")
    private Integer classification;

    /**
     * 考察评级:A、B、C、D、E
     */
    @ExcelProperty("考察评级")
    private String rate;

    /**
     * 审核评级:A、B、C、D、E
     */
    @ExcelProperty("审核评级")
    private String reviewRating;

    /**
     * 是否删除：0：未删除，1：已删除
     */
    @ExcelProperty(value = "是否删除", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-未删除,1-已删除")
    @JsonIgnore
    private Integer deleteFlag;

    /**
     * 考察情况id
     */
    @ExcelIgnore
    private String inspectionId;

    /**
     * 审核状态:0：待审核:1：已通过:2：未通过
     */
    @ExcelProperty(value = "审核状态", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-待审核,1-已审核,2-已通过")
    private Integer auditStatus;

    /**
     * 考察状态：0候选，1：待考察，2：已考察
     */
    @ExcelProperty(value = "考察状态", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-候选,1-待考察,2-已考察")
    private Integer inspectionStatus;

    /**
     * 考察审核状态：0：待审核:1：已通过:2：未通过
     */
    @ExcelProperty(value = "考察审核状态", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-待审核,1-已通过,2-未通过")
    private Integer inspectionAuditStatus;

    /**
     * 数据来源：0：供应商入驻，1：系统新增，2：批量导入
     */
    @ExcelProperty(value = "数据来源", converter = EasyExcelConvert.class)
    @EnumFiledConvert(enumMap = "0-供应商入驻,1-系统新增,2-批量导入")
    private Integer dataSources;

    /**
     * 入驻时间
     */
    @ExcelIgnore
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date entryDate;

    /**
     * 头像地址
     */
    @ExcelIgnore
    private String headPortraitPath;

    /**
     * 图片地址
     */
    @ExcelIgnore
    private String imagePath;

    /**
     * 视频地址
     */
    @ExcelIgnore
    private String videoPath;

    /**
     * 备注
     */
    @ExcelIgnore
    private String remark;

    /**
     * 预留字段1
     */
    @ExcelIgnore
    @JsonIgnore
    private String futureField1;

    /**
     * 预留字段2
     */
    @ExcelIgnore
    @JsonIgnore
    private String futureField2;

    /**
     * 预留字段3
     */
    @ExcelIgnore
    @JsonIgnore
    private String futureField3;

    /**
     * 预留字段4
     */
    @ExcelIgnore
    @JsonIgnore
    private String futureField4;

    /**
     * 预留字段5
     */
    @ExcelIgnore
    @JsonIgnore
    private String futureField5;

    /**
     * 产品信息
     */
    @ExcelIgnore
    @JsonIgnore
    private List<SysProduct> sysProductList;

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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
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

    public String getHeadPortraitPath() {
        return headPortraitPath;
    }

    public void setHeadPortraitPath(String headPortraitPath) {
        this.headPortraitPath = headPortraitPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFutureField1() {
        return futureField1;
    }

    public void setFutureField1(String futureField1) {
        this.futureField1 = futureField1;
    }

    public String getFutureField2() {
        return futureField2;
    }

    public void setFutureField2(String futureField2) {
        this.futureField2 = futureField2;
    }

    public String getFutureField3() {
        return futureField3;
    }

    public void setFutureField3(String futureField3) {
        this.futureField3 = futureField3;
    }

    public String getFutureField4() {
        return futureField4;
    }

    public void setFutureField4(String futureField4) {
        this.futureField4 = futureField4;
    }

    public String getFutureField5() {
        return futureField5;
    }

    public void setFutureField5(String futureField5) {
        this.futureField5 = futureField5;
    }

    public List<SysProduct> getSysProductList() {
        return sysProductList;
    }

    public void setSysProductList(List<SysProduct> sysProductList) {
        this.sysProductList = sysProductList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("supplierId", getSupplierId())
                .append("label", getLabel())
                .append("nature", getNature())
                .append("country", getCountry())
                .append("province", getProvince())
                .append("city", getCity())
                .append("area", getArea())
                .append("detailedAddress", getDetailedAddress())
                .append("businessCategory", getBusinessCategory())
                .append("mainCategory", getMainCategory())
                .append("mainProductId", getMainProductId())
                .append("mainProduct", getMainProduct())
                .append("registrationNo", getRegistrationNo())
                .append("registrationNoInChina", getRegistrationNoInChina())
                .append("supplierNameCn", getSupplierNameCn())
                .append("supplierNameEn", getSupplierNameEn())
                .append("supplierNameOwn", getSupplierNameOwn())
                .append("registrationTime", getRegistrationTime())
                .append("registrationTimeValidityPeriod", getRegistrationTimeValidityPeriod())
                .append("legalPerson", getLegalPerson())
                .append("legalPersonTelephone", getLegalPersonTelephone())
                .append("legalPersonEmail", getLegalPersonEmail())
                .append("principalName", getPrincipalName())
                .append("principalTelephone", getPrincipalTelephone())
                .append("principalEmail", getPrincipalEmail())
                .append("classification", getClassification())
                .append("rate", getRate())
                .append("reviewRating", getReviewRating())
                .append("deleteFlag", getDeleteFlag())
                .append("inspectionId", getInspectionId())
                .append("auditStatus", getAuditStatus())
                .append("inspectionStatus", getInspectionStatus())
                .append("inspectionAuditStatus", getInspectionAuditStatus())
                .append("dataSources", getDataSources())
                .append("entryDate", getEntryDate())
                .append("headPortraitPath", getHeadPortraitPath())
                .append("imagePath", getImagePath())
                .append("videoPath", getVideoPath())
                .append("remark", getRemark())
                .append("futureField1", getFutureField1())
                .append("futureField2", getFutureField2())
                .append("futureField3", getFutureField3())
                .append("futureField4", getFutureField4())
                .append("futureField5", getFutureField5())
                .append("sysProductList", getSysProductList())
                .toString();
    }
}
