package com.ruoyi.billiard.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.billiard.domain.DeskBooking;
import com.ruoyi.common.core.domain.model.KeyValueVo;

/**
 * 球桌预约Service接口
 *
 * @author ruoyi
 * @date 2024-09-25
 */
public interface IDeskBookingService extends IService<DeskBooking> {
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
     * 新增球桌预约
     *
     * @param deskBooking 球桌预约
     * @return 结果
     */
    public DeskBooking insertDeskBooking(DeskBooking deskBooking);

    /**
     * 修改球桌预约
     *
     * @param deskBooking 球桌预约
     * @return 结果
     */
    public int updateDeskBooking(DeskBooking deskBooking);

    /**
     * 批量删除球桌预约
     *
     * @param deskBookingIds 需要删除的球桌预约主键集合
     * @return 结果
     */
    public int deleteDeskBookingByDeskBookingIds(Long[] deskBookingIds);

    /**
     * 删除球桌预约信息
     *
     * @param deskBookingId 球桌预约主键
     * @return 结果
     */
    public int deleteDeskBookingByDeskBookingId(Long deskBookingId);

    Map<String, List<DeskBooking>> selectBookingDayMap(DeskBooking booking);

    void checkBookingExpire(Integer timeMinutes);

    List<KeyValueVo<Long, Long>> selectBookingCount(List<Long> ids);
}
