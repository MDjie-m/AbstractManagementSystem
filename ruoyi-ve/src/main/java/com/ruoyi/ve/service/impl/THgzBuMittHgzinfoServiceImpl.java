package com.ruoyi.ve.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ve.mapper.THgzBuMittHgzinfoMapper;
import com.ruoyi.ve.domain.THgzBuMittHgzinfo;
import com.ruoyi.ve.service.ITHgzBuMittHgzinfoService;

/**
 * 国家合格证Service业务层处理
 * 
 * @author cherigo
 * @date 2024-04-22
 */
@Service
public class THgzBuMittHgzinfoServiceImpl implements ITHgzBuMittHgzinfoService 
{
    @Autowired
    private THgzBuMittHgzinfoMapper tHgzBuMittHgzinfoMapper;

    /**
     * 查询国家合格证
     * 
     * @param id 国家合格证主键
     * @return 国家合格证
     */
    @Override
    public THgzBuMittHgzinfo selectTHgzBuMittHgzinfoById(Long id)
    {
        return tHgzBuMittHgzinfoMapper.selectTHgzBuMittHgzinfoById(id);
    }

    /**
     * 查询国家合格证列表
     * 
     * @param tHgzBuMittHgzinfo 国家合格证
     * @return 国家合格证
     */
    @Override
    public List<THgzBuMittHgzinfo> selectTHgzBuMittHgzinfoList(THgzBuMittHgzinfo tHgzBuMittHgzinfo)
    {
        return tHgzBuMittHgzinfoMapper.selectTHgzBuMittHgzinfoList(tHgzBuMittHgzinfo);
    }

    /**
     * 新增国家合格证
     * 
     * @param tHgzBuMittHgzinfo 国家合格证
     * @return 结果
     */
    @Override
    public int insertTHgzBuMittHgzinfo(THgzBuMittHgzinfo tHgzBuMittHgzinfo)
    {
        return tHgzBuMittHgzinfoMapper.insertTHgzBuMittHgzinfo(tHgzBuMittHgzinfo);
    }

    /**
     * 修改国家合格证
     * 
     * @param tHgzBuMittHgzinfo 国家合格证
     * @return 结果
     */
    @Override
    public int updateTHgzBuMittHgzinfo(THgzBuMittHgzinfo tHgzBuMittHgzinfo)
    {
        return tHgzBuMittHgzinfoMapper.updateTHgzBuMittHgzinfo(tHgzBuMittHgzinfo);
    }

    /**
     * 批量删除国家合格证
     * 
     * @param ids 需要删除的国家合格证主键
     * @return 结果
     */
    @Override
    public int deleteTHgzBuMittHgzinfoByIds(Long[] ids)
    {
        return tHgzBuMittHgzinfoMapper.deleteTHgzBuMittHgzinfoByIds(ids);
    }

    /**
     * 删除国家合格证信息
     * 
     * @param id 国家合格证主键
     * @return 结果
     */
    @Override
    public int deleteTHgzBuMittHgzinfoById(Long id)
    {
        return tHgzBuMittHgzinfoMapper.deleteTHgzBuMittHgzinfoById(id);
    }
}
