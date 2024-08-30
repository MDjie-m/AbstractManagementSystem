package com.ruoyi.system.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.system.domain.SysCurrency;
import org.apache.ibatis.annotations.Mapper;

/**
 * 汇率Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
@Mapper
public interface SysCurrencyMapper 
{
    /**
     * 查询汇率
     * 
     * @param currencyId 汇率主键
     * @return 汇率
     */
    public SysCurrency selectSysCurrencyByCurrencyId(Long currencyId);

    /**
     * 查询汇率列表
     * 
     * @param sysCurrency 汇率
     * @return 汇率集合
     */
    public List<SysCurrency> selectSysCurrencyList(SysCurrency sysCurrency);

    /**
     * 新增汇率
     * 
     * @param sysCurrency 汇率
     * @return 结果
     */
    public int insertSysCurrency(SysCurrency sysCurrency);

    /**
     * 修改汇率
     * 
     * @param sysCurrency 汇率
     * @return 结果
     */
    public int updateSysCurrency(SysCurrency sysCurrency);

    /**
     * 删除汇率
     * 
     * @param currencyId 汇率主键
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyId(Long currencyId);

    /**
     * 批量删除汇率
     * 
     * @param currencyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyIds(Long[] currencyIds);

    public Double getCurrency();
}
