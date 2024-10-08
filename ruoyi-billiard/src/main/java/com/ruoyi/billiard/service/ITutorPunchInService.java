package com.ruoyi.billiard.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import  com.ruoyi.billiard.domain.TutorPunchIn;

/**
 * 教练打卡Service接口
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
public interface ITutorPunchInService  extends IService<TutorPunchIn>
{
    /**
     * 查询教练打卡
     * 
     * @param tutorPunchInId 教练打卡主键
     * @return 教练打卡
     */
    public TutorPunchIn selectTutorPunchInByTutorPunchInId(Long tutorPunchInId);

    /**
     * 查询教练打卡列表
     * 
     * @param tutorPunchIn 教练打卡
     * @return 教练打卡集合
     */
    public List<TutorPunchIn> selectTutorPunchInList(TutorPunchIn tutorPunchIn);

    /**
     * 新增教练打卡
     * 
     * @param tutorPunchIn 教练打卡
     * @return 结果
     */
    public int insertTutorPunchIn(TutorPunchIn tutorPunchIn);

    /**
     * 修改教练打卡
     * 
     * @param tutorPunchIn 教练打卡
     * @return 结果
     */
    public int updateTutorPunchIn(TutorPunchIn tutorPunchIn);

    /**
     * 批量删除教练打卡
     * 
     * @param tutorPunchInIds 需要删除的教练打卡主键集合
     * @return 结果
     */
    public int deleteTutorPunchInByTutorPunchInIds(Long[] tutorPunchInIds);

    /**
     * 删除教练打卡信息
     * 
     * @param tutorPunchInId 教练打卡主键
     * @return 结果
     */
    public int deleteTutorPunchInByTutorPunchInId(Long tutorPunchInId);

    Boolean punchIn(Long storeId , Long tutorId, LocalDate  scheduleTime, LocalDateTime time);


    List<TutorPunchIn> queryCurrentPunchIn(Long storeId, LocalDate scheduleDay );
}
