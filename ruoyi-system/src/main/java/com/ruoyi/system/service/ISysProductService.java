package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.domain.vo.SysProductVO;

/**
 * 产品Service接口
 * 
 * @author xgg
 * @date 2024-07-23
 */
public interface ISysProductService 
{
    /**
     * 查询产品
     * 
     * @param sysProDuctDTO 产品
     * @return 产品
     */
    public SysProductVO selectSysProductByProductId(SysProDuctDTO sysProDuctDTO);

    /**
     * 查询产品列表
     * 
     * @param sysProDuctDTO 产品dto对象
     * @return 产品集合
     */
    public List<SysProductVO> selectSysProductList(SysProDuctDTO sysProDuctDTO);

    /**
     * 新增产品
     * 
     * @param sysProduct 产品
     * @return 结果
     */
    public int insertSysProduct(SysProduct sysProduct);

    /**
     * 修改产品
     * 
     * @param sysProduct 产品
     * @return 结果
     */
    public int updateSysProduct(SysProduct sysProduct);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键集合
     * @return 结果
     */
    public int deleteSysProductByProductIds(List<String> productIds);

    /**
     * 删除产品信息
     * 
     * @param productId 产品主键
     * @return 结果
     */
    public int deleteSysProductByProductId(String productId);

    /**
     * 导入产品
     * @param productList 产品数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return
     */
    public String importProduct(List<SysProduct> productList, Boolean isUpdateSupport);
    /**
     * 批量删除产品
     *
     * @param productId 产品id
     * @return 结果
     */
    public int updateStatus(String productId,String status);

    /**
     * 切换报价清单状态(不常用、常用)：报价清单中的产品即为常用的报价产品
     * @param productId
     * @param status
     * @return
     */
    int updateQuoteListStatus(String productId, String status);

    /**
     * 切换询价清单状态(不常用、常用)：询价清单中的产品即为常用来询价的产品
     * @param productId
     * @param status
     * @return
     */
    int updateInquiryListStatus(String productId, String status);
}
