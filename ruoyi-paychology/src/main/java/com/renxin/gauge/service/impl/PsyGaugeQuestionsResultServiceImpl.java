package com.renxin.gauge.service.impl;

import com.renxin.common.core.domain.dto.GaugeCommitResultDTO;
import com.renxin.common.enums.GaugeStatus;
import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.*;
import com.renxin.gauge.mapper.PsyGaugeQuestionsOptionsMapper;
import com.renxin.gauge.mapper.PsyGaugeQuestionsResultMapper;
import com.renxin.gauge.mapper.PsyGaugeScoreSettingMapper;
import com.renxin.gauge.mapper.PsyOrderMapper;
import com.renxin.gauge.service.IPsyGaugeQuestionsResultService;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.gauge.vo.GaugeReportVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 心理测评问题结果Service业务层处理
 * 
 * @author renxin
 * @date 2022-09-10
 */
@Service
public class PsyGaugeQuestionsResultServiceImpl implements IPsyGaugeQuestionsResultService 
{
    @Resource
    private PsyGaugeQuestionsResultMapper psyGaugeQuestionsResultMapper;

    @Resource
    private PsyGaugeQuestionsOptionsMapper psyGaugeQuestionsOptionsMapper;

    @Resource
    private PsyOrderMapper psyOrderMapper;

    @Resource
    private IPsyGaugeService psyGaugeService;

    @Resource
    private PsyGaugeScoreSettingMapper psyGaugeScoreSettingMapper;

    /**
     * 查询心理测评问题结果
     * 
     * @param id 心理测评问题结果主键
     * @return 心理测评问题结果
     */
    @Override
    public PsyGaugeQuestionsResult selectPsyGaugeQuestionsResultById(Long id)
    {
        return psyGaugeQuestionsResultMapper.selectPsyGaugeQuestionsResultById(id);
    }

    /**
     * 查询心理测评问题结果列表
     * 
     * @param psyGaugeQuestionsResult 心理测评问题结果
     * @return 心理测评问题结果
     */
    @Override
    public List<PsyGaugeQuestionsResult> selectPsyGaugeQuestionsResultList(PsyGaugeQuestionsResult psyGaugeQuestionsResult)
    {
        return psyGaugeQuestionsResultMapper.selectPsyGaugeQuestionsResultList(psyGaugeQuestionsResult);
    }

    /**
     * 新增心理测评问题结果
     * 
     * @param psyGaugeQuestionsResult 心理测评问题结果
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int answer(PsyGaugeQuestionsResult psyGaugeQuestionsResult ,Long userId)
    {
        //先删除该问题的答案
        psyGaugeQuestionsResult.setUserId(userId);
        psyGaugeQuestionsResultMapper.deleteResult(psyGaugeQuestionsResult);

        //查询问题分数，进行数据绑定，插入数据
        List<PsyGaugeQuestionsOptions> psyGaugeQuestionsOptions = psyGaugeQuestionsOptionsMapper.queryOptionsByIds(psyGaugeQuestionsResult.getQuestionsOptionsIdList());
//        Map<Integer, Integer> collect = psyGaugeQuestionsOptions.stream().collect(Collectors.toMap(PsyGaugeQuestionsOptions::getId, PsyGaugeQuestionsOptions::getValue));

        List<PsyGaugeQuestionsResult> results = Lists.newArrayList();
        for (PsyGaugeQuestionsOptions item : psyGaugeQuestionsOptions) {
            PsyGaugeQuestionsResult build = PsyGaugeQuestionsResult.builder()
                    .gaugeId(psyGaugeQuestionsResult.getGaugeId())
                    .questionsId(psyGaugeQuestionsResult.getQuestionsId())
                    .questionsOptionsId(item.getId())
                    .score(item.getValue())
                    .lat(item.getLat())
                    .questionsLat(psyGaugeQuestionsResult.getQuestionsLat())
                    .userId(userId)
                    .orderId(psyGaugeQuestionsResult.getOrderId())
                    .build();
            build.setCreateTime(DateUtils.getNowDate());
            results.add(build);
        }
        return psyGaugeQuestionsResultMapper.batchInsert(results);
    }

    /**
     * 修改心理测评问题结果
     * 
     * @param psyGaugeQuestionsResult 心理测评问题结果
     * @return 结果
     */
    @Override
    public int updatePsyGaugeQuestionsResult(PsyGaugeQuestionsResult psyGaugeQuestionsResult)
    {
        return psyGaugeQuestionsResultMapper.updatePsyGaugeQuestionsResult(psyGaugeQuestionsResult);
    }

