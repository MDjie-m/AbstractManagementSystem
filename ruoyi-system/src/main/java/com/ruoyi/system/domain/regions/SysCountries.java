package com.ruoyi.system.domain.regions;

import com.ruoyi.common.core.domain.BaseEntity;

/**
* 国家表
*/
public class SysCountries extends BaseEntity {

    /**
    * id
    */
    private Integer id;
    /**
    * 大洲id
    */
    private Integer continentId;
    /**
    * 地区代码
    */
    private String code;
    /**
    * 名称
    */
    private String name;
    /**
    * 全称
    */
    private String fullName;
    /**
    * 中文名称
    */
    private String cname;
    /**
    * 中文全称
    */
    private String fullCname;
    /**
    * 小写英文全称
    */
    private String lowerName;
    /**
     * 手机区号
     */
    private String mobilePrefix;
    /**
    * 备注
    */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContinentId() {
        return continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getFullCname() {
        return fullCname;
    }

    public void setFullCname(String fullCname) {
        this.fullCname = fullCname;
    }

    public String getLowerName() {
        return lowerName;
    }

    public void setLowerName(String lowerName) {
        this.lowerName = lowerName;
    }

    public String getMobilePrefix() {
        return mobilePrefix;
    }

    public void setMobilePrefix(String mobilePrefix) {
        this.mobilePrefix = mobilePrefix;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
