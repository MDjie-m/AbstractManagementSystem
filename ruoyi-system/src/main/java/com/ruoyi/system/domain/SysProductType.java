package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 产品分类对象 sys_product_type
 * 
 * @author xgg
 * @date 2024-07-22
 */
public class SysProductType extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String productCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 父级编码 */
    @Excel(name = "父级编码")
    private String parentCode;

    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setParentCode(String parentCode) 
    {
        this.parentCode = parentCode;
    }

    public String getParentCode() 
    {
        return parentCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("parentCode", getParentCode())
            .append("remark", getRemark())
            .toString();
    }
}
