package com.ruoyi.system.domain.regions;

import com.ruoyi.common.core.domain.BaseEntity;


/**
* 区（市）
*/
public class SysCities extends BaseEntity {

    /**
    * 
    */
    private Integer id;
    /**
    * 所属州省id
    */
    private Integer stateId;
    /**
    * 编码
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
    * 地区代码
    */
    private String codeFull;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
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
