package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;

/**
 * 商品分类对象 t_goods_category
 * 
 * @author ruoyi
 * @date 2024-09-08
 */
@TableName("t_goods_category")
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsCategory extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    @TableId("goods_category_id")
    private Long goodsCategoryId;

    /** 商品分类名称 */
    @Excel(name = "商品分类名称")

    @TableField("goods_category_name")
    private String goodsCategoryName;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 排序 */
    @Excel(name = "排序")

    @TableField("sort")
    private Long sort;







}
