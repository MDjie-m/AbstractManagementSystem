package com.ruoyi.billiard.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.TutorBooking;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import org.apache.ibatis.annotations.Param;

/**
 * 教练预约Service接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
public interface ITutorBookingService  extends IService<TutorBooking>
{
    /**
     * 查询教练预约
     * 
     * @param tutorBookingId 教练预约主键
     * @return 教练预约
     */
    public TutorBooking selectTutorBookingByTutorBookingId(Long tutorBookingId);

    /**
     * 查询教练预约列表
     * 
     * @param tutorBooking 教练预约
     * @return 教练预约集合
     */
    public List<TutorBooking> selectTutorBookingList(TutorBooking tutorBooking);

    /**
     * 新增教练预约
     * 
     * @param tutorBooking 教练预约
     * @return 结果
     */
    public TutorBooking insertTutorBooking(TutorBooking tutorBooking);

    /**
     * 修改教练预约
     * 
     * @param tutorBooking 教练预约
     * @return 结果
     */
    public int updateTutorBooking(TutorBooking tutorBooking);

    /**
     * 批量删除教练预约
     * 
     * @param tutorBookingIds 需要删除的教练预约主键集合
     * @return 结果
     */
    public int deleteTutorBookingByTutorBookingIds(Long[] tutorBookingIds);

    /**
     * 删除教练预约信息
     * 
     * @param tutorBookingId 教练预约主键
     * @return 结果
     */
    public int deleteTutorBookingByTutorBookingId(Long tutorBookingId);

    List<KeyValueVo<Long,Long>> selectBookingCount(@Param("ids") List<Long> ids,
                                             @Param("startTime")  Date startTime, @Param("endTime")Date endTime);

    Map<String, List<TutorBooking>> selectBookingDayMap(TutorBooking reqVo);

    Boolean verifyBooking(Long bookingId, Long storeId);

    List<TutorBooking> queryLastBooking(List<Long> tutorIds);

    void checkBookingExpire(Integer time);
}