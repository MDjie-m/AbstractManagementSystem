package com.baoli.sysmanage.mapper;

import java.util.List;
import com.baoli.sysmanage.domain.BlSysBank;

/**
 * 银行管理Mapper接口
 * 
 * @author niujs
 * @date 2024-04-08
 */
public interface BlSysBankMapper 
{
    /**
     * 查询银行管理
     * 
     * @param id 银行管理主键
     * @return 银行管理
     */
    public BlSysBank selectBlSysBankById(Long id);

    /**
     * 查询银行管理列表
     * 
     * @param blSysBank 银行管理
     * @return 银行管理集合
     */
    public List<BlSysBank> selectBlSysBankList(BlSysBank blSysBank);

    /**
     * 新增银行管理
     * 
     * @param blSysBank 银行管理
     * @return 结果
     */
    public int insertBlSysBank(BlSysBank blSysBank);

    /**
     * 修改银行管理
     * 
     * @param blSysBank 银行管理
     * @return 结果
     */
    public int updateBlSysBank(BlSysBank blSysBank);

    /**
     * 删除银行管理
     * 
     * @param id 银行管理主键
     * @return 结果
     */
    public int deleteBlSysBankById(Long id);

    /**
     * 批量删除银行管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBlSysBankByIds(Long[] ids);
}
