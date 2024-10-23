package com.ruoyi.billiard.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.billiard.domain.DeskType;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.mapper.StoreDeskMapper;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.DeskPlace;
import com.ruoyi.billiard.service.IDeskPlaceService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.DeskPlaceMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 台桌区域Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-23
 */
@Service
public class DeskPlaceServiceImpl extends ServiceImpl<DeskPlaceMapper, DeskPlace> implements IDeskPlaceService {


    @Resource
    private StoreDeskMapper storeDeskMapper;

    /**
     * 查询台桌区域
     *
     * @param deskPlaceId 台桌区域主键
     * @return 台桌区域
     */
    @Override
    public DeskPlace selectDeskPlaceByDeskPlaceId(Long deskPlaceId) {
        return baseMapper.selectById(deskPlaceId);
    }

    /**
     * 查询台桌区域列表
     *
     * @param deskPlace 台桌区域
     * @return 台桌区域
     */
    @Override
    public List<DeskPlace> selectDeskPlaceList(DeskPlace deskPlace) {
        return baseMapper.selectDeskPlaceList(deskPlace);
    }

    /**
     * 新增台桌区域
     *
     * @param deskPlace 台桌区域
     * @return 结果
     */
    @Override
    public int insertDeskPlace(DeskPlace deskPlace) {
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query().eq(DeskPlace::getName, deskPlace.getName())
                .eq(DeskPlace::getStoreId, deskPlace.getStoreId())), "区域名称重复");
        SecurityUtils.fillCreateUser(deskPlace);
        deskPlace.setDeskPlaceId(IdUtils.singleNextId());
        return baseMapper.insert(deskPlace);
    }

    /**
     * 修改台桌区域
     *
     * @param deskPlace 台桌区域
     * @return 结果
     */
    @Override
    public int updateDeskPlace(DeskPlace deskPlace) {
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query().eq(DeskPlace::getName, deskPlace.getName())
                .eq(DeskPlace::getStoreId, deskPlace.getStoreId()).ne(DeskPlace::getDeskPlaceId, deskPlace.getDeskPlaceId())), "区域名称重复");
        SecurityUtils.fillUpdateUser(deskPlace);

        return baseMapper.updateById(deskPlace);
    }

    /**
     * 批量删除台桌区域
     *
     * @param deskPlaceIds 需要删除的台桌区域主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDeskPlaceByDeskPlaceIds(Long[] deskPlaceIds) {
        for (Long deskPlaceId : deskPlaceIds) {
            deleteDeskPlaceByDeskPlaceId(deskPlaceId);
        }
        return deskPlaceIds.length;
    }

    /**
     * 删除台桌区域信息
     *
     * @param deskPlaceId 台桌区域主键
     * @return 结果
     */
    @Override
    public int deleteDeskPlaceByDeskPlaceId(Long deskPlaceId) {
        AssertUtil.isTrue(!storeDeskMapper.exists(storeDeskMapper.query().eq(StoreDesk::getPlaceType, deskPlaceId)), "区域名称已被使用，无法删除");
        return baseMapper.deleteDeskPlaceByDeskPlaceId(deskPlaceId);
    }

    @Override
    public List<KeyValueVo<Long, Long>> selectListByStoreId(Long storeId) {
        List<DeskPlace> list=selectDeskPlaceList(DeskPlace.builder().storeId(storeId).build());
        return list.stream().map(p->{
            KeyValueVo<Long, Long> item=new KeyValueVo<>(p.getDeskPlaceId(),p.getDeskPlaceId(),p.getName());
            return  item;
        }).collect(Collectors.toList());
    }
}
