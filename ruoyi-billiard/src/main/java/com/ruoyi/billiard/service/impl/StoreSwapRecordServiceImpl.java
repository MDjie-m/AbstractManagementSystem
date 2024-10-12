package com.ruoyi.billiard.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.billiard.domain.vo.LightStatusResVo;
import com.ruoyi.billiard.service.IStoreService;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.StoreSwapRecord;
import com.ruoyi.billiard.service.IStoreSwapRecordService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.StoreSwapRecordMapper;

import javax.annotation.Resource;

/**
 * 交班记录Service业务层处理
 *
 * @author ruoyi
 * @date 2024-10-10
 */
@Service
public class StoreSwapRecordServiceImpl extends ServiceImpl<StoreSwapRecordMapper, StoreSwapRecord> implements IStoreSwapRecordService {

    @Resource
    private IStoreService storeService;

    /**
     * 查询交班记录
     *
     * @param swapRecordId 交班记录主键
     * @return 交班记录
     */
    @Override
    public StoreSwapRecord selectStoreSwapRecordBySwapRecordId(Long swapRecordId) {
        return baseMapper.selectById(swapRecordId);
    }

    /**
     * 查询交班记录列表
     *
     * @param storeSwapRecord 交班记录
     * @return 交班记录
     */
    @Override
    public List<StoreSwapRecord> selectStoreSwapRecordList(StoreSwapRecord storeSwapRecord) {
        return baseMapper.selectStoreSwapRecordList(storeSwapRecord);
    }

    /**
     * 新增交班记录
     *
     * @param storeSwapRecord 交班记录
     * @return 结果
     */
    @Override
    public int insertStoreSwapRecord(StoreSwapRecord reqVo) {
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query()
                .eq(StoreSwapRecord::getScheduleDay, reqVo.getScheduleDay())), "已经有交班记录，无需重复交班");
        Date time = DateUtils.toDate(reqVo.getScheduleDay());
        StoreSwapRecord storeSwapRecord = storeService.swapPreview(reqVo.getStoreId(), time, time);
        storeSwapRecord.setStoreId(reqVo.getStoreId());
        storeSwapRecord.setScheduleDay(reqVo.getScheduleDay());
        SecurityUtils.fillCreateUser(storeSwapRecord);
        storeSwapRecord.setSwapRecordId(IdUtils.singleNextId());
        storeSwapRecord.setRemark(reqVo.getRemark());
        return baseMapper.insert(storeSwapRecord);
    }

    /**
     * 修改交班记录
     *
     * @param storeSwapRecord 交班记录
     * @return 结果
     */
    @Override
    public int updateStoreSwapRecord(StoreSwapRecord storeSwapRecord) {
        SecurityUtils.fillUpdateUser(storeSwapRecord);

        return baseMapper.updateById(storeSwapRecord);
    }

    /**
     * 批量删除交班记录
     *
     * @param swapRecordIds 需要删除的交班记录主键
     * @return 结果
     */
    @Override
    public int deleteStoreSwapRecordBySwapRecordIds(Long[] swapRecordIds) {
        return baseMapper.deleteStoreSwapRecordBySwapRecordIds(swapRecordIds);
    }

    /**
     * 删除交班记录信息
     *
     * @param swapRecordId 交班记录主键
     * @return 结果
     */
    @Override
    public int deleteStoreSwapRecordBySwapRecordId(Long swapRecordId) {
        return baseMapper.deleteStoreSwapRecordBySwapRecordId(swapRecordId);
    }

}
