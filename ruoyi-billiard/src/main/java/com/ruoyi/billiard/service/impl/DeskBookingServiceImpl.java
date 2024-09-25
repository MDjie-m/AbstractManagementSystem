package com.ruoyi.billiard.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.enums.BookingStatus;
import com.ruoyi.billiard.mapper.DeskBookingMapper;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.ArrayUtil;
import com.ruoyi.common.utils.AssertUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.DeskBooking;
import com.ruoyi.billiard.service.IDeskBookingService;

/**
 * 球桌预约Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-25
 */
@Service
public class DeskBookingServiceImpl extends ServiceImpl<DeskBookingMapper, DeskBooking> implements IDeskBookingService {


    /**
     * 查询球桌预约
     *
     * @param deskBookingId 球桌预约主键
     * @return 球桌预约
     */
    @Override
    public DeskBooking selectDeskBookingByDeskBookingId(Long deskBookingId) {
        return baseMapper.selectById(deskBookingId);
    }

    /**
     * 查询球桌预约列表
     *
     * @param deskBooking 球桌预约
     * @return 球桌预约
     */
    @Override
    public List<DeskBooking> selectDeskBookingList(DeskBooking deskBooking) {
        return baseMapper.selectDeskBookingList(deskBooking);
    }

    /**
     * 新增球桌预约
     *
     * @param deskBooking 球桌预约
     * @return 结果
     */
    @Override
    public DeskBooking insertDeskBooking(DeskBooking deskBooking) {
        SecurityUtils.fillCreateUser(deskBooking);
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query().eq(DeskBooking::getDeskId, deskBooking.getDeskId())
                        .gt(DeskBooking::getStartTime, deskBooking.getStartTime())
                        .lt(DeskBooking::getEndTime, deskBooking.getStartTime())
                        .in(DeskBooking::getStatus, BookingStatus.ACTIVE, BookingStatus.USED)),
                "当前时间段已存在预约");
        AssertUtil.isTrue(!baseMapper.exists(baseMapper.query()
                        .eq(DeskBooking::getDeskId, deskBooking.getDeskId())
                        .ge(DeskBooking::getStartTime, deskBooking.getEndTime())
                        .le(DeskBooking::getEndTime, deskBooking.getEndTime())
                        .in(DeskBooking::getStatus, BookingStatus.ACTIVE, BookingStatus.USED)),
                "当前时间段已存在预约");
        deskBooking.setDeskBookingId(IdUtils.singleNextId());
        deskBooking.setStatus(BookingStatus.ACTIVE);
        baseMapper.insert(deskBooking);
        return deskBooking;
    }

    /**
     * 修改球桌预约
     *
     * @param deskBooking 球桌预约
     * @return 结果
     */
    @Override
    public int updateDeskBooking(DeskBooking deskBooking) {
        SecurityUtils.fillUpdateUser(deskBooking);

        return baseMapper.updateById(deskBooking);
    }

    /**
     * 批量删除球桌预约
     *
     * @param deskBookingIds 需要删除的球桌预约主键
     * @return 结果
     */
    @Override
    public int deleteDeskBookingByDeskBookingIds(Long[] deskBookingIds) {
        return baseMapper.deleteDeskBookingByDeskBookingIds(deskBookingIds);
    }

    /**
     * 删除球桌预约信息
     *
     * @param deskBookingId 球桌预约主键
     * @return 结果
     */
    @Override
    public int deleteDeskBookingByDeskBookingId(Long deskBookingId) {
        return baseMapper.deleteDeskBookingByDeskBookingId(deskBookingId);
    }

    @Override
    public Map<String, List<DeskBooking>> selectBookingDayMap(DeskBooking booking) {
        return ArrayUtil.groupBy(selectDeskBookingList(booking), p -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, p.getStartTime()));
    }

    @Override
    public void checkBookingExpire(Integer timeMinutes) {

        //超时自动过期
        baseMapper.update(null, baseMapper.updateWrapper().
                le(DeskBooking::getStartTime, DateUtils.toDate(LocalDateTime.now().minusMinutes(timeMinutes)))
                .eq(DeskBooking::getStatus, BookingStatus.ACTIVE)
                .ge(DeskBooking::getStartTime, DateUtils.toDate(LocalDateTime.now().minusHours(24)))
                .set(DeskBooking::getStatus, BookingStatus.EXPIRE)
                .set(BaseEntity::getRemark, "系统检测超时，自动过期"));
    }
}
