package com.ruoyi.system.domain.dto;

import com.alipay.api.domain.Product;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.FjxProduct;
import com.ruoyi.system.domain.FjxShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * @author:
 * @description:
 * @create: 2024-11-02 16:10
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FjxShopCartDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private FjxShoppingCart shoppingCart;

    private FjxProduct product;



    //    @Excel(name = "商品图片的URL")
//    private String imageUrl;
//
//    @Excel(name = "商品价格")
//    private BigDecimal price;



//    @Override
//    public String toString() {
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//                .append("id", getId())
//                .append("cartId", getCartId())
//                .append("userId", getUserId())
//                .append("productId", getProductId())
//                .append("quantity", getQuantity())
//                .toString();
//    }
}
