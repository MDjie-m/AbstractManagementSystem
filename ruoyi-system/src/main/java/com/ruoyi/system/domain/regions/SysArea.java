package com.ruoyi.system.domain.regions;

import com.ruoyi.common.core.domain.BaseEntity;

/**
* 地区表
*/
public class SysArea extends BaseEntity {

    /**
    * id
    */
    private Integer id;
    /**
    * 国家id
    */
    private Integer countryId;
    /**
    * 编码
    */
    private Integer code;
    /**
    * 地区名称
    */
    private String name;
    /**
    * 中文地区名称
    */
    private String cname;
    /**
    * 小写英文名称
    */
    private String lowerName;

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
}
