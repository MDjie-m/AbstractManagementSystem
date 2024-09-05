package com.ruoyi.system.domain.regions;

import com.ruoyi.common.core.domain.BaseEntity;

/**
* 大洲表
*/
public class SysContinents extends BaseEntity {

    /**
    * 自增id
    */
    private Integer id;
    /**
    * 英文名
    */
    private String name;
    /**
    * 中文名
    */
    private String cname;
    /**
    * 小写英文名
    */
    private String lowerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
