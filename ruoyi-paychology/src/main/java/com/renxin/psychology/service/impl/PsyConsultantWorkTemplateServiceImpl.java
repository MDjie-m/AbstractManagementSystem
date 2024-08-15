package com.renxin.psychology.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultWork;
import com.renxin.psychology.domain.PsyConsultantWorkTemplate;
import com.renxin.psychology.mapper.PsyConsultWorkMapper;
import com.renxin.psychology.mapper.PsyConsultantWorkTemplateMapper;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyConsultWorkService;
import com.renxin.psychology.service.IPsyConsultantWorkTemplateService;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 咨询师排程模版Service业务层处理
 * 
 * @author renxin
 * @date 2024-08-13
 */
@Service
public class PsyConsultantWorkTemplateServiceImpl implements IPsyConsultantWorkTemplateService 
{
    @Autowired
    private PsyConsultantWorkTemplateMapper psyConsultantWorkTemplateMapper;
    
    @Autowired
    private IPsyConsultService psyConsultService;
    
    @Autowired
    private IPsyConsultWorkService consultWorkService;

    @Resource
    private PsyConsultWorkMapper consultWorkMapper;

    /**
     * 查询咨询师排程模版
     * 
     * @param id 咨询师排程模版主键
     * @return 咨询师排程模版
     */
    @Override
    public PsyConsultantWorkTemplate selectPsyConsultantWorkTemplateById(Long id)
    {
        return psyConsultantWorkTemplateMapper.selectPsyConsultantWorkTemplateById(id);
    }

    /**
     * 查询咨询师排程模版列表
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 咨询师排程模版
     */
    @Override
    public List<PsyConsultantWorkTemplate> selectPsyConsultantWorkTemplateList(PsyConsultantWorkTemplate psyConsultantWorkTemplate)
    {
        return psyConsultantWorkTemplateMapper.selectPsyConsultantWorkTemplateList(psyConsultantWorkTemplate);
    }

    /**
     * 新增咨询师排程模版
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 结果
     */
    @Override
    public int insertPsyConsultantWorkTemplate(PsyConsultantWorkTemplate req)
    {
        req.setCreateTime(DateUtils.getNowDate());
        req.setCreateBy(ObjectUtils.isNotEmpty(req.getCreateBy()) ? req.getCreateBy() : req.getConsultantId()+"");
        PsyConsultVO consultantVO = psyConsultService.getOne(req.getConsultantId());
        req.setConsultantName(consultantVO.getNickName());
        
        return psyConsultantWorkTemplateMapper.insertPsyConsultantWorkTemplate(req);
    }

