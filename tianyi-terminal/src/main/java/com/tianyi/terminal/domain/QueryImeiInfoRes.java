package com.tianyi.terminal.domain;

import java.util.List;

public class QueryImeiInfoRes {
    private List<RecordImei> dataRows;
    private int total;


    public List<RecordImei> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<RecordImei> dataRows) {
        this.dataRows = dataRows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
