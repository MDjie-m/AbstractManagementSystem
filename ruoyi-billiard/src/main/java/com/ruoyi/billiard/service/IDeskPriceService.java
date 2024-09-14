package com.ruoyi.billiard.service;

import java.util.List;
import com.ruoyi.billiard.domain.DeskPrice;

/**
 * 球桌价格Service接口
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
public interface IDeskPriceService 
{
    /**
     * 查询球桌价格
     * 
     * @param deskPriceId 球桌价格主键
     * @return 球桌价格
     */
    public DeskPrice selectDeskPriceByDeskPriceId(Long deskPriceId);

    /**
     * 查询球桌价格列表
     * 
     * @param deskPrice 球桌价格
     * @return 球桌价格集合
     */
    public List<DeskPrice> selectDeskPriceList(DeskPrice deskPrice);

    /**
     * 新增球桌价格
     * 
     * @param deskPrice 球桌价格
     * @return 结果
     */
    public int insertDeskPrice(DeskPrice deskPrice);

    /**
     * 修改球桌价格
     * 
     * @param deskPrice 球桌价格
     * @return 结果
     */
    public int updateDeskPrice(DeskPrice deskPrice);

    /**
     * 批量删除球桌价格
     * 
     * @param deskPriceIds 需要删除的球桌价格主键集合
     * @return 结果
     */
    public int deleteDeskPriceByDeskPriceIds(Long[] deskPriceIds);

    /**
     * 删除球桌价格信息
     * 
     * @param deskPriceId 球桌价格主键
     * @return 结果
     */
    public int deleteDeskPriceByDeskPriceId(Long deskPriceId);
}
