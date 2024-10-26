package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FjxUserFeedback;

/**
 * 用户反馈信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
public interface FjxUserFeedbackMapper 
{
    /**
     * 查询用户反馈信息
     * 
     * @param id 用户反馈信息主键
     * @return 用户反馈信息
     */
    public FjxUserFeedback selectFjxUserFeedbackById(String id);

    /**
     * 查询用户反馈信息列表
     * 
     * @param fjxUserFeedback 用户反馈信息
     * @return 用户反馈信息集合
     */
    public List<FjxUserFeedback> selectFjxUserFeedbackList(FjxUserFeedback fjxUserFeedback);

    /**
     * 新增用户反馈信息
     * 
     * @param fjxUserFeedback 用户反馈信息
     * @return 结果
     */
    public int insertFjxUserFeedback(FjxUserFeedback fjxUserFeedback);

    /**
     * 修改用户反馈信息
     * 
     * @param fjxUserFeedback 用户反馈信息
     * @return 结果
     */
    public int updateFjxUserFeedback(FjxUserFeedback fjxUserFeedback);

    /**
     * 删除用户反馈信息
     * 
     * @param id 用户反馈信息主键
     * @return 结果
     */
    public int deleteFjxUserFeedbackById(String id);

    /**
     * 批量删除用户反馈信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFjxUserFeedbackByIds(String[] ids);
}
