package com.tianyi.sim.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianyi.sim.mapper.PackExpListMapper;
import com.tianyi.sim.domain.PackExpList;
import com.tianyi.sim.service.IPackExpListService;

/**
 * 套餐到期预警Service业务层处理
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Service
public class PackExpListServiceImpl implements IPackExpListService 
{
    @Autowired
    private PackExpListMapper packExpListMapper;

    /**
     * 查询套餐到期预警
     * 
     * @param provId 套餐到期预警主键
     * @return 套餐到期预警
     */
    @Override
    public PackExpList selectPackExpListByProvId(Long provId)
    {
        return packExpListMapper.selectPackExpListByProvId(provId);
    }

    /**
     * 查询套餐到期预警列表
     * 
     * @param packExpList 套餐到期预警
     * @return 套餐到期预警
     */
    @Override
    public List<PackExpList> selectPackExpListList(PackExpList packExpList)
    {
        return packExpListMapper.selectPackExpListList(packExpList);
    }

    /**
     * 新增套餐到期预警
     * 
     * @param packExpList 套餐到期预警
     * @return 结果
     */
    @Override
    public int insertPackExpList(PackExpList packExpList)
    {
        return packExpListMapper.insertPackExpList(packExpList);
    }

    /**
     * 修改套餐到期预警
     * 
     * @param packExpList 套餐到期预警
     * @return 结果
     */
    @Override
    public int updatePackExpList(PackExpList packExpList)
    {
        return packExpListMapper.updatePackExpList(packExpList);
    }

    /**
     * 批量删除套餐到期预警
     * 
     * @param provIds 需要删除的套餐到期预警主键
     * @return 结果
     */
    @Override
    public int deletePackExpListByProvIds(Long[] provIds)
    {
        return packExpListMapper.deletePackExpListByProvIds(provIds);
    }

    /**
     * 删除套餐到期预警信息
     * 
     * @param provId 套餐到期预警主键
     * @return 结果
     */
    @Override
    public int deletePackExpListByProvId(Long provId)
    {
        return packExpListMapper.deletePackExpListByProvId(provId);
    }
}
