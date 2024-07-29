package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysSupplierPrice;
import com.ruoyi.system.domain.vo.AsticVo;

/**
 * 供应商报价Service接口
 * 
 * @author wzh
 * @date 2024-07-21
 */
public interface ISysSupplierPriceService 
{
    /**
     * 查询供应商报价
     * 
     * @param supplierPriceId 供应商报价主键
     * @return 供应商报价
     */
    public SysSupplierPrice selectSysSupplierPriceBySupplierPriceId(String supplierPriceId);

    /**
     * 查询供应商报价列表
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 供应商报价集合
     */
    public List<SysSupplierPrice> selectSysSupplierPriceList(SysSupplierPrice sysSupplierPrice);

    /**
     * 新增供应商报价
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 结果
     */
    public int insertSysSupplierPrice(SysSupplierPrice sysSupplierPrice);

    /**
     * 修改供应商报价
     * 
     * @param sysSupplierPrice 供应商报价
     * @return 结果
     */
    public int updateSysSupplierPrice(SysSupplierPrice sysSupplierPrice);

    /**
     * 批量删除供应商报价
     * 
     * @param supplierPriceIds 需要删除的供应商报价主键集合
     * @return 结果
     */
    public int deleteSysSupplierPriceBySupplierPriceIds(String[] supplierPriceIds);

    /**
     * 删除供应商报价信息
     * 
     * @param supplierPriceId 供应商报价主键
     * @return 结果
     */
    public int deleteSysSupplierPriceBySupplierPriceId(String supplierPriceId);

    public List<AsticVo> productPriceStatistics(
            List<String> supplierNames, String productName, String startDate, String endDate);

}
