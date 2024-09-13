package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.DeskPriceMapper;
import com.ruoyi.billiard.domain.DeskPrice;
import com.ruoyi.billiard.service.IDeskPriceService;

/**
 * 球桌价格Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Service
public class DeskPriceServiceImpl implements IDeskPriceService 
{
    @Autowired
    private DeskPriceMapper deskPriceMapper;

    /**
     * 查询球桌价格
     * 
     * @param deskPriceId 球桌价格主键
     * @return 球桌价格
     */
    @Override
    public DeskPrice selectDeskPriceByDeskPriceId(Long deskPriceId)
    {
        return deskPriceMapper.selectById(deskPriceId);
    }

    /**
     * 查询球桌价格列表
     * 
     * @param deskPrice 球桌价格
     * @return 球桌价格
     */
    @Override
    public List<DeskPrice> selectDeskPriceList(DeskPrice deskPrice)
    {
        return deskPriceMapper.selectDeskPriceList(deskPrice);
    }

    /**
     * 新增球桌价格
     * 
     * @param deskPrice 球桌价格
     * @return 结果
     */
    @Override
    public int insertDeskPrice(DeskPrice deskPrice)
    {
        SecurityUtils.fillCreateUser(deskPrice);
        deskPrice.setDeskPriceId(IdUtils.singleNextId());
        return deskPriceMapper.insertDeskPrice(deskPrice);
    }

    /**
     * 修改球桌价格
     * 
     * @param deskPrice 球桌价格
     * @return 结果
     */
    @Override
    public int updateDeskPrice(DeskPrice deskPrice)
    {
        SecurityUtils.fillUpdateUser(deskPrice);

        return deskPriceMapper.updateDeskPrice(deskPrice);
    }

    /**
     * 批量删除球桌价格
     * 
     * @param deskPriceIds 需要删除的球桌价格主键
     * @return 结果
     */
    @Override
    public int deleteDeskPriceByDeskPriceIds(Long[] deskPriceIds)
    {
        return deskPriceMapper.deleteDeskPriceByDeskPriceIds(deskPriceIds);
    }

    /**
     * 删除球桌价格信息
     * 
     * @param deskPriceId 球桌价格主键
     * @return 结果
     */
    @Override
    public int deleteDeskPriceByDeskPriceId(Long deskPriceId)
    {
        return deskPriceMapper.deleteDeskPriceByDeskPriceId(deskPriceId);
    }
}
