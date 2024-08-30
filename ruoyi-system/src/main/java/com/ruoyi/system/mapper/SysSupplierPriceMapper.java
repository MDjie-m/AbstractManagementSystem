package com.ruoyi.system.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysSupplierPrice;
import com.ruoyi.system.domain.vo.SupplierProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 供应商报价Mapper接口
 * 
 * @author wzh
 * @date 2024-07-21
 */
@Mapper
public interface SysSupplierPriceMapper 
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
     * 删除供应商报价
     * 
     * @param supplierPriceId 供应商报价主键
     * @return 结果
     */
    public int deleteSysSupplierPriceBySupplierPriceId(String supplierPriceId);

    /**
     * 批量删除供应商报价
     * 
     * @param supplierPriceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSupplierPriceBySupplierPriceIds(String[] supplierPriceIds);

    public List<SysSupplierPrice> productPriceStatistics(
            @Param("supplierIds") List<String> supplierIds,
            @Param("productId") String productId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);

    List<SupplierProductVo> getSuppliersByProductName(
            @Param("productId") String productId,
            @Param("classification")Integer classification,
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate);


    Integer getQuoteRows(String productId);

    Integer getCnSupplierRows(String productId);

    Integer getSupplierRows(String productId);
}
