package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.SysProduct;

import java.util.List;

public class SysProDuctDTO extends SysProduct {
    /** 新增产品使用 */
    private String names;
    private String codes;

    /** 切换报价清单与询价清单使用 */
    private List<String> productIds;
    private Integer status;

    /** 标识是否查找供应商名称 */
    private boolean flag;

    /** 查询使用 */
    /** 最小库存 */
    private Integer startInventory;
    /** 最大库存 */
    private Integer endInventory;
    //预计后面如果筛选条件可以多选的话就得自己添加list参数
    //还有起批应该也是范围查询
    /** 最小起批数量 */
    private Integer minStartBatch;
    /** 最大起批数量 */
    private Integer maxStartBatch;

    //多选：产品产地、产品分类（5个）、保质期
    //还没加上的多选条件有，保质期
    /** 产品产地列表，多选然后查询 */
    private List<String> origins;

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

    //范围查询：库存和起批，单价范围、生产日期
    //todo 目前只弄了库存，起批和单价范围、生成日期范围都没弄，但是库存的范围查询暂时存疑，生产日期可以为null，这里要注意一下。


    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMinStartBatch() {
        return minStartBatch;
    }

    public void setMinStartBatch(Integer minStartBatch) {
        this.minStartBatch = minStartBatch;
    }

    public Integer getMaxStartBatch() {
        return maxStartBatch;
    }

    public void setMaxStartBatch(Integer maxStartBatch) {
        this.maxStartBatch = maxStartBatch;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

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

    public Integer getStartInventory() {
        return startInventory;
    }

    public void setStartInventory(Integer startInventory) {
        this.startInventory = startInventory;
    }

    public Integer getEndInventory() {
        return endInventory;
    }

    public void setEndInventory(Integer endInventory) {
        this.endInventory = endInventory;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

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
