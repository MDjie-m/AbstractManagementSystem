package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.SysProduct;

public class SysProDuctDTO extends SysProduct {
    private String names;
    private String codes;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }
}
