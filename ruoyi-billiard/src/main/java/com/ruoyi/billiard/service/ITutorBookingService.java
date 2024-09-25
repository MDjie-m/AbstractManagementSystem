package com.ruoyi.billiard.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.TutorBooking;

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
    public int insertTutorBooking(TutorBooking tutorBooking);

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
}
