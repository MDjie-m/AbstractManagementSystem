package com.tianyi.sim.domain.vo;

import java.util.List;
import java.util.Map;

public class DataQueryRes {
    private List<Map<String,Object>> dataRows;
    private int total;

    public List<Map<String, Object>> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<Map<String, Object>> dataRows) {
        this.dataRows = dataRows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
