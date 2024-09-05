package com.ruoyi.system.domain.vo;

import java.util.List;

public class QuoteVo {
    private SysProductVO productVO;
    private Integer quoteRows;
    private Integer cnSupplierRows;
    private Integer supplierRows;
    private Integer totalRows;

    public QuoteVo() {
    }

    public QuoteVo(SysProductVO productVO, Integer quoteRows, Integer cnSupplierRows, Integer supplierRows, Integer totalRows) {
        this.productVO = productVO;
        this.quoteRows = quoteRows;
        this.cnSupplierRows = cnSupplierRows;
        this.supplierRows = supplierRows;
        this.totalRows = totalRows;
    }

    /**
     * 获取
     * @return productVO
     */
    public SysProductVO getProductVO() {
        return productVO;
    }

    /**
     * 设置
     * @param productVO
     */
    public void setProductVO(SysProductVO productVO) {
        this.productVO = productVO;
    }

    /**
     * 获取
     * @return quoteRows
     */
    public Integer getQuoteRows() {
        return quoteRows;
    }

    /**
     * 设置
     * @param quoteRows
     */
    public void setQuoteRows(Integer quoteRows) {
        this.quoteRows = quoteRows;
    }

    /**
     * 获取
     * @return cnSupplierRows
     */
    public Integer getCnSupplierRows() {
        return cnSupplierRows;
    }

    /**
     * 设置
     * @param cnSupplierRows
     */
    public void setCnSupplierRows(Integer cnSupplierRows) {
        this.cnSupplierRows = cnSupplierRows;
    }

    /**
     * 获取
     * @return supplierRows
     */
    public Integer getSupplierRows() {
        return supplierRows;
    }

    /**
     * 设置
     * @param supplierRows
     */
    public void setSupplierRows(Integer supplierRows) {
        this.supplierRows = supplierRows;
    }

    /**
     * 获取
     * @return totalRows
     */
    public Integer getTotalRows() {
        return totalRows;
    }

    /**
     * 设置
     * @param totalRows
     */
    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public String toString() {
        return "QuoteVo{productVO = " + productVO + ", quoteRows = " + quoteRows + ", cnSupplierRows = " + cnSupplierRows + ", supplierRows = " + supplierRows + ", totalRows = " + totalRows + "}";
    }
}
