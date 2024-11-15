package com.tianyi.terminal.domain;

import com.tianyi.common.annotation.Excel;
import com.tianyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;


@ApiModel(value = "QueryBSTerminalInfo对象")
public class QueryBSTerminalInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 基站id */
    @Excel(name = "基站id")
    private String bsId;

    /** 基站名称*/
    @Excel(name = "基站名称")
    private String bsName;

    @Excel(name = "经度")
    private String lng;

    /** 基站id */
    @Excel(name = "维度")
    private String lat;

    /** 终端数量 */
    @Excel(name = "终端数量")
    private String terminalCnt;

    @Excel(name = "流量")
    private String vol;
    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getTerminalCnt() {
        return terminalCnt;
    }

    public void setTerminalCnt(String terminalCnt) {
        this.terminalCnt = terminalCnt;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }
}
