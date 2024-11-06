package com.tianyi.sim.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianyi.sim.mapper.SimShutdownListMapper;
import com.tianyi.sim.domain.SimShutdownList;
import com.tianyi.sim.service.ISimShutdownListService;

/**
 * 停机清单Service业务层处理
 * 
 * @author tianyi
 * @date 2024-11-05
 */
@Service
public class SimShutdownListServiceImpl implements ISimShutdownListService 
{
    @Autowired
    private SimShutdownListMapper simShutdownListMapper;

    /**
     * 查询停机清单
     * 
     * @param provId 停机清单主键
     * @return 停机清单
     */
    @Override
    public SimShutdownList selectSimShutdownListByProvId(Long provId)
    {
        return simShutdownListMapper.selectSimShutdownListByProvId(provId);
    }

    /**
     * 查询停机清单列表
     * 
     * @param simShutdownList 停机清单
     * @return 停机清单
     */
    @Override
    public List<SimShutdownList> selectSimShutdownListList(SimShutdownList simShutdownList)
    {
        return simShutdownListMapper.selectSimShutdownList(simShutdownList);
    }

    /**
     * 新增停机清单
     * 
     * @param simShutdownList 停机清单
     * @return 结果
     */
    @Override
    public int insertSimShutdownList(SimShutdownList simShutdownList)
    {
        return simShutdownListMapper.insertSimShutdownList(simShutdownList);
    }

    /**
     * 修改停机清单
     * 
     * @param simShutdownList 停机清单
     * @return 结果
     */
    @Override
    public int updateSimShutdownList(SimShutdownList simShutdownList)
    {
        return simShutdownListMapper.updateSimShutdownList(simShutdownList);
    }

    /**
     * 批量删除停机清单
     * 
     * @param provIds 需要删除的停机清单主键
     * @return 结果
     */
    @Override
    public int deleteSimShutdownListByProvIds(Long[] provIds)
    {
        return simShutdownListMapper.deleteSimShutdownListByProvIds(provIds);
    }

    /**
     * 删除停机清单信息
     * 
     * @param provId 停机清单主键
     * @return 结果
     */
    @Override
    public int deleteSimShutdownListByProvId(Long provId)
    {
        return simShutdownListMapper.deleteSimShutdownListByProvId(provId);
    }
}
