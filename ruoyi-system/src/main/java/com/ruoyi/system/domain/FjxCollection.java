package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收藏对象 FJX_collection
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class FjxCollection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private String id;

    /** 收藏记录的唯一标识 */
    @Excel(name = "收藏记录的唯一标识")
    private Long collectionId;

    /** 商品的唯一标识 */
    @Excel(name = "商品的唯一标识")
    private Long productId;

    /** 用户的唯一标识 */
    @Excel(name = "用户的唯一标识")
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCollectionId(Long collectionId) 
    {
        this.collectionId = collectionId;
    }

    public Long getCollectionId() 
    {
        return collectionId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("collectionId", getCollectionId())
            .append("productId", getProductId())
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("productName", getProductName())
            .toString();
    }
}
