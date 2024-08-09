package com.renxin.psychology.service.impl;


import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyUserLabel;
import com.renxin.psychology.mapper.PsyUserLabelMapper;
import com.renxin.psychology.service.IPsyUserLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户标签Service业务层处理
 * 
 * @author renxin
 * @date 2024-08-09
 */
@Service
public class PsyUserLabelServiceImpl implements IPsyUserLabelService 
{
    @Autowired
    private PsyUserLabelMapper psyUserLabelMapper;

    /**
     * 查询用户标签
     * 
     * @param id 用户标签主键
     * @return 用户标签
     */
    @Override
    public PsyUserLabel selectPsyUserLabelById(Long id)
    {
        return psyUserLabelMapper.selectPsyUserLabelById(id);
    }

    /**
     * 查询用户标签列表
     * 
     * @param psyUserLabel 用户标签
     * @return 用户标签
     */
    @Override
    public List<PsyUserLabel> selectPsyUserLabelList(PsyUserLabel psyUserLabel)
    {
        return psyUserLabelMapper.selectPsyUserLabelList(psyUserLabel);
    }

    /**
     * 新增用户标签
     * 
     * @param psyUserLabel 用户标签
     * @return 结果
     */
    @Override
    public int insertPsyUserLabel(PsyUserLabel psyUserLabel)
    {
        psyUserLabel.setCreateTime(DateUtils.getNowDate());
        return psyUserLabelMapper.insertPsyUserLabel(psyUserLabel);
    }

    /**
     * 修改用户标签
     * 
     * @param psyUserLabel 用户标签
     * @return 结果
     */
    @Override
    public int updatePsyUserLabel(PsyUserLabel psyUserLabel)
    {
        psyUserLabel.setUpdateTime(DateUtils.getNowDate());
        return psyUserLabelMapper.updatePsyUserLabel(psyUserLabel);
    }

    /**
     * 批量删除用户标签
     * 
     * @param ids 需要删除的用户标签主键
     * @return 结果
     */
    @Override
    public int deletePsyUserLabelByIds(Long[] ids)
    {
        return psyUserLabelMapper.deletePsyUserLabelByIds(ids);
    }

    /**
     * 删除用户标签信息
     * 
     * @param id 用户标签主键
     * @return 结果
     */
    @Override
    public int deletePsyUserLabelById(Long id)
    {
        return psyUserLabelMapper.deletePsyUserLabelById(id);
    }
}
