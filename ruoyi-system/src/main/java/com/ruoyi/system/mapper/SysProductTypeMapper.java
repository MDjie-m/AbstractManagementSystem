package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysProductType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 产品分类Mapper接口
 * 
 * @author xgg
 * @date 2024-07-22
 */
//@Mapper
public interface SysProductTypeMapper 
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
     * 删除产品分类
     * 
     * @param productCode 产品分类主键
     * @return 结果
     */
    public int deleteSysProductTypeByProductCode(String productCode);

    /**
     * 批量删除产品分类
     * 
     * @param productCodes 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysProductTypeByProductCodes(String[] productCodes);

    List<SysProductType> selectChildren(@Param("parentCode") String parentCode);

    List<SysProductType> selectType(@Param("productCodes") String[] newCodes);

    SysProductType selectTag(@Param("code") String code);
}
