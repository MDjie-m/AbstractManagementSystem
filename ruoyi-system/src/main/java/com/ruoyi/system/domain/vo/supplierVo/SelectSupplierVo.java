package com.ruoyi.system.domain.vo.supplierVo;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.system.domain.SysSupplier;

import java.util.Date;

/**
 * 供应商筛选Vo
 */
public class SelectSupplierVo extends SysSupplier {

    /**
     * 起止入驻时间
     */
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date startEntryDate;

    /**
     * 止入驻时间
     */
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date endEntryDate;

    /**
     * 内部一级分类
     */
    private String cnPrimaryCategory;

    /**
     * 内部二级分类
     */
    private String cnSecondaryCategory;

    /**
     * 内部三级分类
     */
    private String cnTertiaryCategory;

    /**
     * 内部四级分类
     */
    private String cnQuaternaryCategory;

    /**
     * 内部五级分类
     */
    private String cnFifthCategory;

    public Date getStartEntryDate() {
        return startEntryDate;
    }

    public void setStartEntryDate(Date startEntryDate) {
        this.startEntryDate = startEntryDate;
    }

    public Date getEndEntryDate() {
        return endEntryDate;
    }

    public void setEndEntryDate(Date endEntryDate) {
        this.endEntryDate = endEntryDate;
    }

    public String getCnPrimaryCategory() {
        return cnPrimaryCategory;
    }

    public void setCnPrimaryCategory(String cnPrimaryCategory) {
        this.cnPrimaryCategory = cnPrimaryCategory;
    }

    public String getCnSecondaryCategory() {
        return cnSecondaryCategory;
    }

    public void setCnSecondaryCategory(String cnSecondaryCategory) {
        this.cnSecondaryCategory = cnSecondaryCategory;
    }

    public String getCnTertiaryCategory() {
        return cnTertiaryCategory;
    }

    public void setCnTertiaryCategory(String cnTertiaryCategory) {
        this.cnTertiaryCategory = cnTertiaryCategory;
    }

    public String getCnQuaternaryCategory() {
        return cnQuaternaryCategory;
    }

    public void setCnQuaternaryCategory(String cnQuaternaryCategory) {
        this.cnQuaternaryCategory = cnQuaternaryCategory;
    }

    public String getCnFifthCategory() {
        return cnFifthCategory;
    }

    public void setCnFifthCategory(String cnFifthCategory) {
        this.cnFifthCategory = cnFifthCategory;
    }
}
