package com.tianyi.terminal.domain;

import com.tianyi.common.annotation.Excel;
import com.tianyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;


@ApiModel(value = "QueryBSStatInfo对象")
public class QueryImeiInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;


    @Excel(name = "imei")
    private String imei;

    @Excel(name = "imsi")
    private String imsi;

    @Excel(name = "接入号")
    private String billingNbr;

    @Excel(name = "销售省")
    private String provName;

    @Excel(name = "销售单位")
    private String areaName;

    @Excel(name = "客户名称")
    private String custName;

    @Excel(name = "模组厂家")
    private String manufacturer;

    @Excel(name = "模组型号")
    private String modelName;

    @Excel(name = "流量")
    private String vol;

    @Excel(name = "上网时长")
    private String volDur;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getBillingNbr() {
        return billingNbr;
    }

    public void setBillingNbr(String billingNbr) {
        this.billingNbr = billingNbr;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getVolDur() {
        return volDur;
    }

    public void setVolDur(String volDur) {
        this.volDur = volDur;
    }
}
