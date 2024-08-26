package com.ruoyi.system.domain.regions;

import com.ruoyi.common.core.domain.BaseEntity;

/**
* 县
*/
public class SysRegions extends BaseEntity {

    /**
    * 
    */
    private Integer id;
    /**
    * 所属城市id
    */
    private Integer cityId;
    /**
    * 县编码
    */
    private String code;
    /**
    * 
    */
    private String name;
    /**
    * 名称
    */
    private String cname;
    /**
    * 
    */
    private String lowerName;
    /**
    * 地区代码
    */
    private String codeFull;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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
}
