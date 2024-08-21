package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.SysProductType;

/**
 * 产品分类Service接口
 * 
 * @author xgg
 * @date 2024-07-22
 */
public interface ISysProductTypeService 
{
    /**
     * 查询产品分类
     * 
     * @param productCode 产品分类主键
     * @return 产品分类
     */
    public SysProductType selectSysProductTypeByProductCode(String productCode);

    /**
     * 查询产品分类列表
     * 
     * @param sysProductType 产品分类
     * @return 产品分类集合
     */
    public List<SysProductType> selectSysProductTypeList(SysProductType sysProductType);

    /**
     * 新增产品分类
     * 
     * @param sysProductType 产品分类
     * @return 结果
     */
    public int insertSysProductType(SysProductType sysProductType);

    /**
     * 修改产品分类
     * 
     * @param sysProductType 产品分类
     * @return 结果
     */
    public int updateSysProductType(SysProductType sysProductType);

    /**
     * 批量删除产品分类
     * 
     * @param productCodes 需要删除的产品分类主键集合
     * @return 结果
     */
    public int deleteSysProductTypeByProductCodes(String[] productCodes);

    /**
     * 删除产品分类信息
     * 
     * @param productCode 产品分类主键
     * @return 结果
     */
    public int deleteSysProductTypeByProductCode(String productCode);

    /**
     * 按照层级组装成一个TreeList
     * @return
     */
    List<Map<String,Object>> selectSysProductTypeTreeList(Integer depth ,Integer classification);

    List<SysProductType> selectType(String[] newCodes);

    SysProductType selectTag(String code);
}
