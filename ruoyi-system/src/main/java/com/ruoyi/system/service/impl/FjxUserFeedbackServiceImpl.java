package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FjxUserFeedbackMapper;
import com.ruoyi.system.domain.FjxUserFeedback;
import com.ruoyi.system.service.IFjxUserFeedbackService;

/**
 * 用户反馈信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
@Service
public class FjxUserFeedbackServiceImpl implements IFjxUserFeedbackService 
{
    @Autowired
    private FjxUserFeedbackMapper fjxUserFeedbackMapper;

    /**
     * 查询用户反馈信息
     * 
     * @param id 用户反馈信息主键
     * @return 用户反馈信息
     */
    @Override
    public FjxUserFeedback selectFjxUserFeedbackById(String id)
    {
        return fjxUserFeedbackMapper.selectFjxUserFeedbackById(id);
    }

    /**
     * 查询用户反馈信息列表
     * 
     * @param fjxUserFeedback 用户反馈信息
     * @return 用户反馈信息
     */
    @Override
    public List<FjxUserFeedback> selectFjxUserFeedbackList(FjxUserFeedback fjxUserFeedback)
    {
        return fjxUserFeedbackMapper.selectFjxUserFeedbackList(fjxUserFeedback);
    }

    /**
     * 新增用户反馈信息
     * 
     * @param fjxUserFeedback 用户反馈信息
     * @return 结果
     */
    @Override
    public int insertFjxUserFeedback(FjxUserFeedback fjxUserFeedback)
    {
        return fjxUserFeedbackMapper.insertFjxUserFeedback(fjxUserFeedback);
    }

    /**
     * 修改用户反馈信息
     * 
     * @param fjxUserFeedback 用户反馈信息
     * @return 结果
     */
    @Override
    public int updateFjxUserFeedback(FjxUserFeedback fjxUserFeedback)
    {
        return fjxUserFeedbackMapper.updateFjxUserFeedback(fjxUserFeedback);
    }

    /**
     * 批量删除用户反馈信息
     * 
     * @param ids 需要删除的用户反馈信息主键
     * @return 结果
     */
    @Override
    public int deleteFjxUserFeedbackByIds(String[] ids)
    {
        return fjxUserFeedbackMapper.deleteFjxUserFeedbackByIds(ids);
    }

    /**
     * 删除用户反馈信息信息
     * 
     * @param id 用户反馈信息主键
     * @return 结果
     */
    @Override
    public int deleteFjxUserFeedbackById(String id)
    {
        return fjxUserFeedbackMapper.deleteFjxUserFeedbackById(id);
    }
}
