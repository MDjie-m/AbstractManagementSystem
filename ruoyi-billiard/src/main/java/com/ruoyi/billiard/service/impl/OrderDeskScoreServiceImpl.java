package com.ruoyi.billiard.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ruoyi.billiard.enums.ScorerBtnType;
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
public class OrderDeskScoreServiceImpl implements IOrderDeskScoreService {
    @Autowired
    private OrderDeskScoreMapper orderDeskScoreMapper;

    /**
     * 查询台球桌比分
     *
     * @param tOrderDeskScoreId 台球桌比分主键
     * @return 台球桌比分
     */
    @Override
    public OrderDeskScore selectOrderDeskScoreByTOrderDeskScoreId(Long tOrderDeskScoreId) {
        return orderDeskScoreMapper.selectById(tOrderDeskScoreId);
    }

    /**
     * 查询台球桌比分列表
     *
     * @param orderDeskScore 台球桌比分
     * @return 台球桌比分
     */
    @Override
    public List<OrderDeskScore> selectOrderDeskScoreList(OrderDeskScore orderDeskScore) {
        return orderDeskScoreMapper.selectOrderDeskScoreList(orderDeskScore);
    }

    /**
     * 新增台球桌比分
     *
     * @param orderDeskScore 台球桌比分
     * @return 结果
     */
    @Override
    public int insertOrderDeskScore(OrderDeskScore orderDeskScore) {
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
    public int updateOrderDeskScore(OrderDeskScore orderDeskScore) {
        SecurityUtils.fillUpdateUser(orderDeskScore);

        return orderDeskScoreMapper.updateById(orderDeskScore);
    }

    @Override
    public Boolean addScore(ScorerBtnType btnType, Long storeId, Long deskId, Long orderId) {
        OrderDeskScore lastScore = orderDeskScoreMapper.selectOne(orderDeskScoreMapper.query()
                .eq(OrderDeskScore::getDeskId, deskId).eq(OrderDeskScore::getOrderId, orderId)
                .orderByDesc(OrderDeskScore::getOrderDeskScoreId).last(" limit 1"));
        Date endTime = new Date();
        int lastRed = 0;
        int lastGreen = 0;
        int diffSeconds = 0;
        if (Objects.nonNull(lastScore)) {
            lastScore.setEndTime(endTime);
            lastRed = lastScore.getScoreA();
            lastGreen = lastScore.getScoreB();
            diffSeconds = DateUtils.diffSeconds(endTime, lastScore.getStartTime());
            //小于五秒说明在连续按键
            if (diffSeconds <= 5 && !Objects.equals(btnType, ScorerBtnType.CLEAR)) {
                orderDeskScoreMapper.deleteById(lastScore.getOrderDeskScoreId());
                endTime = lastScore.getStartTime();
            } else {
                orderDeskScoreMapper.updateById(lastScore);
            }
        }
        OrderDeskScore newScore = OrderDeskScore
                .builder()
                .orderDeskScoreId(IdUtils.singleNextId())
                .deskId(deskId)
                .startTime(endTime)
                .orderId(orderId)
                .build();
        newScore.setScoreA(lastRed);
        newScore.setScoreB(lastGreen);
        if (Objects.equals(btnType, ScorerBtnType.RED)) {
            newScore.setScoreA(lastRed + 1);

        } else if (Objects.equals(btnType, ScorerBtnType.GREEN)) {
            newScore.setScoreB(lastGreen + 1);
        } else {
            newScore.setScoreA(0);
            newScore.setScoreB(0);
        }
        newScore.setCreateById(0L);
        newScore.setCreateBy("system");
        newScore.setCreateTime(LocalDateTime.now());
        orderDeskScoreMapper.insert(newScore);
        return true;
    }

    @Override
    public Boolean stopRecordScore(Long deskId, Long orderId) {
        OrderDeskScore lastScore = orderDeskScoreMapper.selectOne(orderDeskScoreMapper.query()
                .eq(OrderDeskScore::getDeskId, deskId).eq(OrderDeskScore::getOrderId, orderId)
                .orderByDesc(OrderDeskScore::getOrderDeskScoreId).last(" limit 1"));
        if (Objects.nonNull(lastScore) && Objects.isNull(lastScore.getEndTime())) {
            lastScore.setEndTime(new Date());
            lastScore.setUpdateTime(LocalDateTime.now());
            lastScore.setUpdateById(0L);
            lastScore.setUpdateBy("system");
            lastScore.setUpdateTime(LocalDateTime.now());
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean initScore(Long deskId, Long orderId) {
        OrderDeskScore lastScore = orderDeskScoreMapper.selectOne(orderDeskScoreMapper.query()
                .eq(OrderDeskScore::getDeskId, deskId).eq(OrderDeskScore::getOrderId, orderId)
                .orderByDesc(OrderDeskScore::getOrderDeskScoreId).last(" limit 1"));
        if (Objects.nonNull(lastScore)) {
            return Boolean.TRUE;
        }
        OrderDeskScore newScore = OrderDeskScore
                .builder()
                .orderDeskScoreId(IdUtils.singleNextId())
                .deskId(deskId)
                .startTime(new Date())
                .orderId(orderId)
                .build();
        newScore.setScoreA(0);
        newScore.setScoreB(0);
        newScore.setCreateById(0L);
        newScore.setCreateBy("system");
        newScore.setCreateTime(LocalDateTime.now());
        orderDeskScoreMapper.insert(newScore);
        return Boolean.TRUE;
    }
}
