package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.MyBaseEntity;

/**
 * 库存对象 t_stock
 *
 * @author ruoyi
 * @date 2024-09-09
 */
@TableName("t_stock")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock extends MyBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品编码 */

    @TableId("stock_id")
    private Long stockId;

    /** 门店Id */
    @Excel(name = "门店Id")

    @TableField("goods_id")
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")

    @TableField("goods_name")
    private String goodsName;

    /** 门店 */
    @Excel(name = "门店")

    @TableField("store_id")
    private Long storeId;

    /** 总库存 */
    @Excel(name = "总库存")

    @TableField("total")
    private Long total;

    /** 入库总数 */
    @Excel(name = "入库总数")

    @TableField("total_in")
    private Long totalIn;

    /** 出库总数 */
    @Excel(name = "出库总数")

    @TableField("total_out")
    private Long totalOut;

    /** 最近一次盘点日志ID */
    @Excel(name = "最近一次盘点日志ID")

    @TableField("last_check_point_id")
    private Long lastCheckPointId;

    /** 创建者Id */
    @Excel(name = "创建者Id")

    @TableField("create_by_id")
    private Long createById;

    /** 更新者Id */
    @Excel(name = "更新者Id")

    @TableField("update_by_id")
    private Long updateById;

    @TableField(exist = false)
    private String goodsImg;













}
