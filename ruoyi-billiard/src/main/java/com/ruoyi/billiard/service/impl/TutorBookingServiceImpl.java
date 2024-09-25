package com.ruoyi.billiard.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.TutorBookingMapper;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.TutorBooking;
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
    public int insertTutorBooking(TutorBooking tutorBooking)
    {
        SecurityUtils.fillCreateUser(tutorBooking);
        tutorBooking.setTutorBookingId(IdUtils.singleNextId());
        return baseMapper.insert(tutorBooking);
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
}
