package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.enums.DesensitizedType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.Size;

/**
 * 用户详细信息对象 sys_user_detail
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
public class SysUserDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户详情表id */
    private String userDetailsId;

    /** 中文姓名 */
    @Excel(name = "中文姓名")
    @Sensitive(desensitizedType = DesensitizedType.USERNAME)
    private String userNameCn;

    /** 英文姓名 */
    @Excel(name = "英文姓名")
    @Sensitive(desensitizedType = DesensitizedType.USERNAME)
    private String userNameEn;

    /** 本国姓名 */
    @Excel(name = "本国姓名")
    @Sensitive(desensitizedType = DesensitizedType.USERNAME)
    private String userNameOwn;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String area;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String detailedAddress;

    /** 负责品类 */
    @Excel(name = "负责品类")
    private String principalProductType;

    /** 负责产品 */
    @Excel(name = "负责产品")
    private String principalProduct;

    /** 公司工号 */
    @Excel(name = "公司工号")
    private String companyCid;

    /** 身份证号 */
    @Excel(name = "身份证号")
    @Sensitive(desensitizedType = DesensitizedType.ID_CARD)
    private String identificationNumber;

    /** 0-未删除，1-已删除 */
    @Excel(name = "0-未删除，1-已删除")
    private Long delFlag;

    public SysUserDetail() {
    }

    public SysUserDetail( String userDetailsId, String userNameCn, String userNameEn, String userNameOwn, String country, String province, String city, String area, String detailedAddress, String principalProductType, String principalProduct, String companyCid, String identificationNumber, Long delFlag) {
        this.userDetailsId = userDetailsId;
        this.userNameCn = userNameCn;
        this.userNameEn = userNameEn;
        this.userNameOwn = userNameOwn;
        this.country = country;
        this.province = province;
        this.city = city;
        this.area = area;
        this.detailedAddress = detailedAddress;
        this.principalProductType = principalProductType;
        this.principalProduct = principalProduct;
        this.companyCid = companyCid;
        this.identificationNumber = identificationNumber;
        this.delFlag = delFlag;
    }

    public void setUserDetailsId(String userDetailsId) 
    {
        this.userDetailsId = userDetailsId;
    }

    public String getUserDetailsId() 
    {
        return userDetailsId;
    }
    public void setUserNameCn(String userNameCn) 
    {
        this.userNameCn = userNameCn;
    }

    public String getUserNameCn() 
    {
        return userNameCn;
    }
    public void setUserNameEn(String userNameEn) 
    {
        this.userNameEn = userNameEn;
    }

    public String getUserNameEn() 
    {
        return userNameEn;
    }
    public void setUserNameOwn(String userNameOwn) 
    {
        this.userNameOwn = userNameOwn;
    }

    public String getUserNameOwn() 
    {
        return userNameOwn;
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

    public String getDetailedAddress() 
    {
        return detailedAddress;
    }
    public void setPrincipalProductType(String principalProductType) 
    {
        this.principalProductType = principalProductType;
    }

    public String getPrincipalProductType() 
    {
        return principalProductType;
    }
    public void setPrincipalProduct(String principalProduct) 
    {
        this.principalProduct = principalProduct;
    }

    public String getPrincipalProduct() 
    {
        return principalProduct;
    }
    public void setCompanyCid(String companyCid) 
    {
        this.companyCid = companyCid;
    }

    public String getCompanyCid() 
    {
        return companyCid;
    }
    public void setIdentificationNumber(String identificationNumber)
    {
        this.identificationNumber = identificationNumber;
    }
    @Size(min = 0, max = 18, message = "身份证长度不能超过18个字符")
    public String getIdentificationNumber()
    {
        return identificationNumber;
    }
    public void setDelFlag(Long delFlag)
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userDetailsId", getUserDetailsId())
            .append("userNameCn", getUserNameCn())
            .append("userNameEn", getUserNameEn())
            .append("userNameOwn", getUserNameOwn())
            .append("country", getCountry())
            .append("detailedAddress", getDetailedAddress())
            .append("principalProductType", getPrincipalProductType())
            .append("principalProduct", getPrincipalProduct())
            .append("companyCid", getCompanyCid())
            .append("identificationNumber", getIdentificationNumber())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }

    /**
     * 获取
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }
}
