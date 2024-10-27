package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物车对象 FJX_shopping_cart
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class FjxShoppingCart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private String id;

    /** 购物车的唯一标识 */
    @Excel(name = "购物车的唯一标识")
    private Long cartId;

    /** 用户的唯一标识 */
    @Excel(name = "用户的唯一标识")
    private Long userId;

    /** 商品的唯一标识 */
    @Excel(name = "商品的唯一标识")
    private Long productId;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long quantity;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCartId(Long cartId) 
    {
        this.cartId = cartId;
    }

    public Long getCartId() 
    {
        return cartId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cartId", getCartId())
            .append("userId", getUserId())
            .append("productId", getProductId())
            .append("quantity", getQuantity())
            .toString();
    }
}
