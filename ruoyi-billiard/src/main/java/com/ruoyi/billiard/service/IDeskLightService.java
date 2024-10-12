package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.DeskLight;
import com.ruoyi.billiard.domain.vo.LightStatusResVo;
import com.ruoyi.billiard.domain.vo.LightSwitchReqVo;

/**
 * 灯光Service接口
 * 
 * @author ruoyi
 * @date 2024-10-12
 */
public interface IDeskLightService  extends IService<DeskLight>
{
    /**
     * 查询灯光
     * 
     * @param lightId 灯光主键
     * @return 灯光
     */
    public DeskLight selectDeskLightByLightId(Long lightId);

    /**
     * 查询灯光列表
     * 
     * @param deskLight 灯光
     * @return 灯光集合
     */
    public List<DeskLight> selectDeskLightList(DeskLight deskLight);

    /**
     * 新增灯光
     * 
     * @param deskLight 灯光
     * @return 结果
     */
    public int insertDeskLight(DeskLight deskLight);

    /**
     * 修改灯光
     * 
     * @param deskLight 灯光
     * @return 结果
     */
    public int updateDeskLight(DeskLight deskLight);

    /**
     * 批量删除灯光
     * 
     * @param lightIds 需要删除的灯光主键集合
     * @return 结果
     */
    public int deleteDeskLightByLightIds(Long[] lightIds);

    /**
     * 删除灯光信息
     * 
     * @param lightId 灯光主键
     * @return 结果
     */
    public int deleteDeskLightByLightId(Long lightId);

    Boolean switchLight(LightSwitchReqVo reqVo);

    LightStatusResVo queryLightStatus(Long storeId);
}
