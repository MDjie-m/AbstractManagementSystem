package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.DeskBooking;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 球桌预约Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@Mapper
public interface DeskBookingMapper extends MyBaseMapper<DeskBooking>
{
    /**
     * 查询球桌预约
     * 
     * @param deskBookingId 球桌预约主键
     * @return 球桌预约
     */
    public DeskBooking selectDeskBookingByDeskBookingId(Long deskBookingId);

    /**
     * 查询球桌预约列表
     * 
     * @param deskBooking 球桌预约
     * @return 球桌预约集合
     */
    public List<DeskBooking> selectDeskBookingList(DeskBooking deskBooking);


    /**
     * 删除球桌预约
     * 
     * @param deskBookingId 球桌预约主键
     * @return 结果
     */
    public int deleteDeskBookingByDeskBookingId(Long deskBookingId);

    /**
     * 批量删除球桌预约
     * 
     * @param deskBookingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeskBookingByDeskBookingIds(Long[] deskBookingIds);
}
