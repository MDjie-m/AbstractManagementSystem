package com.renxin.gauge.domain;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 心理测评对象 psy_gauge
 * 
 * @author renxin
 * @date 2022-08-30
 */
@Data
public class PsyGauge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 测评标题 */
    @Excel(name = "测评标题")
    private String title;

    /** 子标题 */
    @Excel(name = "子标题")
    private String subtitle;

    /** 头部图片 */
    @Excel(name = "头部图片")
    private String headPicture;

    /** 测评描述 */
    @Excel(name = "测评描述")
    private String introduce;

    /** 测评分类 */
    @Excel(name = "测评分类")
    private Integer gaugeClass;

    /** 测评题数 */
    @Excel(name = "测评题数")
    private Integer gaugeNum;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 价格 */
    @Excel(name = "比率")
    private BigDecimal gaugeRatio;

    /** 测评次数 */
    @Excel(name = "测评次数")
    private Integer num;

    /** 总分 */
    @Excel(name = "总分")
    private Integer gaugeScore;

    /** 是否展示错题 */
    @Excel(name = "错题")
    private String wrong;

    /** 是否展示雷达图 */
    @Excel(name = "雷达图")
    private String radar;

    /** 测评类型 */
    @Excel(name = "测评类型")
    private Integer type;

    @Excel(name = "测评简介")
    private String gaugeDes;

    @Excel(name = "列表展示图片")
    private String listShowPicture;

    @TableLogic
    private Integer delFlag;
    private Long userId;
    
    private List<Long> idList;

}
