package com.ruoyi.companySys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.companySys.mapper.WindFarmMapper;
import com.ruoyi.companySys.domain.WindFarm;
import com.ruoyi.companySys.service.IWindFarmService;

/**
 * 风场管理Service业务层处理
 * 
 * @author GG
 * @date 2024-07-13
 */
@Service
public class WindFarmServiceImpl implements IWindFarmService 
{
    @Autowired
    private WindFarmMapper windFarmMapper;

    /**
     * 查询风场管理
     * 
     * @param wId 风场管理主键
     * @return 风场管理
     */
    @Override
    public WindFarm selectWindFarmByWId(Long wId)
    {
        return windFarmMapper.selectWindFarmByWId(wId);
    }

    /**
     * 查询风场管理列表
     * 
     * @param windFarm 风场管理
     * @return 风场管理
     */
    @Override
    public List<WindFarm> selectWindFarmList(WindFarm windFarm)
    {
        return windFarmMapper.selectWindFarmList(windFarm);
    }

    /**
     * 新增风场管理
     * 
     * @param windFarm 风场管理
     * @return 结果
     */
    @Override
    public int insertWindFarm(WindFarm windFarm)
    {
        return windFarmMapper.insertWindFarm(windFarm);
    }

    /**
     * 修改风场管理
     * 
     * @param windFarm 风场管理
     * @return 结果
     */
    @Override
    public int updateWindFarm(WindFarm windFarm)
    {
        return windFarmMapper.updateWindFarm(windFarm);
    }

    /**
     * 批量删除风场管理
     * 
     * @param wIds 需要删除的风场管理主键
     * @return 结果
     */
    @Override
    public int deleteWindFarmByWIds(Long[] wIds)
    {
        return windFarmMapper.deleteWindFarmByWIds(wIds);
    }

    /**
     * 删除风场管理信息
     * 
     * @param wId 风场管理主键
     * @return 结果
     */
    @Override
    public int deleteWindFarmByWId(Long wId)
    {
        return windFarmMapper.deleteWindFarmByWId(wId);
    }
}
