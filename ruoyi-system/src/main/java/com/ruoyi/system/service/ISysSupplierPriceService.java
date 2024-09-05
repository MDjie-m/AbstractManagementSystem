package com.ruoyi.system.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.SysSupplierPrice;
import com.ruoyi.system.domain.vo.AsticVo;
import com.ruoyi.system.domain.vo.QuoteVo;
import com.ruoyi.system.domain.vo.SupplierProductVo;
import com.ruoyi.system.domain.vo.SysProductVO;
import org.springframework.web.bind.annotation.RequestParam;

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
            List<String> supplierIds, String productId, String startDate, String endDate);

    /**
     * 查询可报价产品列表
     *
     * @param supplierId 供应商id productName 产品名称
     * @return 结果
     */
    public List<QuoteVo> quoteableProducts(String supplierId , String productId);

    /**
     * 根据产品名称返回对此产品报价的供应商列表
     * @param productId 产品id
     *  @return 结果
     */
    public List<SupplierProductVo> quoteSupplier(String productId, Integer classification,Date startDate, Date endDate);
}
