package com.ruoyi.system.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author:
 * @description:
 * @create: 2024-11-03 21:45
 **/
@Data
public class FjxInFjxShopCartDto {

    /** 用户id */
    private Long userId;

    /**  商品id列表 */
    private List<Long>  productId;

    /**  商品数量列表 */
    private List<Long> quantity;

}
