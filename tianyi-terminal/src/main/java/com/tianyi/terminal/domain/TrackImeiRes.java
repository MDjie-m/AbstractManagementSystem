package com.tianyi.terminal.domain;

import java.util.List;

public class TrackImeiRes {
    private List<Record> dataRows;
    private Long total;


    public List<Record> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<Record> dataRows) {
        this.dataRows = dataRows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
