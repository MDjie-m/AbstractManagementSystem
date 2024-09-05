package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.SysProductStandard;

import java.util.List;

public class SysProductStandardDTO extends SysProductStandard {
    /** 产品内部一级分类，可多选 */
    private List<String> cnPrimaryCategorys;
    /** 产品内部二级分类，可多选 */
    private List<String> cnSecondaryCategorys;
    /** 产品内部三级分类，可多选 */
    private List<String> cnTertiaryCategorys;
    /** 产品内部四级分类，可多选 */
    private List<String> cnQuaternaryCategorys;
    /** 产品内部五级分类，可多选 */
    private List<String> cnFifthCategorys;

    public List<String> getCnPrimaryCategorys() {
        return cnPrimaryCategorys;
    }

    public void setCnPrimaryCategorys(List<String> cnPrimaryCategorys) {
        this.cnPrimaryCategorys = cnPrimaryCategorys;
    }

    public List<String> getCnSecondaryCategorys() {
        return cnSecondaryCategorys;
    }

    public void setCnSecondaryCategorys(List<String> cnSecondaryCategorys) {
        this.cnSecondaryCategorys = cnSecondaryCategorys;
    }

    public List<String> getCnTertiaryCategorys() {
        return cnTertiaryCategorys;
    }

    public void setCnTertiaryCategorys(List<String> cnTertiaryCategorys) {
        this.cnTertiaryCategorys = cnTertiaryCategorys;
    }

    public List<String> getCnQuaternaryCategorys() {
        return cnQuaternaryCategorys;
    }

    public void setCnQuaternaryCategorys(List<String> cnQuaternaryCategorys) {
        this.cnQuaternaryCategorys = cnQuaternaryCategorys;
    }

    public List<String> getCnFifthCategorys() {
        return cnFifthCategorys;
    }

    public void setCnFifthCategorys(List<String> cnFifthCategorys) {
        this.cnFifthCategorys = cnFifthCategorys;
    }
}
