package com.ruoyi.billiard.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.domain.DeskBooking;
import com.ruoyi.billiard.domain.TutorBooking;
import com.ruoyi.billiard.enums.BookingStatus;
import com.ruoyi.billiard.mapper.TutorBookingMapper;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.MyBaseEntity;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.service.ITutorBookingService;
/**
 * 教练预约Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@Service
public class TutorBookingServiceImpl extends ServiceImpl<TutorBookingMapper,TutorBooking> implements ITutorBookingService
{


    /**
     * 查询教练预约
     * 
     * @param tutorBookingId 教练预约主键
     * @return 教练预约
     */
    @Override
    public TutorBooking selectTutorBookingByTutorBookingId(Long tutorBookingId)
    {
        return baseMapper.selectById(tutorBookingId);
    }

    /**
     * 查询教练预约列表
     * 
     * @param tutorBooking 教练预约
     * @return 教练预约
     */
    @Override
    public List<TutorBooking> selectTutorBookingList(TutorBooking tutorBooking)
    {
        return baseMapper.selectTutorBookingList(tutorBooking);
    }

    /**
     * 新增教练预约
     * 
     * @param tutorBooking 教练预约
     * @return 结果
     */
    @Override
    public TutorBooking insertTutorBooking(TutorBooking tutorBooking)
    {
        SecurityUtils.fillCreateUser(tutorBooking);
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query().eq(TutorBooking::getTutorId, tutorBooking.getTutorId())
                        .gt(TutorBooking::getStartTime, tutorBooking.getStartTime())
                        .lt(TutorBooking::getEndTime, tutorBooking.getStartTime())
                        .in(TutorBooking::getStatus, BookingStatus.ACTIVE, BookingStatus.USED)),
                "当前时间段已存在预约");
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query()
                        .eq(TutorBooking::getTutorId, tutorBooking.getTutorId())
                        .ge(TutorBooking::getStartTime, tutorBooking.getEndTime())
                        .le(TutorBooking::getEndTime, tutorBooking.getEndTime())
                        .in(TutorBooking::getStatus, BookingStatus.ACTIVE, BookingStatus.USED)),
                "当前时间段已存在预约");
        tutorBooking.setTutorBookingId(IdUtils.singleNextId());
        tutorBooking.setStatus(BookingStatus.ACTIVE);
        baseMapper.insert(tutorBooking);
        return tutorBooking;
    }

    /**
     * 修改教练预约
     * 
     * @param tutorBooking 教练预约
     * @return 结果
     */
    @Override
    public int updateTutorBooking(TutorBooking tutorBooking)
    {
        SecurityUtils.fillUpdateUser(tutorBooking);

        return baseMapper.updateById(tutorBooking);
    }

    /**
     * 批量删除教练预约
     * 
     * @param tutorBookingIds 需要删除的教练预约主键
     * @return 结果
     */
    @Override
    public int deleteTutorBookingByTutorBookingIds(Long[] tutorBookingIds)
    {
        return baseMapper.deleteTutorBookingByTutorBookingIds(tutorBookingIds);
    }

    /**
     * 删除教练预约信息
     * 
     * @param tutorBookingId 教练预约主键
     * @return 结果
     */
    @Override
    public int deleteTutorBookingByTutorBookingId(Long tutorBookingId)
    {
        return baseMapper.deleteTutorBookingByTutorBookingId(tutorBookingId);
    }

    @Override
    public     List<KeyValueVo<Long,Long>> selectBookingCount(List<Long> ids, Date startTime, Date endTime) {
        return baseMapper.selectBookingCount(ids,startTime,endTime);
    }

    @Override
    public Map<String, List<TutorBooking>> selectBookingDayMap(TutorBooking reqVo) {
        return ArrayUtil.groupBy(selectTutorBookingList(reqVo), p -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, p.getStartTime()));

    }
    @Override
    public Boolean verifyBooking(Long bookingId, Long storeId) {

        MyBaseEntity entity = new MyBaseEntity();
        SecurityUtils.fillUpdateUser(entity);
        return baseMapper.update(null, baseMapper.updateWrapper()
                .set(TutorBooking::getStatus, BookingStatus.USED)
                .set(TutorBooking::getUpdateById, entity.getUpdateById())
                .set(BaseEntity::getUpdateBy, entity.getUpdateBy())
                .set(BaseEntity::getUpdateTime, entity.getUpdateTime())
                .eq(TutorBooking::getTutorBookingId, bookingId)
                .eq(TutorBooking::getStoreId, storeId).eq(TutorBooking::getStatus, BookingStatus.ACTIVE)) > 0;
    }

    @Override
    public List<TutorBooking> queryLastBooking(List<Long> tutorIds) {
        List<Long> bookingIds = baseMapper.queryLastBooking( DateUtils.addMinutes(new Date(),30),tutorIds);
        if (CollectionUtils.isEmpty(bookingIds)) {
            return Lists.newArrayList();
        }
        return baseMapper.selectList(baseMapper.query().in(TutorBooking::getTutorBookingId, bookingIds));
    }
}
