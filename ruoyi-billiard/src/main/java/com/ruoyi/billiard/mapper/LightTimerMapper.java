package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.LightTimer;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 灯光计时器Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-19
 */
@Mapper
public interface LightTimerMapper extends MyBaseMapper<LightTimer>
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

}
