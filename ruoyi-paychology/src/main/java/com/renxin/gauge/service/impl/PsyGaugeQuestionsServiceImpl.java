package com.renxin.gauge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.domain.PsyGaugeQuestions;
import com.renxin.gauge.domain.PsyGaugeQuestionsOptions;
import com.renxin.gauge.domain.PsyGaugeQuestionsResult;
import com.renxin.gauge.mapper.PsyGaugeQuestionsMapper;
import com.renxin.gauge.mapper.PsyGaugeQuestionsResultMapper;
import com.renxin.gauge.service.IPsyGaugeQuestionsOptionsService;
import com.renxin.gauge.service.IPsyGaugeQuestionsService;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.gauge.vo.PsyQuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 心理测评问题Service业务层处理
 *
 * @author renxin
 * @date 2022-09-06
 */
@Service
@Slf4j
public class PsyGaugeQuestionsServiceImpl extends ServiceImpl<PsyGaugeQuestionsMapper, PsyGaugeQuestions>
        implements IPsyGaugeQuestionsService {

    @Autowired
    private IPsyGaugeQuestionsService self; // 注入自身

    @Resource
    private RedisCache redisCache;

    @Resource
    private IPsyGaugeQuestionsOptionsService questionsOptionsService;

    @Resource
    private PsyGaugeQuestionsResultMapper psyGaugeQuestionsResultMapper;

    @Resource
    private PsyGaugeQuestionsMapper psyGaugeQuestionsMapper;

    /**
     * 查询心理测评问题
     *
     * @param id 心理测评问题主键
     * @return 心理测评问题
     */
    @Override
    @Cacheable(value = CacheConstants.QUESTION_BY_ID_KEY, key = "#id", unless = "#result == null")
    public PsyGaugeQuestions selectPsyGaugeQuestionsById(Long id) {
        log.info( java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "--------------------------------连接MySQL查询问题:" + id);
        PsyGaugeQuestions question = psyGaugeQuestionsMapper.selectPsyGaugeQuestionsById(id);

        //查询选项清单
        PsyGaugeQuestionsOptions optionReq = new PsyGaugeQuestionsOptions();
            optionReq.setGaugeQuestionsId(id);
        List<PsyGaugeQuestionsOptions> optionList = questionsOptionsService.selectPsyGaugeQuestionsOptionsList(optionReq);

        question.setOptionList(optionList);
        return question;
    }

    /**
     * 查询心理测评问题列表
     *
     * @param psyGaugeQuestions 心理测评问题
     * @return 心理测评问题
     */
    @Override
    public List<PsyGaugeQuestions> selectPsyGaugeQuestionsList(PsyGaugeQuestions psyGaugeQuestions) {
        List<PsyGaugeQuestions> questionsList = psyGaugeQuestionsMapper.selectPsyGaugeQuestionsList(psyGaugeQuestions);
        return questionsList;
    }

    /**
     * 新增心理测评问题
     *
     * @param psyGaugeQuestions 心理测评问题
     * @return 结果
     */
    @Override
    public int insertPsyGaugeQuestions(PsyGaugeQuestions psyGaugeQuestions) {
        psyGaugeQuestions.setCreateTime(DateUtils.getNowDate());
        return psyGaugeQuestionsMapper.insertPsyGaugeQuestions(psyGaugeQuestions);
    }

    /**
     * 修改心理测评问题
     *
     * @param psyGaugeQuestions 心理测评问题
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.QUESTION_BY_ID_KEY, key = "#psyGaugeQuestions.id")
    public int updatePsyGaugeQuestions(PsyGaugeQuestions psyGaugeQuestions) {
        psyGaugeQuestions.setUpdateTime(DateUtils.getNowDate());
        int i = psyGaugeQuestionsMapper.updatePsyGaugeQuestions(psyGaugeQuestions);
        
        refreshIdList();
        return i;
    }

    /**
     * 批量删除心理测评问题
     *
     * @param ids 需要删除的心理测评问题主键
     * @return 结果
     */
    @Override
    public int deletePsyGaugeQuestionsByIds(Long[] ids) {
        int i = psyGaugeQuestionsMapper.deletePsyGaugeQuestionsByIds(ids);
        //批量删除缓存
        redisCache.deleteMultiCache(CacheConstants.QUESTION_BY_ID_KEY, Arrays.asList(ids));
        refreshIdList();
        
        return i;
    }

    /**
     * 删除心理测评问题信息
     *
     * @param id 心理测评问题主键
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.QUESTION_BY_ID_KEY, key = "#id")
    public int deletePsyGaugeQuestionsById(Long id) {
        int i = psyGaugeQuestionsMapper.deletePsyGaugeQuestionsById(id);
        refreshIdList();
        return i;
    }

    @Override
    public List<PsyQuestionVO> appQueryQuesList(PsyGaugeQuestions psyGaugeQuestions) {

        List<PsyQuestionVO> psyQuestionVOS = psyGaugeQuestionsMapper.appQueryQuesList(psyGaugeQuestions);
        psyQuestionVOS.forEach(item ->
            item.setAnswers(
                item.getOptions().stream().filter(PsyGaugeQuestionsOptions::isSelectedFlag)
                        .map(PsyGaugeQuestionsOptions::getId).collect(Collectors.toList())
            )
        );
        return psyQuestionVOS;
    }

    @Override
    public List<PsyQuestionVO> wrongs(Long orderId, Long gaugeId) {
        PsyGaugeQuestions query = new PsyGaugeQuestions();
        PsyGaugeQuestionsResult queryResult = new PsyGaugeQuestionsResult();
        query.setGaugeId(gaugeId);
        query.setOrderId(orderId);
        queryResult.setGaugeId(gaugeId);
        queryResult.setOrderId(orderId);
        queryResult.setScore(0);

        List<PsyQuestionVO> questionList = psyGaugeQuestionsMapper.appQueryQuesList(query);
        List<PsyGaugeQuestionsResult> results = psyGaugeQuestionsResultMapper.selectPsyGaugeQuestionsResultList(queryResult);
        List<Integer> ids = results.stream().filter(i -> i.getScore() == 0).map(PsyGaugeQuestionsResult::getQuestionsId).collect(Collectors.toList());

        questionList = questionList.stream().filter(item -> ids.contains(item.getId())).collect(Collectors.toList());
        questionList.forEach(item -> {
            item.setAnswerTitle(item.getOptions().stream().filter(PsyGaugeQuestionsOptions::isSelectedFlag)
                    .map(PsyGaugeQuestionsOptions::getName).collect(Collectors.toList()));
            item.setReferenceAnswerTitle(item.getOptions().stream().filter(a -> a.getValue() > 0)
                    .map(PsyGaugeQuestionsOptions::getName).collect(Collectors.toList()));
        });

        return questionList;
    }

    //刷新缓存
    @Override
    public void refreshCacheByIdList(List<Long> idList){
        redisCache.deleteMultiCache(CacheConstants.QUESTION_BY_ID_KEY,idList);
        for (Long id : idList) {
            self.selectPsyGaugeQuestionsById(id);
        }
        refreshIdList();
    }

    @Override
    public void refreshCacheById(Long id){
        refreshCacheByIdList(Arrays.asList(id));
    }

    @Override
    public void refreshCacheAll(){
        //获取完整id清单
       /* List<Long> courseIdList = psyGaugeQuestionsMapper.selectList(new LambdaQueryWrapper<PsyGaugeQuestions>()
                .select(PsyGaugeQuestions::getId)
                .orderByDesc(PsyGaugeQuestions::getCreateTime)).stream().map(p -> p.getId()).collect(Collectors.toList());

        //刷新缓存
        refreshCacheByIdList(courseIdList);*/
        List<PsyGaugeQuestions> questionsList = psyGaugeQuestionsMapper.selectPsyGaugeQuestionsList(new PsyGaugeQuestions());
        List<PsyGaugeQuestionsOptions> optionsList = questionsOptionsService.selectPsyGaugeQuestionsOptionsList(new PsyGaugeQuestionsOptions());
        for (PsyGaugeQuestions question : questionsList) {
            question.setOptionList(optionsList.stream().filter(p -> p.getGaugeQuestionsId().equals(question.getId())).collect(Collectors.toList()));
        }
        questionsList.forEach(p -> redisCache.setCacheObject(CacheConstants.QUESTION_BY_ID_KEY+"::"+p.getId(),p));

        refreshIdList();
    }

    //刷新该对象 各种类型下的id清单
    @Override
    public void refreshIdList(){
        //完整对象清单
        List<PsyGaugeQuestions> allGaugeList = psyGaugeQuestionsMapper.selectList(new LambdaQueryWrapper<PsyGaugeQuestions>()
                .select(PsyGaugeQuestions::getId,PsyGaugeQuestions::getGaugeId)
                .orderByDesc(PsyGaugeQuestions::getCreateTime));

        //删除原先的所有idList
        redisCache.deleteStartWith(CacheConstants.QUESTION_ID_LIST);
        
        //id清单放入缓存
        ////完整id清单
        List<Long> allIdList = allGaugeList.stream().map(p -> p.getId()).collect(Collectors.toList());
        redisCache.setCacheList(CacheConstants.QUESTION_ID_LIST + "::" + "all",allIdList);

        ////各个类型的id清单
        Map<Long, List<Long>> listMap =  allGaugeList.stream()
                .collect(Collectors.groupingBy(
                        PsyGaugeQuestions::getGaugeId,
                        Collectors.mapping(PsyGaugeQuestions::getId, Collectors.toList())
                ));
        for (Map.Entry<Long, List<Long>> entry : listMap.entrySet()) {
            redisCache.setCacheList(CacheConstants.QUESTION_ID_LIST + "::" + "gaugeId" + entry.getKey(), entry.getValue());
        }

    }

}
