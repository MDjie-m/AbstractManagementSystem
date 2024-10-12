package com.ruoyi.billiard.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.enums.LightTimerType;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.LightTimerMapper;
import com.ruoyi.billiard.domain.LightTimer;
import com.ruoyi.billiard.service.ILightTimerService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 灯光计时器Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-19
 */
@Service
public class LightTimerServiceImpl extends ServiceImpl<LightTimerMapper,LightTimer> implements ILightTimerService {
    @Autowired
    private LightTimerMapper lightTimerMapper;

    @Autowired
    private StoreDeskMapper storeDeskMapper;

    /**
     * 查询灯光计时器
     *
     * @param lightTimerId 灯光计时器主键
     * @return 灯光计时器
     */
    @Override
    public LightTimer selectLightTimerByLightTimerId(Long lightTimerId) {
        return lightTimerMapper.selectById(lightTimerId);
    }

    /**
     * 查询灯光计时器列表
     *
     * @param lightTimer 灯光计时器
     * @return 灯光计时器
     */
    @Override
    public List<LightTimer> selectLightTimerList(LightTimer lightTimer) {
        Date time = DateUtils.removeSeconds(lightTimer.getEndTime());
        Date endTime = Objects.nonNull(time) ? DateUtils.addSeconds(time, 59) : null;
        List<LightTimer> timers = lightTimerMapper.selectList(lightTimerMapper.query()
                .eq(Objects.nonNull(lightTimer.getDeskId()), LightTimer::getDeskId, lightTimer.getDeskId())
                .eq(LightTimer::getStoreId, lightTimer.getStoreId())
                .between(Objects.nonNull(time), LightTimer::getEndTime,
                        time, endTime));
        if (CollectionUtils.isNotEmpty(timers)) {
            Map<Long, Integer> deskMap = ArrayUtil.toMap(storeDeskMapper.selectList(storeDeskMapper.query().in(StoreDesk::getDeskId,
                    timers.stream().map(LightTimer::getDeskId).collect(Collectors.toList()))), StoreDesk::getDeskId, StoreDesk::getDeskNum);
            for (LightTimer timer : timers) {
                timer.setDeskNum(deskMap.get(timer.getDeskId()));
            }
        }

        return timers;
    }

    /**
     * 新增灯光计时器
     *
     * @param lightTimer 灯光计时器
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertLightTimer(LightTimer lightTimer) {
        if (Objects.equals(lightTimer.getLightType(), LightTimerType.CALC_FEE )) {
            AssertUtil.notNullOrEmpty(lightTimer.getOrderId(), "订单id不能为空");
        }
        lightTimerMapper.delete(lightTimerMapper.query().eq(LightTimer::getDeskId, lightTimer.getDeskId()));
        SecurityUtils.fillCreateUser(lightTimer);
        lightTimer.setStartTime(DateUtils.removeSeconds(lightTimer.getStartTime()));
        lightTimer.setEndTime(DateUtils.removeSeconds(lightTimer.getEndTime()));
        lightTimer.setEnable(Boolean.TRUE);
//        AssertUtil.isTrue(!lightTimerMapper.exists(lightTimerMapper.query()
//                        .between(LightTimer::getStartTime, lightTimer.getStartTime(), lightTimer.getEndTime())
//                        .eq(LightTimer::getEnable, Boolean.TRUE)
//                        .or().between(LightTimer::getEndTime, lightTimer.getStartTime(), lightTimer.getEndTime()).eq(LightTimer::getDeskId, lightTimer.getDeskId()))
//                , "该时间段已有灯光定时");
        return lightTimerMapper.insert(lightTimer);
    }

    @Override
    public Boolean removeByTime(Date time, Long storeId) {
        lightTimerMapper.delete(lightTimerMapper.query().eq(LightTimer::getStoreId, storeId).le(LightTimer::getEndTime, time));
        return Boolean.TRUE;
    }

    @Override
    public void deleteById(Long id) {
        lightTimerMapper.deleteById(id);
    }
}
