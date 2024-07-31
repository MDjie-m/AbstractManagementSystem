package com.ruoyi.system.domain.vo;

import java.util.List;

public class SupplierProductTotalVo {
    private Integer total;
    private List<SupplierProductVo> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SupplierProductVo> getList() {
        return list;
    }

    public void setList(List<SupplierProductVo> list) {
        this.list = list;
    }
}
