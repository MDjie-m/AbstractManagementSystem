package com.ruoyi.billiard.service;

import java.util.List;

import com.ruoyi.billiard.domain.AddDeskScoreReqVo;
import com.ruoyi.billiard.domain.OrderDeskScore;
import com.ruoyi.billiard.enums.ScorerBtnType;

/**
 * 台球桌比分Service接口
 * 
 * @author ruoyi
 * @date 2024-09-23
 */
public interface IOrderDeskScoreService 
{
    /**
     * 查询台球桌比分
     * 
     * @param tOrderDeskScoreId 台球桌比分主键
     * @return 台球桌比分
     */
    public OrderDeskScore selectOrderDeskScoreByTOrderDeskScoreId(Long tOrderDeskScoreId);

    /**
     * 查询台球桌比分列表
     * 
     * @param orderDeskScore 台球桌比分
     * @return 台球桌比分集合
     */
    public List<OrderDeskScore> selectOrderDeskScoreList(OrderDeskScore orderDeskScore);

    /**
     * 新增台球桌比分
     * 
     * @param orderDeskScore 台球桌比分
     * @return 结果
     */
    public int insertOrderDeskScore(OrderDeskScore orderDeskScore);

    /**
     * 修改台球桌比分
     * 
     * @param orderDeskScore 台球桌比分
     * @return 结果
     */
    public int updateOrderDeskScore(OrderDeskScore orderDeskScore);

    Boolean addScore(ScorerBtnType btnType, Long storeId, Long deskId, Long orderId);

    Boolean stopRecordScore(Long deskId,Long orderId);

    Boolean initScore(Long deskId, Long orderId);
}
