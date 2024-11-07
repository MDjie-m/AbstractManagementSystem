package com.tianyi.sim.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianyi.sim.mapper.SimNbListMapper;
import com.tianyi.sim.domain.SimNbList;
import com.tianyi.sim.service.ISimNbListService;

/**
 * NB异常清单Service业务层处理
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Service
public class SimNbListServiceImpl implements ISimNbListService 
{
    @Autowired
    private SimNbListMapper simNbListMapper;

    /**
     * 查询NB异常清单
     * 
     * @param provId NB异常清单主键
     * @return NB异常清单
     */
    @Override
    public SimNbList selectSimNbListByProvId(Long provId)
    {
        return simNbListMapper.selectSimNbListByProvId(provId);
    }

    /**
     * 查询NB异常清单列表
     * 
     * @param simNbList NB异常清单
     * @return NB异常清单
     */
    @Override
    public List<SimNbList> selectSimNbListList(SimNbList simNbList)
    {
        return simNbListMapper.selectSimNbListList(simNbList);
    }

    /**
     * 新增NB异常清单
     * 
     * @param simNbList NB异常清单
     * @return 结果
     */
    @Override
    public int insertSimNbList(SimNbList simNbList)
    {
        return simNbListMapper.insertSimNbList(simNbList);
    }

    /**
     * 修改NB异常清单
     * 
     * @param simNbList NB异常清单
     * @return 结果
     */
    @Override
    public int updateSimNbList(SimNbList simNbList)
    {
        return simNbListMapper.updateSimNbList(simNbList);
    }

    /**
     * 批量删除NB异常清单
     * 
     * @param provIds 需要删除的NB异常清单主键
     * @return 结果
     */
    @Override
    public int deleteSimNbListByProvIds(Long[] provIds)
    {
        return simNbListMapper.deleteSimNbListByProvIds(provIds);
    }

    /**
     * 删除NB异常清单信息
     * 
     * @param provId NB异常清单主键
     * @return 结果
     */
    @Override
    public int deleteSimNbListByProvId(Long provId)
    {
        return simNbListMapper.deleteSimNbListByProvId(provId);
    }
}
