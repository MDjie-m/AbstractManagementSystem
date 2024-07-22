package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商对象 sys_supplier
 * 
 * @author xgg
 * @date 2024-07-21
 */
public class SysSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商id */
    private String supplierId;

    /** 标签：0：供应商、商家，1：供应商，2.商家 */
    @Excel(name = "标签：0：供应商、商家，1：供应商，2.商家")
    private Integer label;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 地区 */
    @Excel(name = "地区")
    private String area;

    /** 产品分类:进口/牛肉，国产/鸡肉 */
    @Excel(name = "产品分类:进口/牛肉，国产/鸡肉")
    private String productType;

    /** 注册编号 */
    @Excel(name = "注册编号")
    private String registrationNo;

    /** 在华注册编号 */
    @Excel(name = "在华注册编号")
    private String registrationNoInChina;

    /** 企业名称（中文） */
    @Excel(name = "企业名称", readConverterExp = "中=文")
    private String supplierNameCn;

    /** 企业名称（英文） */
    @Excel(name = "企业名称", readConverterExp = "英=文")
    private String supplierNameEn;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /** 注册时间有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTimeValidityPeriod;

    /** 考察评级:A、B、C、D、E */
    @Excel(name = "考察评级:A、B、C、D、E")
    private String rate;

    /** 审核评级:A、B、C、D、E */
    @Excel(name = "审核评级:A、B、C、D、E")
    private String reviewRating;

    /** 是否删除：0：未删除，1：已删除 */
    @Excel(name = "是否删除：0：未删除，1：已删除")
    private Integer deleteFlag;

    /** 考察情况id */
    @Excel(name = "考察情况id")
    private String inspectionId;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String telephone;

    /** 电子邮件 */
    @Excel(name = "电子邮件")
    private String email;

    /** 分类：0：国产，1：进口 */
    @Excel(name = "分类：0：国产，1：进口")
    private Integer classification;

    /** 审核状态:0：待审核:1：已通过:2：未通过 */
    @Excel(name = "审核状态:0：待审核:1：已通过:2：未通过")
    private Integer auditStatus;

    /** 考察状态：0候选，1：待考察，2：已考察 */
    @Excel(name = "考察状态：0候选，1：待考察，2：已考察")
    private Integer inspectionStatus;

    /** 考察审核状态：0：待审核:1：已通过:2：未通过 */
    @Excel(name = "考察审核状态：0：待审核:1：已通过:2：未通过")
    private Integer inspectionAuditStatus;

    /** 数据来源：0：供应商入驻，1：系统新增，2：批量导入 */
    @Excel(name = "数据来源：0：供应商入驻，1：系统新增，2：批量导入")
    private Integer dataSources;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String futureField1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String futureField2;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private String futureField3;

    /** 预留字段4 */
    @Excel(name = "预留字段4")
    private String futureField4;

    /** 预留字段5 */
    @Excel(name = "预留字段5")
    private String futureField5;

    /** 产品信息 */
    private List<SysProduct> sysProductList;

    public void setSupplierId(String supplierId) 
    {
        this.supplierId = supplierId;
    }

    public String getSupplierId() 
    {
        return supplierId;
    }
    public void setLabel(Integer label)
    {
        this.label = label;
    }

    public Integer getLabel()
    {
        return label;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setProductType(String productType) 
    {
        this.productType = productType;
    }

    public String getProductType() 
    {
        return productType;
    }
    public void setRegistrationNo(String registrationNo) 
    {
        this.registrationNo = registrationNo;
    }

    public String getRegistrationNo() 
    {
        return registrationNo;
    }
    public void setRegistrationNoInChina(String registrationNoInChina) 
    {
        this.registrationNoInChina = registrationNoInChina;
    }

    public String getRegistrationNoInChina() 
    {
        return registrationNoInChina;
    }
    public void setSupplierNameCn(String supplierNameCn) 
    {
        this.supplierNameCn = supplierNameCn;
    }

    public String getSupplierNameCn() 
    {
        return supplierNameCn;
    }
    public void setSupplierNameEn(String supplierNameEn) 
    {
        this.supplierNameEn = supplierNameEn;
    }

    public String getSupplierNameEn() 
    {
        return supplierNameEn;
    }
    public void setRegistrationTime(Date registrationTime) 
    {
        this.registrationTime = registrationTime;
    }

    public Date getRegistrationTime() 
    {
        return registrationTime;
    }
    public void setRegistrationTimeValidityPeriod(Date registrationTimeValidityPeriod) 
    {
        this.registrationTimeValidityPeriod = registrationTimeValidityPeriod;
    }

    public Date getRegistrationTimeValidityPeriod() 
    {
        return registrationTimeValidityPeriod;
    }
    public void setRate(String rate) 
    {
        this.rate = rate;
    }

    public String getRate() 
    {
        return rate;
    }
    public void setReviewRating(String reviewRating) 
    {
        this.reviewRating = reviewRating;
    }

    public String getReviewRating() 
    {
        return reviewRating;
    }
    public void setDeleteFlag(Integer deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeleteFlag()
    {
        return deleteFlag;
    }
    public void setInspectionId(String inspectionId) 
    {
        this.inspectionId = inspectionId;
    }

    public String getInspectionId() 
    {
        return inspectionId;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setClassification(Integer classification)
    {
        this.classification = classification;
    }

    public Integer getClassification()
    {
        return classification;
    }
    public void setAuditStatus(Integer auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus()
    {
        return auditStatus;
    }
    public void setInspectionStatus(Integer inspectionStatus)
    {
        this.inspectionStatus = inspectionStatus;
    }

    public Integer getInspectionStatus()
    {
        return inspectionStatus;
    }
    public void setInspectionAuditStatus(Integer inspectionAuditStatus)
    {
        this.inspectionAuditStatus = inspectionAuditStatus;
    }

    public Integer getInspectionAuditStatus()
    {
        return inspectionAuditStatus;
    }
    public void setDataSources(Integer dataSources)
    {
        this.dataSources = dataSources;
    }

    public Integer getDataSources()
    {
        return dataSources;
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
    public void setFutureField4(String futureField4) 
    {
        this.futureField4 = futureField4;
    }

    public String getFutureField4() 
    {
        return futureField4;
    }
    public void setFutureField5(String futureField5) 
    {
        this.futureField5 = futureField5;
    }

    public String getFutureField5() 
    {
        return futureField5;
    }

    public List<SysProduct> getSysProductList()
    {
        return sysProductList;
    }

    public void setSysProductList(List<SysProduct> sysProductList)
    {
        this.sysProductList = sysProductList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("supplierId", getSupplierId())
            .append("label", getLabel())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("area", getArea())
            .append("productType", getProductType())
            .append("registrationNo", getRegistrationNo())
            .append("registrationNoInChina", getRegistrationNoInChina())
            .append("supplierNameCn", getSupplierNameCn())
            .append("supplierNameEn", getSupplierNameEn())
            .append("registrationTime", getRegistrationTime())
            .append("registrationTimeValidityPeriod", getRegistrationTimeValidityPeriod())
            .append("rate", getRate())
            .append("reviewRating", getReviewRating())
            .append("deleteFlag", getDeleteFlag())
            .append("inspectionId", getInspectionId())
            .append("contacts", getContacts())
            .append("telephone", getTelephone())
            .append("email", getEmail())
            .append("classification", getClassification())
            .append("auditStatus", getAuditStatus())
            .append("inspectionStatus", getInspectionStatus())
            .append("inspectionAuditStatus", getInspectionAuditStatus())
            .append("dataSources", getDataSources())
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
