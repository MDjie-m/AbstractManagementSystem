package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品对象 FJX_product
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class FjxProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private String id;

    /** 商品的唯一标识 */
    @Excel(name = "商品的唯一标识")
    private Long productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品图片的URL */
    @Excel(name = "商品图片的URL")
    private String imageUrl;

    /** 商品库存量 */
    @Excel(name = "商品库存量")
    private Long stockQuantity;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 商品描述 */
    @Excel(name = "商品描述")
    private String description;

    /** 商品总数量 */
    @Excel(name = "商品总数量")
    private Long totalQuantity;

    /** 商品更新的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "商品更新的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /** 商品创建的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "商品创建的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 商品上架的时间 */
    @Excel(name = "商品上架的时间")
    private String shelfTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }
    public void setStockQuantity(Long stockQuantity) 
    {
        this.stockQuantity = stockQuantity;
    }

    public Long getStockQuantity() 
    {
        return stockQuantity;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setTotalQuantity(Long totalQuantity) 
    {
        this.totalQuantity = totalQuantity;
    }

    public Long getTotalQuantity() 
    {
        return totalQuantity;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setShelfTime(String shelfTime) 
    {
        this.shelfTime = shelfTime;
    }

    public String getShelfTime() 
    {
        return shelfTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("name", getName())
            .append("imageUrl", getImageUrl())
            .append("stockQuantity", getStockQuantity())
            .append("price", getPrice())
            .append("description", getDescription())
            .append("totalQuantity", getTotalQuantity())
            .append("updatedTime", getUpdatedTime())
            .append("createdTime", getCreatedTime())
            .append("shelfTime", getShelfTime())
            .toString();
    }
}