    /**
     * 批量删除心理测评问题结果
     * 
     * @param ids 需要删除的心理测评问题结果主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeQuestionsResultByIds(Long[] ids)
    {
        return psyGaugeQuestionsResultMapper.deletePsyGaugeQuestionsResultByIds(ids);
    }

    /**
     * 删除心理测评问题结果信息
     * 
     * @param id 心理测评问题结果主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeQuestionsResultById(Long id)
    {
        return psyGaugeQuestionsResultMapper.deletePsyGaugeQuestionsResultById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String commitResult(GaugeCommitResultDTO gaugeCommitResultDTO ,Long userId) {
        //获取订单分值
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId",gaugeCommitResultDTO.getOrderId());
        paramMap.put("userId",userId);
        PsyOrder order = psyOrderMapper.selectPsyOrderById(gaugeCommitResultDTO.getOrderId());
//        PsyGauge gauge = psyGaugeService.selectPsyGaugeById(order.getGaugeId());
        String sum = "0";
        switch (order.getGaugeType()) {
            case GaugeConstant.GAUGE_COMPUTE_3:
                // MBTI
                List<String> list = psyGaugeQuestionsResultMapper.getQuestionLat(paramMap);
                HashMap<String, Integer> countMap = new HashMap<>();
                for (String num : list) {
                    if (countMap.containsKey(num)) {
                        int count = countMap.get(num);
                        count++;
                        countMap.put(num, count);
                    } else {
                        countMap.put(num, 1);
                    }
                }

                StringBuilder sb = new StringBuilder();
                sb.append(countMap.get("E") > countMap.get("I") ? "E" : "I");
                sb.append(countMap.get("S") > countMap.get("N") ? "S" : "N");
                sb.append(countMap.get("T") > countMap.get("F") ? "T" : "F");
                sb.append(countMap.get("J") > countMap.get("P") ? "J" : "P");
                sum = sb.toString();
                break;
            default:
                int score = psyGaugeQuestionsResultMapper.getQuestionScore(paramMap);
                score = (int) Math.round(score * order.getGaugeRatio().doubleValue());

                if (GaugeConstant.GAUGE_COMPUTE_8 == order.getGaugeType()) {
                    score = (int) Math.round(score * 100.0 / order.getGaugeNum() + 50);
                }
                sum = score + "";
                break;
        }
        paramMap.put("score",sum);
        //获取当前得分匹配结果
        PsyGaugeScoreSetting psyGaugeScoreSetting = psyGaugeScoreSettingMapper.selectPsyGaugeScoreSettingByGaugeId(paramMap);
        // 测评完成更新测评状态
        PsyOrder psyOrder = new PsyOrder();
        psyOrder.setUserId(userId);
        psyOrder.setId(gaugeCommitResultDTO.getOrderId());
        psyOrder.setGaugeStatus(GaugeStatus.FINISHED.getValue());
        psyOrder.setMobile(gaugeCommitResultDTO.getMobile());
        psyOrder.setAge(gaugeCommitResultDTO.getAge());
        psyOrder.setSex(gaugeCommitResultDTO.getSex());
        psyOrder.setScore(sum);

        if(psyGaugeScoreSetting!=null){
            //将该订单答题情况改为已完成
            psyOrder.setResultUrl(psyGaugeScoreSetting.getProposal());
        }

        psyOrderMapper.updatePsyOrder(psyOrder);
        return "ok";
    }

    /**
     * 新增心理测评问题结果
     *
     * @param psyGaugeQuestionsResultAlls 心理测评问题结果
     * @return 结果
     */

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addList(List<PsyGaugeQuestionsResultAll> psyGaugeQuestionsResultAlls) {
        if(CollectionUtils.isNotEmpty(psyGaugeQuestionsResultAlls)){
            //先删除当前订单所有问题的答案
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("orderId",psyGaugeQuestionsResultAlls.get(0).getOrderId());
            paramMap.put("userId",psyGaugeQuestionsResultAlls.get(0).getUserId());
            //psyGaugeQuestionsResultMapper.deleteAllResult(paramMap);
            List<PsyGaugeQuestionsResultAll> results = Lists.newArrayList();
            int sum=0;
            for (PsyGaugeQuestionsResultAll psyGaugeQuestionsResultAll:psyGaugeQuestionsResultAlls) {
                sum+=psyGaugeQuestionsResultAll.getValue();
                psyGaugeQuestionsResultAll.setCreateTime(DateUtils.getNowDate());
                psyGaugeQuestionsResultAll.setUserId(psyGaugeQuestionsResultAlls.get(0).getUserId());
                results.add(psyGaugeQuestionsResultAll);
            }
            paramMap.put("score",sum);
            //获取当前得分匹配结果
            PsyGaugeScoreSetting psyGaugeScoreSetting = psyGaugeScoreSettingMapper.selectPsyGaugeScoreSettingByGaugeId(paramMap);
            if(psyGaugeScoreSetting!=null){
                paramMap.put("proposal",psyGaugeScoreSetting.getProposal());
                //将该订单答题结果同步到订单表
                psyOrderMapper.updatePsyOrderByOrderId(paramMap);
            }
            //保存订单选项及结果
            //return psyGaugeQuestionsResultMapper.batchAllInsert(results);
        }
        return 0;
    }

    @Override
    public GaugeReportVO getReport(String orderId) {
        GaugeReportVO vo = new GaugeReportVO();
        PsyOrder psyOrder = psyOrderMapper.selectPsyOrderByOrderId(orderId);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId",psyOrder.getId());
        paramMap.put("userId",psyOrder.getUserId());

        if (GaugeConstant.GAUGE_COMPUTE_3 == psyOrder.getGaugeType()) {
            vo.setLats(psyGaugeQuestionsResultMapper.getQuestionLat(paramMap));
        }

        List<Integer> asList = Arrays.asList(GaugeConstant.GAUGE_COMPUTE_2, GaugeConstant.GAUGE_COMPUTE_8);

        if (asList.contains(psyOrder.getGaugeType())) {
            PsyGaugeQuestionsResult query = new PsyGaugeQuestionsResult();
            query.setOrderId(psyOrder.getId());
            query.setUserId(psyOrder.getUserId());
            vo.setResults(selectPsyGaugeQuestionsResultList(query));
        }

        paramMap.put("score",psyOrder.getScore());
        vo.setOrder(psyOrder);
        vo.setSetting(psyGaugeScoreSettingMapper.selectPsyGaugeScoreSettingByGaugeId(paramMap));
        return vo;
    }
}
