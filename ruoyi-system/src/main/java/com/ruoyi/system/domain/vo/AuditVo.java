package com.ruoyi.system.domain.vo;

import java.util.List;

public class AuditVo {
    private List<String> supplierList;
    private Integer auditStatus;
    private String remark ;
    private String rate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public List<String> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<String> supplierList) {
        this.supplierList = supplierList;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
