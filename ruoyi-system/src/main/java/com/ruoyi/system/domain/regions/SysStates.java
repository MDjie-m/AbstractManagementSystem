package com.ruoyi.system.domain.regions;

import com.ruoyi.common.core.domain.BaseEntity;

/**
* 省份（州）
*/
public class SysStates extends BaseEntity {

    /**
    * id
    */
    private Integer id;
    /**
    * 所属国家代码
    */
    private Integer countryId;
    /**
    * 省份(州)编码
    */
    private String code;
    /**
    * 英文名称
    */
    private String name;
    /**
    * 中文名称
    */
    private String cname;
    /**
    * 小写英文名称
    */
    private String lowerName;
    /**
    * 全称编码
    */
    private String codeFull;
    /**
     * 手机区号
     */
    private String mobilePrefix;
    /**
    * 美国地区id
    */
    private Integer areaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getLowerName() {
        return lowerName;
    }

    public void setLowerName(String lowerName) {
        this.lowerName = lowerName;
    }

    public String getCodeFull() {
        return codeFull;
    }

    public void setCodeFull(String codeFull) {
        this.codeFull = codeFull;
    }

    public String getMobilePrefix() {
        return mobilePrefix;
    }

    public void setMobilePrefix(String mobilePrefix) {
        this.mobilePrefix = mobilePrefix;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}
