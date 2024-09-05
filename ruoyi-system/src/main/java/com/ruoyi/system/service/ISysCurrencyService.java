package com.ruoyi.system.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.system.domain.SysCurrency;

/**
 * 汇率Service接口
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
public interface ISysCurrencyService 
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
     * 批量删除汇率
     * 
     * @param currencyIds 需要删除的汇率主键集合
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyIds(Long[] currencyIds);

    /**
     * 删除汇率信息
     * 
     * @param currencyId 汇率主键
     * @return 结果
     */
    public int deleteSysCurrencyByCurrencyId(Long currencyId);

    /**
     * 获取当日汇率
     *
     *
     * @return 结果
     */
    public  Double getCurrency();
}
