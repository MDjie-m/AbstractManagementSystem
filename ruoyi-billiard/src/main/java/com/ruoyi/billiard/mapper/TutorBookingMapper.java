package com.ruoyi.billiard.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.billiard.domain.TutorBooking;
import com.ruoyi.common.core.domain.model.KeyValueVo;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 教练预约Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@Mapper
public interface TutorBookingMapper extends MyBaseMapper<TutorBooking>
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
     * 删除教练预约
     * 
     * @param tutorBookingId 教练预约主键
     * @return 结果
     */
    public int deleteTutorBookingByTutorBookingId(Long tutorBookingId);

    /**
     * 批量删除教练预约
     * 
     * @param tutorBookingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTutorBookingByTutorBookingIds(Long[] tutorBookingIds);

    List<KeyValueVo<Long,Long>> selectBookingCount(@Param("ids") List<Long> ids,
                                             @Param("startTime")  Date startTime, @Param("endTime")Date endTime);

    List<Long> queryLastBooking(@Param("time") Date time, @Param("ids") List<Long> tutorIds);
}
