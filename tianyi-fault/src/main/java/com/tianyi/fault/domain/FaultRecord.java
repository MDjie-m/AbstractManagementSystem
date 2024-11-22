package com.tianyi.fault.domain;

import org.dromara.easyes.annotation.IndexField;
import org.dromara.easyes.annotation.rely.FieldType;

public class FaultRecord {
    @IndexField(value = "start_time",fieldType = FieldType.KEYWORD)
    String startTime;

    @IndexField(value = "billing_nbr",fieldType = FieldType.KEYWORD)
    String billingNbr;

    @IndexField(value = "imsi",fieldType = FieldType.KEYWORD)
    String imsi;
    @IndexField(value = "imei",fieldType = FieldType.KEYWORD)
    String imei;
    @IndexField(value = "net_type",fieldType = FieldType.KEYWORD)
    String netType;
    @IndexField(value = "bs_longitude",fieldType = FieldType.DOUBLE)
    String lng;
    @IndexField(value = "bs_latitude",fieldType = FieldType.DOUBLE)
    String lat;
    @IndexField(value = "bs_id",fieldType = FieldType.KEYWORD)
    String bsId;
    @IndexField(value = "bs_name",fieldType = FieldType.KEYWORD)
    String bsName;
    @IndexField(value = "vol_dur",fieldType = FieldType.KEYWORD)
    String volDur;
    @IndexField(value = "area_id",fieldType = FieldType.KEYWORD)
    String areaId;
    @IndexField(value = "area_name",fieldType = FieldType.KEYWORD)
    String areaName;
    @IndexField(value = "cust_id",fieldType = FieldType.KEYWORD)
    String custId;
    @IndexField(value = "cust_name",fieldType = FieldType.KEYWORD)
    String custName;
    @IndexField(value = "model_name",fieldType = FieldType.KEYWORD)
    String modelName;
    @IndexField(value = "manufacturer",fieldType = FieldType.KEYWORD)
    String manufacturer;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBillingNbr() {
        return billingNbr;
    }

    public void setBillingNbr(String billingNbr) {
        this.billingNbr = billingNbr;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBsId() {
        return bsId;
    }

    public void setBsId(String bsId) {
        this.bsId = bsId;
    }

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public String getVolDur() {
        return volDur;
    }

    public void setVolDur(String volDur) {
        this.volDur = volDur;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "FaultRecord{" +
                "startTime='" + startTime + '\'' +
                ", billingNbr='" + billingNbr + '\'' +
                ", imsi='" + imsi + '\'' +
                ", imei='" + imei + '\'' +
                ", netType='" + netType + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", bsId='" + bsId + '\'' +
                ", bsName='" + bsName + '\'' +
                ", volDur='" + volDur + '\'' +
                ", areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
