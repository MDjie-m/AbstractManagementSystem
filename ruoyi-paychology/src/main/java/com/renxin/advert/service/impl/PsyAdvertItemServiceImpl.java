package com.renxin.advert.service.impl;


import com.renxin.advert.domain.PsyAdvertItem;
import com.renxin.advert.mapper.PsyAdvertItemMapper;
import com.renxin.advert.service.IPsyAdvertItemService;
import com.renxin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告条目Service业务层处理
 * 
 * @author renxin
 * @date 2024-08-16
 */
@Service
public class PsyAdvertItemServiceImpl implements IPsyAdvertItemService 
{
    @Autowired
    private PsyAdvertItemMapper psyAdvertItemMapper;

    /**
     * 查询广告条目
     * 
     * @param id 广告条目主键
     * @return 广告条目
     */
    @Override
    public PsyAdvertItem selectPsyAdvertItemById(Long id)
    {
        return psyAdvertItemMapper.selectPsyAdvertItemById(id);
    }

    /**
     * 查询广告条目列表
     * 
     * @param psyAdvertItem 广告条目
     * @return 广告条目
     */
    @Override
    public List<PsyAdvertItem> selectPsyAdvertItemList(PsyAdvertItem psyAdvertItem)
    {
        return psyAdvertItemMapper.selectPsyAdvertItemList(psyAdvertItem);
    }

    /**
     * 新增广告条目
     * 
     * @param psyAdvertItem 广告条目
     * @return 结果
     */
    @Override
    public int insertPsyAdvertItem(PsyAdvertItem psyAdvertItem)
    {
        psyAdvertItem.setCreateTime(DateUtils.getNowDate());
        return psyAdvertItemMapper.insertPsyAdvertItem(psyAdvertItem);
    }

    /**
     * 修改广告条目
     * 
     * @param psyAdvertItem 广告条目
     * @return 结果
     */
    @Override
    public int updatePsyAdvertItem(PsyAdvertItem psyAdvertItem)
    {
        psyAdvertItem.setUpdateTime(DateUtils.getNowDate());
        return psyAdvertItemMapper.updatePsyAdvertItem(psyAdvertItem);
    }

    /**
     * 批量删除广告条目
     * 
     * @param ids 需要删除的广告条目主键
     * @return 结果
     */
    @Override
    public int deleteItemByIds(Long[] ids)
    {
        return psyAdvertItemMapper.deleteItemByIds(ids);
    }

    /**
     * 删除广告条目信息
     */
    @Override
    public int deleteItemByAdvertNo(String no)
    {
        return psyAdvertItemMapper.deleteItemByAdvertNo(no);
    }

    /**
     * 批量insert广告条目
     * @param list
     * @return
     */
    @Override
    public int insertPsyAdvertItemList(List<PsyAdvertItem> list){
       return psyAdvertItemMapper.insertPsyAdvertItemList(list);
    }
}
