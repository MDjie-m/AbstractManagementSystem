package com.ruoyi.ve.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 国家合格证对象 t_hgz_bu_mitt_hgzinfo
 * 
 * @author cherigo
 * @date 2024-04-22
 */
public class THgzBuMittHgzinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 合格证序号 */
    @Excel(name = "合格证序号")
    private String hgzxh;

    /** 合格证编号 */
    @Excel(name = "合格证编号")
    private String hgzbh;

    /** 状态 0待获取 1成功 2失败 */
    @Excel(name = "状态")
    private String flag;

    /** 国家合格证json */
    @Excel(name = "国家合格证json")
    private String hgzinfo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setHgzxh(String hgzxh) 
    {
        this.hgzxh = hgzxh;
    }

    public String getHgzxh() 
    {
        return hgzxh;
    }
    public void setHgzbh(String hgzbh) 
    {
        this.hgzbh = hgzbh;
    }

    public String getHgzbh() 
    {
        return hgzbh;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }
    public void setHgzinfo(String hgzinfo) 
    {
        this.hgzinfo = hgzinfo;
    }

    public String getHgzinfo() 
    {
        return hgzinfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hgzxh", getHgzxh())
            .append("hgzbh", getHgzbh())
            .append("flag", getFlag())
            .append("hgzinfo", getHgzinfo())
            .toString();
    }
}
