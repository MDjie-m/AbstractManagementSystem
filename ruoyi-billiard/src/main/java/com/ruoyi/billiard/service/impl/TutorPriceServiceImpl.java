package com.ruoyi.billiard.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.mapper.TutorPriceMapper;
import com.ruoyi.billiard.domain.TutorPrice;
import com.ruoyi.billiard.service.ITutorPriceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教练价格Service业务层处理
 * 
 * @author zhoukeu
 * @date 2024-09-14
 */
@Service
public class TutorPriceServiceImpl implements ITutorPriceService 
{
    @Autowired
    private TutorPriceMapper tutorPriceMapper;

    @Autowired
    private IStoreService storeService;

    /**
     * 查询教练价格
     * 
     * @param tutorPriceId 教练价格主键
     * @return 教练价格
     */
    @Override
    public TutorPrice selectTutorPriceByTutorPriceId(Long tutorPriceId)
    {
        return tutorPriceMapper.selectById(tutorPriceId);
    }

    /**
     * 查询教练价格列表
     * 
     * @param tutorPrice 教练价格
     * @return 教练价格
     */
    @Override
    public List<TutorPrice> selectTutorPriceList(TutorPrice tutorPrice)
    {
        List<TutorPrice> tutorPrices = Optional.ofNullable(tutorPriceMapper.selectTutorPriceList(tutorPrice)).orElse(Collections.emptyList());
        tutorPrices.forEach(tutorPrice1 -> tutorPrice1.setStoreName(storeService.selectStoreByStoreId(tutorPrice1.getStoreId()).getStoreName()));
        return tutorPrices;
    }

    /**
     * 新增教练价格
     * 
     * @param tutorPrice 教练价格
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertTutorPrice(TutorPrice tutorPrice)
    {
        SecurityUtils.fillCreateUser(tutorPrice);
        tutorPrice.setTutorPriceId(IdUtils.singleNextId());

        Long num = tutorPriceMapper.selectCount(tutorPriceMapper.query().eq(TutorPrice::getStoreId, tutorPrice.getStoreId()).eq(TutorPrice::getLevel, tutorPrice.getLevel()));
        AssertUtil.isTrue(num == 0, "该门店已存在该等级教练价格");
        return tutorPriceMapper.insertTutorPrice(tutorPrice);
    }

    /**
     * 修改教练价格
     * 
     * @param tutorPrice 教练价格
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTutorPrice(TutorPrice tutorPrice)
    {
        SecurityUtils.fillUpdateUser(tutorPrice);
        Long num = tutorPriceMapper.selectCount(tutorPriceMapper.query().eq(TutorPrice::getStoreId, tutorPrice.getStoreId()).eq(TutorPrice::getLevel, tutorPrice.getLevel()).ne(TutorPrice::getTutorPriceId, tutorPrice.getTutorPriceId()));
        AssertUtil.isTrue(num == 0, "该门店已存在该等级教练价格");
        return tutorPriceMapper.updateTutorPrice(tutorPrice);
    }

    /**
     * 批量删除教练价格
     * 
     * @param tutorPriceIds 需要删除的教练价格主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteTutorPriceByTutorPriceIds(Long[] tutorPriceIds)
    {
        return tutorPriceMapper.deleteTutorPriceByTutorPriceIds(tutorPriceIds);
    }

    /**
     * 删除教练价格信息
     * 
     * @param tutorPriceId 教练价格主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteTutorPriceByTutorPriceId(Long tutorPriceId)
    {
        return tutorPriceMapper.deleteTutorPriceByTutorPriceId(tutorPriceId);
    }
}