    /**
     * 修改咨询师排程模版
     * 
     * @param psyConsultantWorkTemplate 咨询师排程模版
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePsyConsultantWorkTemplate(PsyConsultantWorkTemplate req)
    {
        req.setUpdateTime(DateUtils.getNowDate());
        req.setUpdateBy(ObjectUtils.isNotEmpty(req.getUpdateBy()) ? req.getUpdateBy() : req.getConsultantId()+"");
        PsyConsultVO consultantVO = psyConsultService.getOne(req.getConsultantId());
        req.setConsultantName(consultantVO.getNickName());

        int i = psyConsultantWorkTemplateMapper.updatePsyConsultantWorkTemplate(req);
        //执行模版, 生成近期排程
        executeConsultantWorkTemplate(req);
        return i;
    }

    /**
     * 批量删除咨询师排程模版
     * 
     * @param ids 需要删除的咨询师排程模版主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantWorkTemplateByIds(Long[] ids)
    {
        return psyConsultantWorkTemplateMapper.deletePsyConsultantWorkTemplateByIds(ids);
    }

    /**
     * 删除咨询师排程模版信息
     * 
     * @param id 咨询师排程模版主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantWorkTemplateById(Long id)
    {
        return psyConsultantWorkTemplateMapper.deletePsyConsultantWorkTemplateById(id);
    }

    //执行指定模版, 生成排班数据
    @Override
    public void executeConsultantWorkTemplate(PsyConsultantWorkTemplate req){
        //需要执行的模板清单
        List<PsyConsultantWorkTemplate> workTemplateList = psyConsultantWorkTemplateMapper.selectPsyConsultantWorkTemplateList(req);
        fillStartAndEnd(req);
        String createBy = ObjectUtils.isNotEmpty(req.getConsultantId()) ? req.getConsultantId()+"" : "system";

        for (PsyConsultantWorkTemplate workTemplate : workTemplateList) {
            //指定咨询师在该时期已有的旧排程
            List<PsyConsultWork> oldWorkList = consultWorkMapper.selectList(new LambdaQueryWrapper<PsyConsultWork>()
                    .eq(PsyConsultWork::getConsultId, workTemplate.getConsultantId())
                    .ge(PsyConsultWork::getDay,req.getStartDate())
                    .le(PsyConsultWork::getDay,req.getEndDate()));

            //按模版生成新排程
            List<PsyConsultWork> newWorkList = new ArrayList<>();
            ////获取期间每一天的日期
            List<String> dayList = getDateListBetween(req.getStartDate(), req.getEndDate());
            ////一周之内,每一天的设置
            String[] monday = workTemplate.getMonday().split("\\|");
            String[] tuesday = workTemplate.getTuesday().split("\\|");
            String[] wednesday = workTemplate.getWednesday().split("\\|");
            String[] thursday = workTemplate.getThursday().split("\\|");
            String[] friday = workTemplate.getFriday().split("\\|");
            String[] saturday = workTemplate.getSaturday().split("\\|");
            String[] sunday = workTemplate.getSunday().split("\\|");
            
            //根据周几不同, 设置相应的工作时间
            for (String day : dayList) {
                PsyConsultWork work = new PsyConsultWork();
                work.setConsultId(workTemplate.getConsultantId());
                work.setDay(day);
                work.setStatus("0");//工作日
                work.setUsed("[]");
                switch (getDayOfWeek(day)){
                    case "周一":
                        work.setWeek("周一");
                        work.setLive("[" + monday[0] + "]");
                        if (monday.length > 1){
                            work.setTimes(monday[1]);
                        }else {
                            work.setTimes("");
                        }
                        break;
                    case "周二":
                        work.setWeek("周二");
                        work.setLive("[" + tuesday[0] + "]");
                        if (tuesday.length > 1){
                            work.setTimes(tuesday[1]);
                        }else {
                            work.setTimes("");
                        }
                        break;
                    case "周三":
                        work.setWeek("周三");
                        work.setLive("[" + wednesday[0] + "]");
                        if (wednesday.length > 1){
                            work.setTimes(wednesday[1]);
                        }else {
                            work.setTimes("");
                        }
                        break;
                    case "周四":
                        work.setWeek("周四");
                        work.setLive("[" + thursday[0] + "]");
                        if (thursday.length > 1){
                            work.setTimes(thursday[1]);
                        }else {
                            work.setTimes("");
                        }
                        break;
                    case "周五":
                        work.setWeek("周五");
                        work.setLive("[" + friday[0] + "]");
                        if (friday.length > 1){
                            work.setTimes(friday[1]);
                        }else {
                            work.setTimes("");
                        }
                        break;
                    case "周六":
                        work.setWeek("周六");
                        work.setLive("[" + saturday[0] + "]");
                        if (saturday.length > 1){
                            work.setTimes(saturday[1]);
                        }else {
                            work.setTimes("");
                        }
                        break;
                    case "周日":
                        work.setWeek("周日");
                        work.setLive("[" + sunday[0] + "]");
                        if (sunday.length > 1){
                            work.setTimes(sunday[1]);
                        }else {
                            work.setTimes("");
                        }
                        break;
                }
                //if (!"[]".equals(work.getLive())){
                    newWorkList.add(work);
                //}
            }

            //比较新旧清单, 执行分类
            Map<String, List<PsyConsultWork>> listMap = compareWorkLists(oldWorkList, newWorkList);
            //根据分类清单执行数据refresh
            refreshWorkData(listMap, createBy);
        }
    }

    @Override
    public void savePsyConsultantWorkBatch(List<PsyConsultWork> newWorkList){
        //指定咨询师在该时期已有的旧排程
        List<String> oldWorkDayList = newWorkList.stream().map(p -> p.getDay()).collect(Collectors.toList());
        List<PsyConsultWork> oldWorkList = consultWorkMapper.selectList(new LambdaQueryWrapper<PsyConsultWork>()
                .eq(PsyConsultWork::getConsultId, newWorkList.get(0).getConsultId())
                .in(PsyConsultWork::getDay,oldWorkDayList));

        Map<String, List<PsyConsultWork>> listMap = compareWorkListsForTeam(oldWorkList, newWorkList);
        refreshWorkData(listMap,"system");
    }


    //比较新旧清单(团督课程生成排程专用)
    private Map<String, List<PsyConsultWork>> compareWorkListsForTeam(List<PsyConsultWork> oldWorkList, List<PsyConsultWork> newWorkList) {
        Set<String> oldDays = oldWorkList.stream()
                .map(PsyConsultWork::getDay)
                .collect(Collectors.toSet());

        Set<String> newDays = newWorkList.stream()
                .map(PsyConsultWork::getDay)
                .collect(Collectors.toSet());

        // 获取共同的部分并更新 live 字段
        List<PsyConsultWork> common = oldWorkList.stream()
                .filter(work -> newDays.contains(work.getDay()))
                .peek(work -> {
                    // 找到对应的 newWork
                    Optional<PsyConsultWork> matchingNewWork = newWorkList.stream()
                            .filter(newWork -> newWork.getDay().equals(work.getDay()))
                            .findFirst();


                    if (matchingNewWork.isPresent()) {
                        PsyConsultWork newWork = matchingNewWork.get();
                        // live : 合并 新live 和 旧live 字段的并集
                        String combinedLive = mergeLiveAndUsed(work.getLive(), newWork.getLive());
                        combinedLive = combinedLive.replace("[,","[").replace(" ","");
                        work.setLive(combinedLive);

                        //used 取新旧并集
                        String combinedUsed = mergeLiveAndUsed(work.getUsed(), newWork.getUsed());
                        combinedUsed = combinedUsed.replace("[,","[").replace(" ","");
                        work.setUsed(combinedUsed);
                        
                    }
                })
                .collect(Collectors.toList());

        List<PsyConsultWork> onlyOld = oldWorkList.stream()
                .filter(work -> !newDays.contains(work.getDay()))
                .collect(Collectors.toList());

        List<PsyConsultWork> onlyNew = newWorkList.stream()
                .filter(work -> !oldDays.contains(work.getDay()))
                .collect(Collectors.toList());

        Map<String, List<PsyConsultWork>> resultMap = new HashMap<>();
        resultMap.put("onlyOld", onlyOld);
        resultMap.put("onlyNew", onlyNew);
        resultMap.put("common", common);

        return resultMap;
    }
    
    //比较新旧清单
    private Map<String, List<PsyConsultWork>> compareWorkLists(List<PsyConsultWork> oldWorkList, List<PsyConsultWork> newWorkList) {
        Set<String> oldDays = oldWorkList.stream()
                .map(PsyConsultWork::getDay)
                .collect(Collectors.toSet());

        Set<String> newDays = newWorkList.stream()
                .map(PsyConsultWork::getDay)
                .collect(Collectors.toSet());

        // 获取共同的部分并更新 live 字段
        List<PsyConsultWork> common = oldWorkList.stream()
                .filter(work -> newDays.contains(work.getDay()))
                .peek(work -> {
                    // 找到对应的 newWork
                    Optional<PsyConsultWork> matchingNewWork = newWorkList.stream()
                            .filter(newWork -> newWork.getDay().equals(work.getDay()))
                            .findFirst();
                    
                    if (matchingNewWork.isPresent()) {
                        PsyConsultWork newWork = matchingNewWork.get();
                        // live : 合并 新live 和 旧used 字段的并集
                        String combinedLive = mergeLiveAndUsed(work.getUsed(), newWork.getLive());
                        // 移除开头的逗号和空格
                        combinedLive = combinedLive.replace("[,","[").replace(" ","");
                        work.setLive(combinedLive);

                        // times :  以 newWork 中的为准
                        String newWorkTimes = newWork.getTimes();
                        if (ObjectUtils.isNotEmpty(newWorkTimes)) { work.setTimes(newWorkTimes); }
                    }
                })
                .collect(Collectors.toList());

        List<PsyConsultWork> onlyOld = oldWorkList.stream()
                .filter(work -> !newDays.contains(work.getDay()))
                .peek(work -> work.setLive(work.getUsed()))//使用used覆盖live
                .collect(Collectors.toList());

        List<PsyConsultWork> onlyNew = newWorkList.stream()
                .filter(work -> !oldDays.contains(work.getDay()))
                .collect(Collectors.toList());

        Map<String, List<PsyConsultWork>> resultMap = new HashMap<>();
        resultMap.put("onlyOld", onlyOld);
        resultMap.put("onlyNew", onlyNew);
        resultMap.put("common", common);

        return resultMap;
    }

    //根据分类清单执行数据refresh
    private void refreshWorkData(Map<String, List<PsyConsultWork>> listMap, String createBy){
        List<PsyConsultWork> commonList = listMap.get("common");//共有部分
        List<PsyConsultWork> onlyOldList = listMap.get("onlyOld");
        List<PsyConsultWork> onlyNewList = listMap.get("onlyNew");

        commonList.forEach(p -> {
            p.setUpdateTime(new Date());
            p.setUpdateBy(createBy);
            consultWorkMapper.updateById(p);
        });
        onlyOldList.forEach(p -> {
            p.setUpdateTime(new Date());
            p.setUpdateBy(createBy);
            consultWorkMapper.updateById(p);
        });
        onlyNewList.forEach(p -> {
            p.setCreateTime(new Date());
            p.setCreateBy(createBy);
        });
        if (ObjectUtils.isNotEmpty(onlyNewList)){
            consultWorkMapper.insertBatch(onlyNewList);
        }
    }
    
    //获取起止日期内的每一天
    private List<String> getDateListBetween(String startDate, String endDate) {
        List<String> dateList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        // 遍历日期
        LocalDate current = start;
        while (!current.isAfter(end)) {
            dateList.add(current.format(formatter));
            current = current.plusDays(1);
        }
        return dateList;
    }

    //获取时间点的并集
    private String getUnionHours(String aHours,String bHours){
        aHours = aHours.replace("[","").replace("]","");
        bHours = bHours.replace("[","").replace("]","");

        // 将字符串转换为集合
        Set<String> aSet = new HashSet<>(Arrays.asList(aHours.split(",")));
        Set<String> bSet = new HashSet<>(Arrays.asList(bHours.split(",")));

        // 合并两个集合
        aSet.addAll(bSet);

        String result = new TreeSet<String>(aSet).toString();
        return result;
    }
    
    
    //根据日期获取周几
    private String getDayOfWeek(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // 获取星期几
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 映射到中文
        switch (dayOfWeek) {
            case MONDAY:
                return "周一";
            case TUESDAY:
                return "周二";
            case WEDNESDAY:
                return "周三";
            case THURSDAY:
                return "周四";
            case FRIDAY:
                return "周五";
            case SATURDAY:
                return "周六";
            case SUNDAY:
                return "周日";
            default:
                return "";
        }
    }

    //填充起止日期 (默认今天至下个月底)
    private void fillStartAndEnd(PsyConsultantWorkTemplate req){
        String start = req.getStartDate();
        String end = req.getEndDate();
        if (ObjectUtils.isNotEmpty(start) && ObjectUtils.isNotEmpty(end)){
            return;
        }

        //获取今天的日期
        LocalDate today = LocalDate.now();
        // 计算下个月的第一天的日期
        LocalDate firstDayOfNextMonth = today.plusMonths(1).withDayOfMonth(1);
        // 计算下个月的最后一天
        LocalDate lastDayOfNextMonth = firstDayOfNextMonth.plusMonths(1).minusDays(1);
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 格式化日期
        String todayStr = today.format(formatter);
        String endOfNextMonthStr = lastDayOfNextMonth.format(formatter);

        req.setStartDate(todayStr);
        req.setEndDate(endOfNextMonthStr);

    }

    // 合并 live 和 used 字段
    private String mergeLiveAndUsed(String used, String live) {
        Set<String> uniqueValues = new HashSet<>();
        uniqueValues.addAll(parseStringToSet(used));
        uniqueValues.addAll(parseStringToSet(live));

        return uniqueValues.toString(); // 返回"[value1,value2,...]"格式的字符串
    }

    // 将 "[value1,value2,...]" 格式的字符串转为 Set<String>
    private Set<String> parseStringToSet(String str) {
        str = str.replaceAll("[\\[\\] ]", ""); // 去掉方括号和空格
        return new HashSet<>(Arrays.asList(str.split(",")));
    }
}
