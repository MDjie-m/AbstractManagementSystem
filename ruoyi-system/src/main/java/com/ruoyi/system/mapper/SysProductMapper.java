package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.domain.vo.SysProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;

/**
 * 产品Mapper接口
 * 
 * @author xgg
 * @date 2024-07-23
 */
public interface SysProductMapper 
{
    /**
     * 查询产品
     * 
     * @param sysProDuctDTO 产品dto
     * @return 产品
     */
    public SysProductVO selectSysProductByProductId(SysProDuctDTO sysProDuctDTO);

    /**
     * 查询产品列表
     * 
     * @param sysProDuctDTO
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
     * 删除产品
     * 
     * @param productId 产品主键
     * @return 结果
     */
    public int deleteSysProductByProductId(String productId);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysProductByProductIds(@Param("productIds") List<String> productIds);

    public List<String> selectProductNamesByParam(
            @Param("supplierId")String supplierId , @Param("productName")String productName);

    /**
     * 修改产品状态为1-已报价
     *
     * @param productId
     * @return
     */
    public int updateSysProductStatus(String productId);

    /**
     * 修改产品状态前端切换状态
     *
     * @param productId
     * @return
     */
    public int updateStatus(@Param("productId")String productId,@Param("status")String status);
}
