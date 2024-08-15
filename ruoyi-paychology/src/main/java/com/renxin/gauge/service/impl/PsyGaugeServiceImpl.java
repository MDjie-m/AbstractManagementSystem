package com.renxin.gauge.service.impl;

import java.util.Date;
import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.domain.*;
import com.renxin.gauge.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.gauge.service.IPsyGaugeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 心理测评Service业务层处理
 * 
 * @author renxin
 * @date 2022-08-30
 */
@Service
public class PsyGaugeServiceImpl implements IPsyGaugeService 
{
    @Autowired
    private PsyGaugeMapper psyGaugeMapper;

    @Autowired
    private PsyGaugeQuestionsMapper psyGaugeQuestionsMapper;

    @Autowired
    private PsyGaugeQuestionsOptionsMapper psyGaugeQuestionsOptionsMapper;

    @Autowired
    private PsyGaugeMultiSettingMapper psyGaugeMultiSettingMapper;

    @Autowired
    private PsyGaugeScoreSettingMapper psyGaugeScoreSettingMapper;

    /**
     * 查询心理测评
     * 
     * @param id 心理测评主键
     * @return 心理测评
     */
    @Override
    public PsyGauge selectPsyGaugeById(Long id)
    {
        return psyGaugeMapper.selectPsyGaugeById(id);
    }

    /**
     * 查询心理测评列表
     * 
     * @param psyGauge 心理测评
     * @return 心理测评
     */
    @Override
    public List<PsyGauge> selectPsyGaugeList(PsyGauge psyGauge)
    {
        return psyGaugeMapper.selectPsyGaugeList(psyGauge);
    }

    /**
     * 新增心理测评
     * 
     * @param psyGauge 心理测评
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPsyGauge(PsyGauge psyGauge)
    {

        Date date=DateUtils.getNowDate();
        psyGauge.setCreateTime(date);
        int result= psyGaugeMapper.insertPsyGauge(psyGauge);

        //新增问题
        PsyGaugeQuestions psyGaugeQuestions=new PsyGaugeQuestions();
        psyGaugeQuestions.setGaugeId(psyGauge.getId());
        psyGaugeQuestions.setNo(1);
        psyGaugeQuestions.setTitle("默认问题");
        psyGaugeQuestions.setCreateTime(date);
        psyGaugeQuestions.setCreateBy(psyGauge.getCreateBy());
        psyGaugeQuestionsMapper.insertPsyGaugeQuestions(psyGaugeQuestions);
        //新增问题选项
        PsyGaugeQuestionsOptions psyGaugeQuestionsOptions = getPsyGaugeQuestionsOptions(psyGauge, date, psyGaugeQuestions);

        psyGaugeQuestionsOptions.setId(null);
        psyGaugeQuestionsOptions.setName("默认选项2");
        psyGaugeQuestionsOptions.setSort(1);
        psyGaugeQuestionsOptionsMapper.insertPsyGaugeQuestionsOptions(psyGaugeQuestionsOptions);


        PsyGaugeScoreSetting psyGaugeScoreSetting = getPsyGaugeScoreSetting(psyGauge);

        if(psyGauge.getType()==2){

            PsyGaugeMultiSetting psyGaugeMultiSetting=new PsyGaugeMultiSetting();
            psyGaugeMultiSetting.setGaugeId(psyGauge.getId());
            psyGaugeMultiSetting.setName("多维度1");
            psyGaugeMultiSetting.setQuestionIds(psyGaugeQuestions.getId().toString());
            psyGaugeMultiSetting.setScoreSettingIds(psyGaugeScoreSetting.getId().toString());
            psyGaugeMultiSetting.setCreateTime(date);
            psyGaugeMultiSetting.setCreateBy(psyGauge.getCreateBy());
            psyGaugeMultiSettingMapper.insertPsyGaugeMultiSetting(psyGaugeMultiSetting);

        }


        return result;
    }

    private PsyGaugeScoreSetting getPsyGaugeScoreSetting(PsyGauge psyGauge) {
        PsyGaugeScoreSetting psyGaugeScoreSetting=new PsyGaugeScoreSetting();
        psyGaugeScoreSetting.setGaugeId(psyGauge.getId());
        psyGaugeScoreSetting.setStart(1);
        psyGaugeScoreSetting.setEnd(20);
        psyGaugeScoreSetting.setProposal("");
        psyGaugeScoreSettingMapper.insertPsyGaugeScoreSetting(psyGaugeScoreSetting);
        return psyGaugeScoreSetting;
    }

    private PsyGaugeQuestionsOptions getPsyGaugeQuestionsOptions(PsyGauge psyGauge, Date date, PsyGaugeQuestions psyGaugeQuestions) {
        PsyGaugeQuestionsOptions psyGaugeQuestionsOptions=new PsyGaugeQuestionsOptions();
        psyGaugeQuestionsOptions.setGaugeQuestionsId(psyGaugeQuestions.getId());
        psyGaugeQuestionsOptions.setName("默认选项1");
        psyGaugeQuestionsOptions.setValue(1);
        psyGaugeQuestionsOptions.setSort(1);
        psyGaugeQuestionsOptions.setCreateTime(date);
        psyGaugeQuestionsOptions.setCreateBy(psyGauge.getCreateBy());
        psyGaugeQuestionsOptionsMapper.insertPsyGaugeQuestionsOptions(psyGaugeQuestionsOptions);
        return psyGaugeQuestionsOptions;
    }

    /**
     * 修改心理测评
     * 
     * @param psyGauge 心理测评
     * @return 结果
     */
    @Override
    public int updatePsyGauge(PsyGauge psyGauge)
    {
        psyGauge.setUpdateTime(DateUtils.getNowDate());
        return psyGaugeMapper.updatePsyGauge(psyGauge);
    }

    /**
     * 批量删除心理测评
     * 
     * @param ids 需要删除的心理测评主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeByIds(Long[] ids)
    {
        return psyGaugeMapper.deletePsyGaugeByIds(ids);
    }

    /**
     * 删除心理测评信息
     * 
     * @param id 心理测评主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeById(Long id)
    {
        return psyGaugeMapper.deletePsyGaugeById(id);
    }
}
