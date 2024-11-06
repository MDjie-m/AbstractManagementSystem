package com.tianyi.sim.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianyi.sim.mapper.SimNetStopListMapper;
import com.tianyi.sim.domain.SimNetStopList;
import com.tianyi.sim.service.ISimNetStopListService;

/**
 * 断网清单Service业务层处理
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Service
public class SimNetStopListServiceImpl implements ISimNetStopListService 
{
    @Autowired
    private SimNetStopListMapper simNetStopListMapper;

    /**
     * 查询断网清单
     * 
     * @param provId 断网清单主键
     * @return 断网清单
     */
    @Override
    public SimNetStopList selectSimNetStopListByProvId(Long provId)
    {
        return simNetStopListMapper.selectSimNetStopListByProvId(provId);
    }

    /**
     * 查询断网清单列表
     * 
     * @param simNetStopList 断网清单
     * @return 断网清单
     */
    @Override
    public List<SimNetStopList> selectSimNetStopListList(SimNetStopList simNetStopList)
    {
        return simNetStopListMapper.selectSimNetStopListList(simNetStopList);
    }

    /**
     * 新增断网清单
     * 
     * @param simNetStopList 断网清单
     * @return 结果
     */
    @Override
    public int insertSimNetStopList(SimNetStopList simNetStopList)
    {
        return simNetStopListMapper.insertSimNetStopList(simNetStopList);
    }

    /**
     * 修改断网清单
     * 
     * @param simNetStopList 断网清单
     * @return 结果
     */
    @Override
    public int updateSimNetStopList(SimNetStopList simNetStopList)
    {
        return simNetStopListMapper.updateSimNetStopList(simNetStopList);
    }

    /**
     * 批量删除断网清单
     * 
     * @param provIds 需要删除的断网清单主键
     * @return 结果
     */
    @Override
    public int deleteSimNetStopListByProvIds(Long[] provIds)
    {
        return simNetStopListMapper.deleteSimNetStopListByProvIds(provIds);
    }

    /**
     * 删除断网清单信息
     * 
     * @param provId 断网清单主键
     * @return 结果
     */
    @Override
    public int deleteSimNetStopListByProvId(Long provId)
    {
        return simNetStopListMapper.deleteSimNetStopListByProvId(provId);
    }
}
