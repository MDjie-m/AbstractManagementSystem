package com.ruoyi.billiard.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.*;
import com.ruoyi.common.annotation.Excel;

/**
 * 商品对象 t_goods
 *
 * @author ruoyi
 * @date 2024-09-08
 */
@TableName("t_goods")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品编码 */

    @TableId("goods_id")
    private Long goodsId;

    /** 门店Id */
    @Excel(name = "门店Id")

    @TableField("store_id")
    private Long storeId;

    /** 商品名称 */
    @Excel(name = "商品名称")

    @TableField("goods_name")
    private String goodsName;

    /** 商品图片 */
    @Excel(name = "商品图片")

    @TableField("goods_img")
    private String goodsImg;

    /** 排序 */
    @Excel(name = "排序")

    @TableField("sort")
    private Integer sort;

    /** 商品条码 */
    @Excel(name = "商品条码")

    @TableField("barcode")
    private String barcode;

    /** 商品分类 */
    @Excel(name = "商品分类")

    @TableField("category_id")
    private Long categoryId;


    @TableField(exist = false)
    private String  categoryName;


    /** 是否上架销售 */
    @Excel(name = "是否上架销售")

    @TableField("sell")
    private Boolean sell;


    @TableField("成本")
    private BigDecimal cost;
    /** 价格 */
    @Excel(name = "价格")

    @TableField("price")
    private BigDecimal price;

    /**
     * 禁止折扣 默认是false，
     */

    @TableField("discount_disable")
    private Boolean discountDisable;


    /**
     * 库存
     */
    @TableField(exist = false)
    private Long  total;



}
