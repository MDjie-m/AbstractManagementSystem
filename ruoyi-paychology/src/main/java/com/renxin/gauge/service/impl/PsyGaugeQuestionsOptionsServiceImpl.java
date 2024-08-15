package com.renxin.gauge.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.gauge.mapper.PsyGaugeQuestionsOptionsMapper;
import com.renxin.gauge.domain.PsyGaugeQuestionsOptions;
import com.renxin.gauge.service.IPsyGaugeQuestionsOptionsService;

/**
 * 心理测评问题选项Service业务层处理
 * 
 * @author renxin
 * @date 2022-09-06
 */
@Service
public class PsyGaugeQuestionsOptionsServiceImpl implements IPsyGaugeQuestionsOptionsService 
{
    @Autowired
    private PsyGaugeQuestionsOptionsMapper psyGaugeQuestionsOptionsMapper;

    /**
     * 查询心理测评问题选项
     * 
     * @param id 心理测评问题选项主键
     * @return 心理测评问题选项
     */
    @Override
    public PsyGaugeQuestionsOptions selectPsyGaugeQuestionsOptionsById(Long id)
    {
        return psyGaugeQuestionsOptionsMapper.selectPsyGaugeQuestionsOptionsById(id);
    }

    /**
     * 查询心理测评问题选项列表
     * 
     * @param psyGaugeQuestionsOptions 心理测评问题选项
     * @return 心理测评问题选项
     */
    @Override
    public List<PsyGaugeQuestionsOptions> selectPsyGaugeQuestionsOptionsList(PsyGaugeQuestionsOptions psyGaugeQuestionsOptions)
    {
        return psyGaugeQuestionsOptionsMapper.selectPsyGaugeQuestionsOptionsList(psyGaugeQuestionsOptions);
    }

    /**
     * 新增心理测评问题选项
     * 
     * @param psyGaugeQuestionsOptions 心理测评问题选项
     * @return 结果
     */
    @Override
    public int insertPsyGaugeQuestionsOptions(PsyGaugeQuestionsOptions psyGaugeQuestionsOptions)
    {
        psyGaugeQuestionsOptions.setCreateTime(DateUtils.getNowDate());
        return psyGaugeQuestionsOptionsMapper.insertPsyGaugeQuestionsOptions(psyGaugeQuestionsOptions);
    }

    /**
     * 修改心理测评问题选项
     * 
     * @param psyGaugeQuestionsOptions 心理测评问题选项
     * @return 结果
     */
    @Override
    public int updatePsyGaugeQuestionsOptions(PsyGaugeQuestionsOptions psyGaugeQuestionsOptions)
    {
        return psyGaugeQuestionsOptionsMapper.updatePsyGaugeQuestionsOptions(psyGaugeQuestionsOptions);
    }

    /**
     * 批量删除心理测评问题选项
     * 
     * @param ids 需要删除的心理测评问题选项主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeQuestionsOptionsByIds(Long[] ids)
    {
        return psyGaugeQuestionsOptionsMapper.deletePsyGaugeQuestionsOptionsByIds(ids);
    }

    /**
     * 删除心理测评问题选项信息
     * 
     * @param id 心理测评问题选项主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeQuestionsOptionsById(Long id)
    {
        return psyGaugeQuestionsOptionsMapper.deletePsyGaugeQuestionsOptionsById(id);
    }
}
