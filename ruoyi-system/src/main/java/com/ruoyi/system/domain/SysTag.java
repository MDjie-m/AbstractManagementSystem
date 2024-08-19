package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品标签对象 sys_tag
 * 
 * @author ruoyi
 * @date 2024-08-06
 */
public class SysTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签id */
    private Long id;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String name;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String futureField1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String futureField2;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private String futureField3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("remark", getRemark())
            .append("futureField1", getFutureField1())
            .append("futureField2", getFutureField2())
            .append("futureField3", getFutureField3())
            .toString();
    }
}
