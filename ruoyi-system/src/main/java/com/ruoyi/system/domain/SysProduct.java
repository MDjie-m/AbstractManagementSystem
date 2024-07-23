package com.ruoyi.system.domain;

import com.ruoyi.common.utils.uuid.UUID;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品对象 sys_product
 * 
 * @author lyj
 * @date 2024-07-21
 */
public class SysProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品编号 */
    private String productId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierId;

    /** 一级分类 */
    @Excel(name = "一级分类")
    private String primaryCategory;

    /** 二级分类 */
    @Excel(name = "二级分类")
    private String secondaryCategory;

    /** 三级分类 */
    @Excel(name = "三级分类")
    private String tertiaryCategory;

    /** 四级分类 */
    @Excel(name = "四级分类")
    private String quaternaryCategory;

    /** 五级分类 */
    @Excel(name = "五级分类")
    private String fifthCategory;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 0-国产 1-进口 */
    @Excel(name = "0-国产 1-进口")
    private Integer domesticImportedType;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String productModel;

    /** 是否可报价：0：不可报价，1：可报价 */
    @Excel(name = "是否可报价：0：不可报价，1：可报价")
    private Integer quotationFlag;

    /** 预留字段1 */
    @Excel(name = "预留字段1")
    private String futureField1;

    /** 预留字段2 */
    @Excel(name = "预留字段2")
    private String futureField2;

    /** 预留字段3 */
    @Excel(name = "预留字段3")
    private String futureField3;
    public SysProduct() {
        this.productId = UUID.randomUUID().toString();
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductId() 
    {
        return productId;
    }
    public void setSupplierId(String supplierId) 
    {
        this.supplierId = supplierId;
    }

    public String getSupplierId() 
    {
        return supplierId;
    }
    public void setPrimaryCategory(String primaryCategory) 
    {
        this.primaryCategory = primaryCategory;
    }

    public String getPrimaryCategory() 
    {
        return primaryCategory;
    }
    public void setSecondaryCategory(String secondaryCategory) 
    {
        this.secondaryCategory = secondaryCategory;
    }

    public String getSecondaryCategory() 
    {
        return secondaryCategory;
    }
    public void setTertiaryCategory(String tertiaryCategory) 
    {
        this.tertiaryCategory = tertiaryCategory;
    }

    public String getTertiaryCategory() 
    {
        return tertiaryCategory;
    }
    public void setQuaternaryCategory(String quaternaryCategory) 
    {
        this.quaternaryCategory = quaternaryCategory;
    }

    public String getQuaternaryCategory() 
    {
        return quaternaryCategory;
    }
    public void setFifthCategory(String fifthCategory) 
    {
        this.fifthCategory = fifthCategory;
    }

    public String getFifthCategory() 
    {
        return fifthCategory;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setDomesticImportedType(Integer domesticImportedType)
    {
        this.domesticImportedType = domesticImportedType;
    }

    public Integer getDomesticImportedType()
    {
        return domesticImportedType;
    }
    public void setProductModel(String productModel) 
    {
        this.productModel = productModel;
    }

    public String getProductModel() 
    {
        return productModel;
    }
    public void setQuotationFlag(Integer quotationFlag)
    {
        this.quotationFlag = quotationFlag;
    }

    public Integer getQuotationFlag()
    {
        return quotationFlag;
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
            .append("productId", getProductId())
            .append("supplierId", getSupplierId())
            .append("primaryCategory", getPrimaryCategory())
            .append("secondaryCategory", getSecondaryCategory())
            .append("tertiaryCategory", getTertiaryCategory())
            .append("quaternaryCategory", getQuaternaryCategory())
            .append("fifthCategory", getFifthCategory())
            .append("productName", getProductName())
            .append("domesticImportedType", getDomesticImportedType())
            .append("productModel", getProductModel())
            .append("quotationFlag", getQuotationFlag())
            .append("futureField1", getFutureField1())
            .append("futureField2", getFutureField2())
            .append("futureField3", getFutureField3())
            .toString();
    }
}
