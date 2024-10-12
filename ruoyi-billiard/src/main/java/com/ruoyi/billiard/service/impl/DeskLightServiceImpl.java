package com.ruoyi.billiard.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.vo.LightStatusResVo;
import com.ruoyi.billiard.domain.vo.LightSwitchReqVo;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.DeskLight;
import com.ruoyi.billiard.service.IDeskLightService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.DeskLightMapper;

import javax.annotation.Resource;

/**
 * 灯光Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-12
 */
@Service
public class DeskLightServiceImpl extends ServiceImpl<DeskLightMapper, DeskLight> implements IDeskLightService {

    @Resource
    private StoreDeskMapper storeDeskMapper;

    /**
     * 查询灯光
     *
     * @param lightId 灯光主键
     * @return 灯光
     */
    @Override
    public DeskLight selectDeskLightByLightId(Long lightId) {
        return baseMapper.selectById(lightId);
    }

    /**
     * 查询灯光列表
     *
     * @param deskLight 灯光
     * @return 灯光
     */
    @Override
    public List<DeskLight> selectDeskLightList(DeskLight deskLight) {
        return baseMapper.selectDeskLightList(deskLight);
    }

    /**
     * 新增灯光
     *
     * @param deskLight 灯光
     * @return 结果
     */
    @Override
    public int insertDeskLight(DeskLight deskLight) {
        SecurityUtils.fillCreateUser(deskLight);
        deskLight.setLightId(IdUtils.singleNextId());
        return baseMapper.insert(deskLight);
    }

    /**
     * 修改灯光
     *
     * @param deskLight 灯光
     * @return 结果
     */
    @Override
    public int updateDeskLight(DeskLight deskLight) {
        SecurityUtils.fillUpdateUser(deskLight);

        return baseMapper.updateById(deskLight);
    }

    /**
     * 批量删除灯光
     *
     * @param lightIds 需要删除的灯光主键
     * @return 结果
     */
    @Override
    public int deleteDeskLightByLightIds(Long[] lightIds) {
        return baseMapper.deleteDeskLightByLightIds(lightIds);
    }

    /**
     * 删除灯光信息
     *
     * @param lightId 灯光主键
     * @return 结果
     */
    @Override
    public int deleteDeskLightByLightId(Long lightId) {
        return baseMapper.deleteDeskLightByLightId(lightId);
    }

    @Override
    public Boolean switchLight(LightSwitchReqVo reqVo) {
        StoreDesk desk = storeDeskMapper.selectOne(storeDeskMapper.query().eq(StoreDesk::getDeskNum, reqVo.getDeskNum())
                .eq(StoreDesk::getStoreId, reqVo.getStoreId()));
        AssertUtil.notNullOrEmpty(desk, "台桌不存在");

        DeskLight light = baseMapper.selectOne(baseMapper.query().eq(DeskLight::getStoreId, reqVo.getStoreId())
                .eq(DeskLight::getDeskNum, reqVo.getDeskNum()));
        if (Objects.isNull(light)) {
            light = new DeskLight();
            light.setStoreId(reqVo.getStoreId());
            light.setOpen(reqVo.getOpen());
            light.setDeskNum(reqVo.getDeskNum());
            light.setCreateById(0L);
            light.setCreateBy("system");
            light.setCreateTime(LocalDateTime.now());
            light.setLightId(IdUtils.singleNextId());
            baseMapper.insert(light);
            return Boolean.TRUE;
        }
        light.setUpdateTime(LocalDateTime.now());
        light.setOpen(reqVo.getOpen());
        light.setUpdateById(0L);
        light.setUpdateBy("system");
        baseMapper.updateById(light);
        return Boolean.TRUE;
    }

    @Override
    public LightStatusResVo queryLightStatus(Long storeId) {
        LightStatusResVo resVo = baseMapper.queryLightStatus(storeId);
        resVo.setClose(resVo.getAllCount() - resVo.getOpen());
        return resVo;
    }
}
