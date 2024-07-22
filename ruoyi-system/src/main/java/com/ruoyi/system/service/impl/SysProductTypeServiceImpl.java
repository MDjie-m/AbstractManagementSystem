package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProductTypeMapper;
import com.ruoyi.system.domain.SysProductType;
import com.ruoyi.system.service.ISysProductTypeService;

/**
 * 产品分类Service业务层处理
 * 
 * @author xgg
 * @date 2024-07-22
 */
@Service
public class SysProductTypeServiceImpl implements ISysProductTypeService 
{
    @Autowired
    private SysProductTypeMapper sysProductTypeMapper;

    /**
     * 查询产品分类
     * 
     * @param productCode 产品分类主键
     * @return 产品分类
     */
    @Override
    public SysProductType selectSysProductTypeByProductCode(String productCode)
    {
        return sysProductTypeMapper.selectSysProductTypeByProductCode(productCode);
    }

    /**
     * 查询产品分类列表
     * 
     * @param sysProductType 产品分类
     * @return 产品分类
     */
    @Override
    public List<SysProductType> selectSysProductTypeList(SysProductType sysProductType)
    {
        return sysProductTypeMapper.selectSysProductTypeList(sysProductType);
    }

    /**
     * 新增产品分类
     * 
     * @param sysProductType 产品分类
     * @return 结果
     */
    @Override
    public int insertSysProductType(SysProductType sysProductType)
    {
        return sysProductTypeMapper.insertSysProductType(sysProductType);
    }

    /**
     * 修改产品分类
     * 
     * @param sysProductType 产品分类
     * @return 结果
     */
    @Override
    public int updateSysProductType(SysProductType sysProductType)
    {
        return sysProductTypeMapper.updateSysProductType(sysProductType);
    }

    /**
     * 批量删除产品分类
     * 
     * @param productCodes 需要删除的产品分类主键
     * @return 结果
     */
    @Override
    public int deleteSysProductTypeByProductCodes(String[] productCodes)
    {
        return sysProductTypeMapper.deleteSysProductTypeByProductCodes(productCodes);
    }

    /**
     * 删除产品分类信息
     * 
     * @param productCode 产品分类主键
     * @return 结果
     */
    @Override
    public int deleteSysProductTypeByProductCode(String productCode)
    {
        return sysProductTypeMapper.deleteSysProductTypeByProductCode(productCode);
    }
}
