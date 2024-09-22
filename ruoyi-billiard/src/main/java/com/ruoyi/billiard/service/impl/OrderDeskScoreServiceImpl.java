package com.ruoyi.billiard.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.OrderDeskScoreMapper;
import com.ruoyi.billiard.domain.OrderDeskScore;
import com.ruoyi.billiard.service.IOrderDeskScoreService;

/**
 * 台球桌比分Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
@Service
public class OrderDeskScoreServiceImpl implements IOrderDeskScoreService 
{
    @Autowired
    private OrderDeskScoreMapper orderDeskScoreMapper;

    /**
     * 查询台球桌比分
     * 
     * @param tOrderDeskScoreId 台球桌比分主键
     * @return 台球桌比分
     */
    @Override
    public OrderDeskScore selectOrderDeskScoreByTOrderDeskScoreId(Long tOrderDeskScoreId)
    {
        return orderDeskScoreMapper.selectById(tOrderDeskScoreId);
    }

    /**
     * 查询台球桌比分列表
     * 
     * @param orderDeskScore 台球桌比分
     * @return 台球桌比分
     */
    @Override
    public List<OrderDeskScore> selectOrderDeskScoreList(OrderDeskScore orderDeskScore)
    {
        return orderDeskScoreMapper.selectOrderDeskScoreList(orderDeskScore);
    }

    /**
     * 新增台球桌比分
     * 
     * @param orderDeskScore 台球桌比分
     * @return 结果
     */
    @Override
    public int insertOrderDeskScore(OrderDeskScore orderDeskScore)
    {
        SecurityUtils.fillCreateUser(orderDeskScore);
        orderDeskScore.setOrderDeskScoreId(IdUtils.singleNextId());
        return orderDeskScoreMapper.insert(orderDeskScore);
    }

    /**
     * 修改台球桌比分
     * 
     * @param orderDeskScore 台球桌比分
     * @return 结果
     */
    @Override
    public int updateOrderDeskScore(OrderDeskScore orderDeskScore)
    {
        SecurityUtils.fillUpdateUser(orderDeskScore);

        return orderDeskScoreMapper.updateById(orderDeskScore);
    }


}
