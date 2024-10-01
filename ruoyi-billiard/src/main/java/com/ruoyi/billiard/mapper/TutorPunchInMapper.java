package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.TutorPunchIn;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教练打卡Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-01
 */
@Mapper
public interface TutorPunchInMapper extends MyBaseMapper<TutorPunchIn>
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
     * 删除教练打卡
     * 
     * @param tutorPunchInId 教练打卡主键
     * @return 结果
     */
    public int deleteTutorPunchInByTutorPunchInId(Long tutorPunchInId);

    /**
     * 批量删除教练打卡
     * 
     * @param tutorPunchInIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTutorPunchInByTutorPunchInIds(Long[] tutorPunchInIds);
}
