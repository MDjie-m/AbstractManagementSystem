package com.tianyi.sim.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianyi.sim.mapper.NonuseWarningListMapper;
import com.tianyi.sim.domain.NonuseWarningList;
import com.tianyi.sim.service.INonuseWarningListService;

/**
 * 套餐长期不使用预警Service业务层处理
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Service
public class NonuseWarningListServiceImpl implements INonuseWarningListService 
{
    @Autowired
    private NonuseWarningListMapper nonuseWarningListMapper;

    /**
     * 查询套餐长期不使用预警
     * 
     * @param provId 套餐长期不使用预警主键
     * @return 套餐长期不使用预警
     */
    @Override
    public NonuseWarningList selectNonuseWarningListByProvId(Long provId)
    {
        return nonuseWarningListMapper.selectNonuseWarningListByProvId(provId);
    }

    /**
     * 查询套餐长期不使用预警列表
     * 
     * @param nonuseWarningList 套餐长期不使用预警
     * @return 套餐长期不使用预警
     */
    @Override
    public List<NonuseWarningList> selectNonuseWarningListList(NonuseWarningList nonuseWarningList)
    {
        return nonuseWarningListMapper.selectNonuseWarningListList(nonuseWarningList);
    }

    /**
     * 新增套餐长期不使用预警
     * 
     * @param nonuseWarningList 套餐长期不使用预警
     * @return 结果
     */
    @Override
    public int insertNonuseWarningList(NonuseWarningList nonuseWarningList)
    {
        return nonuseWarningListMapper.insertNonuseWarningList(nonuseWarningList);
    }

    /**
     * 修改套餐长期不使用预警
     * 
     * @param nonuseWarningList 套餐长期不使用预警
     * @return 结果
     */
    @Override
    public int updateNonuseWarningList(NonuseWarningList nonuseWarningList)
    {
        return nonuseWarningListMapper.updateNonuseWarningList(nonuseWarningList);
    }

    /**
     * 批量删除套餐长期不使用预警
     * 
     * @param provIds 需要删除的套餐长期不使用预警主键
     * @return 结果
     */
    @Override
    public int deleteNonuseWarningListByProvIds(Long[] provIds)
    {
        return nonuseWarningListMapper.deleteNonuseWarningListByProvIds(provIds);
    }

    /**
     * 删除套餐长期不使用预警信息
     * 
     * @param provId 套餐长期不使用预警主键
     * @return 结果
     */
    @Override
    public int deleteNonuseWarningListByProvId(Long provId)
    {
        return nonuseWarningListMapper.deleteNonuseWarningListByProvId(provId);
    }
}
