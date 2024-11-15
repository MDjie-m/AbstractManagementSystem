package com.tianyi.terminal.domain;

import com.tianyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import com.tianyi.common.annotation.Excel;


@ApiModel(value = "QueryBSStatInfo对象")
public class QueryBSStatInfo extends BaseEntity implements Comparable<QueryBSStatInfo>{
    private static final long serialVersionUID = 1L;

    /** 基站id */
    @Excel(name = "基站id")
    private String bsId;
    /** 基站id */
    @Excel(name = "经度")
    private Double lng;

    /** 基站id */
    @Excel(name = "维度")
    private Double lat;

    /** 终端数量 */
    @Excel(name = "终端数量")
    private int terminalCnt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBsId() {
        return bsId;
    }

    public void setBsId(String bsId) {
        this.bsId = bsId;
    }

    public int getTerminalCnt() {
        return terminalCnt;
    }

    public void setTerminalCnt(int terminalCnt) {
        this.terminalCnt = terminalCnt;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public int compareTo(QueryBSStatInfo o) {
        return o.getTerminalCnt()-terminalCnt;
    }
}
