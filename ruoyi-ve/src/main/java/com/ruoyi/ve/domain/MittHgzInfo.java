package com.ruoyi.ve.domain;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 国家合格证信息查询表 t_hgz_bu_mitt_hgzinfo
 * 
 * @author ruoyi
 */
public class MittHgzInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID", cellType = ColumnType.NUMERIC)
    private Long id;

    /** 合格证序号 */
    @Excel(name = "合格证序号")
    private String hgzxh;

    /** 合格证编号 */
    @Excel(name = "合格证编号")
    private String hgzbh;

    /** 状态 */
    @Excel(name = "状态")
    private String flag;

    /** 合格证json信息 */
    @Excel(name = "国家合格证json ")
    private String hgzinfo;

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setHgzxh(String hgzxh)
    {
        this.hgzxh = hgzxh;
    }

    public void setHgzbh(String hgzbh)
    {
        this.hgzbh = hgzbh;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public void setHgzinfo(String hgzinfo)
    {
        this.hgzinfo = hgzinfo;
    }

    public Long getId()
    {
        return id;
    }

    public String getHgzxh()
    {
        return hgzxh;
    }

    public String getHgzbh()
    {
        return hgzbh;
    }

    public String getFlag()
    {
        return flag;
    }

    public String getHgzinfo()
    {
        return hgzinfo;
    }

    public MittHgzInfo()
    {
        super();
    }

    public MittHgzInfo(Long id, String hgzxh, String hgzbh, String flag, String hgzinfo)
    {
        super();
        this.id = id;
        this.hgzxh = hgzxh;
        this.hgzbh = hgzbh;
        this.flag = flag;
        this.hgzinfo = hgzinfo;
    }

    public MittHgzInfo(String hgzxh, String hgzbh, String flag, String hgzinfo)
    {
        super();
        this.hgzxh = hgzxh;
        this.hgzbh = hgzbh;
        this.flag = flag;
        this.hgzinfo = hgzinfo;
    }



}