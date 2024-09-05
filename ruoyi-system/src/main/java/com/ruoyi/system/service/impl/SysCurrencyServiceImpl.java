package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCurrencyMapper;
import com.ruoyi.system.domain.SysCurrency;
import com.ruoyi.system.service.ISysCurrencyService;

/**
 * 汇率Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-08-28
 */
@Service
public class SysCurrencyServiceImpl implements ISysCurrencyService 
{
    @Autowired
    private SysCurrencyMapper sysCurrencyMapper;

    /**
     * 查询汇率
     * 
     * @param currencyId 汇率主键
     * @return 汇率
     */
    @Override
    public SysCurrency selectSysCurrencyByCurrencyId(Long currencyId)
    {
        return sysCurrencyMapper.selectSysCurrencyByCurrencyId(currencyId);
    }

    /**
     * 查询汇率列表
     * 
     * @param sysCurrency 汇率
     * @return 汇率
     */
    @Override
    public List<SysCurrency> selectSysCurrencyList(SysCurrency sysCurrency)
    {
        return sysCurrencyMapper.selectSysCurrencyList(sysCurrency);
    }

    /**
     * 新增汇率
     * 
     * @param sysCurrency 汇率
     * @return 结果
     */
    @Override
    public int insertSysCurrency(SysCurrency sysCurrency)
    {
        return sysCurrencyMapper.insertSysCurrency(sysCurrency);
    }

    /**
     * 修改汇率
     * 
     * @param sysCurrency 汇率
     * @return 结果
     */
    @Override
    public int updateSysCurrency(SysCurrency sysCurrency)
    {
        return sysCurrencyMapper.updateSysCurrency(sysCurrency);
    }

    /**
     * 批量删除汇率
     * 
     * @param currencyIds 需要删除的汇率主键
     * @return 结果
     */
    @Override
    public int deleteSysCurrencyByCurrencyIds(Long[] currencyIds)
    {
        return sysCurrencyMapper.deleteSysCurrencyByCurrencyIds(currencyIds);
    }

    /**
     * 删除汇率信息
     * 
     * @param currencyId 汇率主键
     * @return 结果
     */
    @Override
    public int deleteSysCurrencyByCurrencyId(Long currencyId)
    {
        return sysCurrencyMapper.deleteSysCurrencyByCurrencyId(currencyId);
    }

    /**
     * 获取当日汇率
     *
     *
     * @return 结果
     */
    @Override
    public  Double getCurrency(){
        return sysCurrencyMapper.getCurrency();
    }

}
