package com.tianyi.sim.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tianyi.sim.mapper.SimArrearListMapper;
import com.tianyi.sim.domain.SimArrearList;
import com.tianyi.sim.service.ISimArrearListService;

/**
 * 欠费停机预警Service业务层处理
 * 
 * @author tianyi
 * @date 2024-11-06
 */
@Service
public class SimArrearListServiceImpl implements ISimArrearListService 
{
    @Autowired
    private SimArrearListMapper simArrearListMapper;

    /**
     * 查询欠费停机预警
     * 
     * @param provId 欠费停机预警主键
     * @return 欠费停机预警
     */
    @Override
    public SimArrearList selectSimArrearListByProvId(Long provId)
    {
        return simArrearListMapper.selectSimArrearListByProvId(provId);
    }

    /**
     * 查询欠费停机预警列表
     * 
     * @param simArrearList 欠费停机预警
     * @return 欠费停机预警
     */
    @Override
    public List<SimArrearList> selectSimArrearListList(SimArrearList simArrearList)
    {
        return simArrearListMapper.selectSimArrearListList(simArrearList);
    }

    /**
     * 新增欠费停机预警
     * 
     * @param simArrearList 欠费停机预警
     * @return 结果
     */
    @Override
    public int insertSimArrearList(SimArrearList simArrearList)
    {
        return simArrearListMapper.insertSimArrearList(simArrearList);
    }

    /**
     * 修改欠费停机预警
     * 
     * @param simArrearList 欠费停机预警
     * @return 结果
     */
    @Override
    public int updateSimArrearList(SimArrearList simArrearList)
    {
        return simArrearListMapper.updateSimArrearList(simArrearList);
    }

    /**
     * 批量删除欠费停机预警
     * 
     * @param provIds 需要删除的欠费停机预警主键
     * @return 结果
     */
    @Override
    public int deleteSimArrearListByProvIds(Long[] provIds)
    {
        return simArrearListMapper.deleteSimArrearListByProvIds(provIds);
    }

    /**
     * 删除欠费停机预警信息
     * 
     * @param provId 欠费停机预警主键
     * @return 结果
     */
    @Override
    public int deleteSimArrearListByProvId(Long provId)
    {
        return simArrearListMapper.deleteSimArrearListByProvId(provId);
    }
}
