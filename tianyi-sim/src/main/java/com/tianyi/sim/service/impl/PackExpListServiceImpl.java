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
     * 查询港华套餐到期预警列表
     *
     * @param packExpList 套餐到期预警
     * @return 套餐到期预警
     */
    @Override
    public List<PackExpList> selectGhPackExpListList(PackExpList packExpList)
    {
        return packExpListMapper.selectGhPackExpListList(packExpList);
    }
}
