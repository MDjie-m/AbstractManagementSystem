package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 产品分类对象 sys_product_type
 * 
 * @author xgg
 * @date 2024-07-22
 */
public class SysProductType extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String productCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** 父级编码 */
    @Excel(name = "父级编码")
    private String parentCode;

    /** 产品标签id（以，分割） */
    @Excel(name = "产品标签id（以，分割）")
    private String tagId;

    /** 产品标签名（以，分割） */
    @Excel(name = "产品标签名（以，分割）")
    private String tagName;

    /** 是否显示：0：显示，1：不显示
     * （没有具体产品名称即没有五级编码和名称） */
    @Excel(name = "是否显示", readConverterExp = "0：显示，1：不显示 ")
    private Integer showFlag;

    /** 是否删除：0-未删除，1-已删除
     *  */
    @Excel(name = "是否删除", readConverterExp = "0-未删除，1-已删除 ")
    private Integer delFlag;

    /** 统计标记字段以","间隔 */
    @Excel(name = "统计标记字段以\",\"间隔")
    private String statistical_marker;

    /** 0-国产,1-进口,2-国产进口 */
    @Excel(name = "是否显示", readConverterExp = "0-国产,1-进口,2-国产进口 ")
    private Integer classification;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    public SysProductType() {
    }

    public SysProductType( String productCode, String productName, String parentCode, String tagId, String tagName, Integer showFlag, Integer delFlag, String statistical_marker, Integer classification, String remark) {
        this.productCode = productCode;
        this.productName = productName;
        this.parentCode = parentCode;
        this.tagId = tagId;
        this.tagName = tagName;
        this.showFlag = showFlag;
        this.delFlag = delFlag;
        this.statistical_marker = statistical_marker;
        this.classification = classification;
        this.remark = remark;
    }

    /**
     * 获取
     * @return productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取
     * @return parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 设置
     * @param parentCode
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获取
     * @return tagId
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * 设置
     * @param tagId
     */
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    /**
     * 获取
     * @return tagName
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 获取
     * @return showFlag
     */
    public Integer getShowFlag() {
        return showFlag;
    }

    /**
     * 设置
     * @param showFlag
     */
    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    /**
     * 获取
     * @return delFlag
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 设置
     * @param delFlag
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取
     * @return statistical_marker
     */
    public String getStatistical_marker() {
        return statistical_marker;
    }

    /**
     * 设置
     * @param statistical_marker
     */
    public void setStatistical_marker(String statistical_marker) {
        this.statistical_marker = statistical_marker;
    }

    /**
     * 获取
     * @return classification
     */
    public Integer getClassification() {
        return classification;
    }

    /**
     * 设置
     * @param classification
     */
    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    /**
     * 获取
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return "SysProductType{productCode = " + productCode + ", productName = " + productName + ", parentCode = " + parentCode + ", tagId = " + tagId + ", tagName = " + tagName + ", showFlag = " + showFlag + ", delFlag = " + delFlag + ", statistical_marker = " + statistical_marker + ", classification = " + classification + ", remark = " + remark + "}";
    }
}
