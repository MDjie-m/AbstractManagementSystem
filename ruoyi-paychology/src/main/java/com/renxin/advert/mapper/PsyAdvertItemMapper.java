package com.renxin.advert.mapper;


import com.renxin.advert.domain.PsyAdvertItem;

import java.util.List;

/**
 * 广告条目Mapper接口
 * 
 * @author renxin
 * @date 2024-08-16
 */
public interface PsyAdvertItemMapper 
{
    /**
     * 查询广告条目
     * 
     * @param id 广告条目主键
     * @return 广告条目
     */
    public PsyAdvertItem selectPsyAdvertItemById(Long id);

    /**
     * 查询广告条目列表
     * 
     * @param psyAdvertItem 广告条目
     * @return 广告条目集合
     */
    public List<PsyAdvertItem> selectPsyAdvertItemList(PsyAdvertItem psyAdvertItem);

    /**
     * 新增广告条目
     * 
     * @param psyAdvertItem 广告条目
     * @return 结果
     */
    public int insertPsyAdvertItem(PsyAdvertItem psyAdvertItem);

    /**
     * 修改广告条目
     * 
     * @param psyAdvertItem 广告条目
     * @return 结果
     */
    public int updatePsyAdvertItem(PsyAdvertItem psyAdvertItem);

    /**
     * 删除广告条目
     */
    public int deleteItemByAdvertNo(String no);

    /**
     * 批量删除广告条目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemByIds(Long[] ids);

    /**
     * 批量insert广告条目
     * @param list
     * @return
     */
    public int insertPsyAdvertItemList(List<PsyAdvertItem> list);
}
