package com.ruoyi.billiard.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.billiard.domain.LightTimer;

/**
 * 灯光计时器Service接口
 * 
 * @author ruoyi
 * @date 2024-09-19
 */
public interface ILightTimerService  extends IService<LightTimer>
{
    /**
     * 查询灯光计时器
     *
     * @param lightTimerId 灯光计时器主键
     * @return 灯光计时器
     */
    public LightTimer selectLightTimerByLightTimerId(Long lightTimerId);

    /**
     * 查询灯光计时器列表
     * 
     * @param lightTimer 灯光计时器
     * @return 灯光计时器集合
     */
    public List<LightTimer> selectLightTimerList(LightTimer lightTimer);

    /**
     * 新增灯光计时器
     * 
     * @param lightTimer 灯光计时器
     * @return 结果
     */
    public int insertLightTimer(LightTimer lightTimer);

    Boolean removeByTime(Date time, Long storeId);

    void deleteById(Long id);
}
