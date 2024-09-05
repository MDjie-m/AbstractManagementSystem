package com.ruoyi.billiard.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 门店对象 t_store
 *
 * @author ruoyi
 * @date 2024-09-06
 */
@TableName("t_store")
@Data
@EqualsAndHashCode(callSuper = true)
public class Store extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门店id */

    @TableId("store_id")
    private Long storeId;

    /** 门店名称 */
    @Excel(name = "门店名称")

    @TableField("store_name")
    private String storeName;

    /** 门店地址 */
    @Excel(name = "门店地址")

    @TableField("store_address")
    private String storeAddress;

    /** 门店图片 */
    @Excel(name = "门店图片")

    @TableField("store_img")
    private String storeImg;

    /** 门店状态（0正常 1闭店） */
    @Excel(name = "门店状态", readConverterExp = "0=正常,1=闭店")

    @TableField("status")
    private Integer status;

    /** 删除标志（0代表存在 2代表删除） */

    @TableField("del_flag")
    private String delFlag;


}
