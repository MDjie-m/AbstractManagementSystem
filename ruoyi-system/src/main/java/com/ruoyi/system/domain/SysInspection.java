package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考察情况对象 sys_inspection
 * 
 * @author tyc
 * @date 2024-07-21
 */
public class SysInspection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考察表uuid */
    private String inspectionId;

    /** 供应商uuid（外键） */
    private String supplierId;

    /** 供应商名称（对应供应商表中uuid同行的供应商名称） */
    private String supplierName;

    /** 考察员id（外键） */
    private String inspectorId;

    /** 考察员姓名 */
    private String inspectorName;

    /** 考察图片保存地址 */
    private String imageAddress;

    /** 视频保存地址 */
    private String videoAddress;

    /** 考察详情描述 */
    private String investigationDetails;

    /** 删除标志（0:未删除，1:已删除） */
    private Integer deleteFlag;

    /** 考察员评价：A、B、C、D、E */
    private String rate;

    /** 考察审核备注 */
    private String auditRemark;

    /** 考察审核结果：0-待审核，1-已通过，2-未通过 */
    private Integer auditResult;

    /** 预留字段1 */
    private String futureField1;

    /** 预留字段2 */
    private String futureField2;

    /** 预留字段3 */
    private String futureField3;

    /** 预留字段4 */
    private String futureField4;

    /** 预留字段5 */
    private String futureField5;

    public void setInspectionId(String inspectionId) 
    {
        this.inspectionId = inspectionId;
    }

    public String getInspectionId() 
    {
        return inspectionId;
    }
    public void setSupplierId(String supplierId) 
    {
        this.supplierId = supplierId;
    }

    public String getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setInspectorId(String inspectorId) 
    {
        this.inspectorId = inspectorId;
    }

    public String getInspectorId() 
    {
        return inspectorId;
    }
    public void setInspectorName(String inspectorName) 
    {
        this.inspectorName = inspectorName;
    }

    public String getInspectorName() 
    {
        return inspectorName;
    }
    public void setImageAddress(String imageAddress) 
    {
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() 
    {
        return imageAddress;
    }
    public void setVideoAddress(String videoAddress) 
    {
        this.videoAddress = videoAddress;
    }

    public String getVideoAddress() 
    {
        return videoAddress;
    }
    public void setInvestigationDetails(String investigationDetails) 
    {
        this.investigationDetails = investigationDetails;
    }

    public String getInvestigationDetails() 
    {
        return investigationDetails;
    }
    public void setDeleteFlag(Integer deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeleteFlag()
    {
        return deleteFlag;
    }
    public void setFutureField1(String futureField1) 
    {
        this.futureField1 = futureField1;
    }

    public String getFutureField1() 
    {
        return futureField1;
    }
    public void setFutureField2(String futureField2) 
    {
        this.futureField2 = futureField2;
    }

    public String getFutureField2() 
    {
        return futureField2;
    }
    public void setFutureField3(String futureField3) 
    {
        this.futureField3 = futureField3;
    }

    public String getFutureField3() 
    {
        return futureField3;
    }
    public void setFutureField4(String futureField4) 
    {
        this.futureField4 = futureField4;
    }

    public String getFutureField4() 
    {
        return futureField4;
    }
    public void setFutureField5(String futureField5) 
    {
        this.futureField5 = futureField5;
    }

    public String getFutureField5() 
    {
        return futureField5;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("inspectionId", getInspectionId())
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("inspectorId", getInspectorId())
            .append("inspectorName", getInspectorName())
            .append("imageAddress", getImageAddress())
            .append("videoAddress", getVideoAddress())
            .append("investigationDetails", getInvestigationDetails())
            .append("deleteFlag", getDeleteFlag())
            .append("rate", getRate())
            .append("auditRemark", getAuditRemark())
            .append("auditResult", getAuditResult())
            .append("futureField1", getFutureField1())
            .append("futureField2", getFutureField2())
            .append("futureField3", getFutureField3())
            .append("futureField4", getFutureField4())
            .append("futureField5", getFutureField5())
            .toString();
    }
}
