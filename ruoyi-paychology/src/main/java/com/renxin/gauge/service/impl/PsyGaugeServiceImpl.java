package com.renxin.gauge.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.domain.RelateInfo;
import com.renxin.common.enums.GaugeStatus;
import com.renxin.common.utils.DateUtils;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.mapper.CourCourseMapper;
import com.renxin.course.service.ICourCourseService;
import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.*;
import com.renxin.gauge.mapper.*;
import com.renxin.gauge.service.IPsyGaugeQuestionsResultService;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.gauge.vo.GaugeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.renxin.gauge.service.IPsyGaugeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 心理测评Service业务层处理
 * 
 * @author renxin
 * @date 2022-08-30
 */
@Service
public class PsyGaugeServiceImpl extends ServiceImpl<PsyGaugeMapper, PsyGauge> 
        implements IPsyGaugeService 
{

    @Autowired
    private IPsyGaugeService self; // 注入自身

    @Resource
    private RedisCache redisCache;
    
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

    @Autowired
    private IPsyGaugeQuestionsResultService psyGaugeQuestionsResultService;
    
    @Autowired
    private IPsyOrderService psyOrderService;

    /**
     * 查询心理测评
     * 
     * @param id 心理测评主键
     * @return 心理测评
     */
    @Override
    @Cacheable(value = CacheConstants.GAUGE_BY_ID_KEY, key = "#id", unless = "#result == null")
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

        refreshIdList();
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
    @CacheEvict(cacheNames = CacheConstants.GAUGE_BY_ID_KEY, key = "#psyGauge.id")
    public int updatePsyGauge(PsyGauge psyGauge)
    {
        psyGauge.setUpdateTime(DateUtils.getNowDate());
        int i = psyGaugeMapper.updatePsyGauge(psyGauge);
        
        refreshIdList();
        return i;
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
        int i = psyGaugeMapper.deletePsyGaugeByIds(ids);
        //批量删除缓存
        redisCache.deleteMultiCache(CacheConstants.GAUGE_BY_ID_KEY, Arrays.asList(ids));
        refreshIdList();
        return i;
    }

    /**
     * 删除心理测评信息
     * 
     * @param id 心理测评主键
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.GAUGE_BY_ID_KEY, key = "#id")
    public int deletePsyGaugeById(Long id)
    {
        int i = psyGaugeMapper.deletePsyGaugeById(id);
        refreshIdList();
        return i;
    }

    //查询与测评的关联信息
    @Override
    public RelateInfo getGaugeRelateInfo(PsyGauge gaugeReq){
        Long userId = gaugeReq.getUserId();
        Long id = gaugeReq.getId();
       // GaugeVO gaugeVO = new GaugeVO();
        RelateInfo gaugeVO = new RelateInfo();
        
        List<PsyOrder> psyOrder = psyOrderService.getPsyOrder(userId, id);
        // 未完成测试，待测试 orderId 优先级最高
        // 全部测试完成，查看报告 orderId
        // 再次购买
        if (psyOrder.size() > 0) {
            Optional<PsyOrder> first = psyOrder.stream().filter(order -> order.getGaugeStatus() == GaugeStatus.UNFINISHED.getValue()).findFirst();
            if (first.isPresent()) {
                gaugeVO.setOrderId(first.get().getId());
                gaugeVO.setOrderNo(first.get().getOrderId());
                gaugeVO.setIsCompleted(GaugeStatus.UNFINISHED.getValue());
            } else {
                gaugeVO.setOrderId(psyOrder.get(0).getId());
                gaugeVO.setOrderNo(psyOrder.get(0).getOrderId());
                gaugeVO.setIsCompleted(psyOrder.get(0).getGaugeStatus());
            }

            gaugeVO.setIsBuy(GaugeConstant.GAUGE_IS_BUY);
        } else {
            gaugeVO.setIsBuy(GaugeConstant.GAUGE_NOT_BUY);
            gaugeVO.setIsCompleted(GaugeStatus.UNFINISHED.getValue());
        }

        gaugeVO.setSize(psyOrder.size());
        PsyGaugeQuestionsResult psyGaugeQuestionsResult = new PsyGaugeQuestionsResult();
        psyGaugeQuestionsResult.setUserId(userId);
        psyGaugeQuestionsResult.setGaugeId(id);
        if (gaugeVO.getOrderId() != null) {
            psyGaugeQuestionsResult.setOrderId(gaugeVO.getOrderId());
        }
        List<PsyGaugeQuestionsResult> psyGaugeQuestionsResultList = psyGaugeQuestionsResultService.selectPsyGaugeQuestionsResultList(psyGaugeQuestionsResult);
        // 将多选题的答案选项分组归并
//        Map<Integer, Long> result  = psyGaugeQuestionsResultList.stream().collect(Collectors.groupingBy(PsyGaugeQuestionsResult::getQuestionsId, Collectors.counting()));
        gaugeVO.setFinishedNum(psyGaugeQuestionsResultList.size());
        if (gaugeVO.getNum() != null) {
            int num = psyOrderService.getOrderNumByGaugeId(id);
            gaugeVO.setNum(gaugeVO.getNum() + num);
        }
        return gaugeVO;
        
    }

    //刷新缓存
    @Override
    public void refreshCacheByIdList(List<Long> idList){
        redisCache.deleteMultiCache(CacheConstants.GAUGE_BY_ID_KEY,idList);
        for (Long id : idList) {
            self.selectPsyGaugeById(id);
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
        List<Long> courseIdList = psyGaugeMapper.selectList(new LambdaQueryWrapper<PsyGauge>()
                .select(PsyGauge::getId)
                .orderByDesc(PsyGauge::getCreateTime)).stream().map(p -> p.getId()).collect(Collectors.toList());

        //刷新缓存
        refreshCacheByIdList(courseIdList);
        refreshIdList();
    }

    //刷新该对象 各种类型下的id清单
    @Override
    public void refreshIdList(){
        //完整对象清单
        List<PsyGauge> allGaugeList = psyGaugeMapper.selectList(new LambdaQueryWrapper<PsyGauge>()
                .select(PsyGauge::getId,PsyGauge::getGaugeClass)
                .orderByDesc(PsyGauge::getCreateTime));

        //id清单放入缓存
        ////完整id清单
        List<Long> allIdList = allGaugeList.stream().map(p -> p.getId()).collect(Collectors.toList());
        redisCache.setCacheList(CacheConstants.GAUGE_ID_LIST + "::" + "all",allIdList);

        ////各个类型的id清单
        Map<Integer, List<Long>> listMap =  allGaugeList.stream()
                .collect(Collectors.groupingBy(
                        PsyGauge::getGaugeClass,
                        Collectors.mapping(PsyGauge::getId, Collectors.toList())
                ));
        for (Map.Entry<Integer, List<Long>> entry : listMap.entrySet()) {
            redisCache.setCacheList(CacheConstants.GAUGE_ID_LIST + "::" + "gaugeClass" + entry.getKey(), entry.getValue());
        }

    }
}
