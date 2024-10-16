package com.renxin.psychology.service.impl;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.constant.CacheConstants;
import com.renxin.common.constant.PsyConstants;
import com.renxin.common.core.domain.model.LoginUser;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.domain.RelateInfo;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.SecurityUtils;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.mapper.PsyConsultMapper;
import com.renxin.psychology.mapper.PsyConsultantOrderMapper;
import com.renxin.psychology.mapper.PsyConsultantSupervisionMemberMapper;
import com.renxin.psychology.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantTeamSupervisionMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 团队督导(组织)Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
@Slf4j
public class PsyConsultantTeamSupervisionServiceImpl extends ServiceImpl<PsyConsultantTeamSupervisionMapper, PsyConsultantTeamSupervision> 
        implements IPsyConsultantTeamSupervisionService 
{
    @Autowired
    private IPsyConsultantTeamSupervisionService self; // 注入自身

    @Resource
    private RedisCache redisCache;
    
    @Autowired
    private PsyConsultantTeamSupervisionMapper psyConsultantTeamSupervisionMapper;
    
    @Autowired
    private IPsyConsultantSupervisionMemberService memberService;
    
    @Autowired
    private IPsyConsultantScheduleService consultantScheduleService;
    
    @Autowired
    private PsyConsultantSupervisionMemberMapper memberMapper;
    
    @Autowired
    private PsyConsultMapper psyConsultMapper;
    
    @Autowired
    private IPsyConsultantWorkTemplateService consultantWorkTemplateService;
    
    @Autowired
    private IPsyConsultantOrderService consultantOrderService;

    @Autowired
    private PsyConsultantOrderMapper psyConsultantOrderMapper;
    
    
    /**
     * 查询团队督导(组织)
     * 
     * @param id 团队督导(组织)主键
     * @return 团队督导(组织)
     */
    @Override
    @Cacheable(value = CacheConstants.TEAM_SUP_BY_ID_KEY, key = "#id", unless = "#result == null")
    public PsyConsultantTeamSupervision selectPsyConsultantTeamSupervisionById(Long id)
    {
        log.info( java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + "--------------------------------连接MySQL查询团督:" + id);
        PsyConsultantTeamSupervision team = psyConsultantTeamSupervisionMapper.selectPsyConsultantTeamSupervisionById(id);

        //查询成员信息
        PsyConsultantSupervisionMember memberReq = new PsyConsultantSupervisionMember();
        memberReq.setTeamSupervisionId(team.getId());
        List<PsyConsultantSupervisionMember> memberList = memberMapper.selectPsyConsultantSupervisionMemberList(memberReq);
        team.setMemberList(memberList);

        //最大团队人数
        Integer maxNumPeople = team.getMaxNumPeople();
        if (ObjectUtils.isNotEmpty(maxNumPeople)){
            //当前登记人数
            int memberNum = memberList.size();
            //剩余名额数
            team.setSurplusJoinNum(maxNumPeople - memberNum);
        }
        
        //计算课程时长
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(team.getLectureStartTime(), formatter);
        LocalTime endTime = LocalTime.parse(team.getLectureEndTime(), formatter);
        Duration duration = Duration.between(startTime, endTime);
        double hours = duration.get(ChronoUnit.SECONDS) / 3600.0;
        team.setLectureHour(hours);

        //督导师详情
        PsyConsult psyConsult = psyConsultMapper.selectById(team.getConsultantId());
        team.setConsultantDetail(psyConsult);
        
        //总讲课次数
        team.setTotalNum(team.getCycleNumber());
        //已完成讲课数
        int usedNum = 0;
        //招募已完成
        if (team.getStatus() != 0){
            usedNum = consultantScheduleService.getTimeNumForTeam(id);
        }
        team.setUsedNum(usedNum);
        //剩余讲课次数
        team.setSurplusNum(team.getCycleNumber() - usedNum);
        
        //团督仍在开课中
        if (team.getStatus() == 1){
            PsyConsultantSchedule querySche = new PsyConsultantSchedule();
            querySche.setTeamId(id);
            querySche.setRealTimeStart(LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            List<PsyConsultantSchedule> scheduleList = consultantScheduleService.selectPsyConsultantScheduleList(querySche);
            if (ObjectUtils.isNotEmpty(scheduleList)){
                team.setNextBeginTime(scheduleList.get(0).getRealTime());
            }
        }
        
        //redisCache.setCacheObject("selectPsyConsultantTeamSupervisionByIdCache::1111",team);
        return team;
    }

    /**
     * 查询团队督导(组织)列表
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 团队督导(组织)
     */
    @Override
    public List<PsyConsultantTeamSupervision> selectPsyConsultantTeamSupervisionList(PsyConsultantTeamSupervision req)
    {
        if (ObjectUtils.isNotEmpty(req.getIdList())){
            List<PsyConsultantTeamSupervision> cacheList = redisCache.getMultiCacheMapValue(CacheConstants.TEAM_SUP_BY_ID_KEY, req.getIdList());
            return cacheList;
        }
        Integer teamType = req.getTeamType();//督导类型
        List<PsyConsultantTeamSupervision> teamList = new ArrayList<>();
        //查询"团队督导"时, 从team表获取数据
        if (ObjectUtils.isEmpty(teamType) || teamType == 1){
            
            teamList = psyConsultantTeamSupervisionMapper.selectPsyConsultantTeamSupervisionList(req);

            for (PsyConsultantTeamSupervision team : teamList) {
                //最大团队人数
                Integer maxNumPeople = team.getMaxNumPeople();
                if (ObjectUtils.isNotEmpty(maxNumPeople)){
                    //当前登记人数
                    int memberNum = memberMapper.queryMemberCount(team.getId() + "");
                    //剩余名额数
                    team.setSurplusJoinNum(maxNumPeople - memberNum);
                }
            }
        }else if (teamType == 2 || teamType ==3){
        //查询"个案督导"或"个人体验"时, 直接查询咨询师数据
            teamList = psyConsultantTeamSupervisionMapper.selectPsyConsultantPersonSupervisionList(req);
        }

//        NoticeMessage noticeMessage = new NoticeMessage();
//        noticeMessage.setMessageType(Constants.MSG_CONSULT_START);
//        noticeMessage.setNoticeMethod(NoticeMethodEnum.WECHAT);
//        noticeMessage.setReceiverId("oP8146998AoIjkNMZx4s2vK4me5w");
//        noticeMessage.setTitle("111");
//        noticeMessage.setContent("222");
//        wxMsgUtils.send(noticeMessage);
        
        return teamList;
    }

//    @Resource
//    private WxMsgUtils wxMsgUtils;
        

    /**
     * 新增团队督导(组织)
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPsyConsultantTeamSupervision(PsyConsultantTeamSupervision req)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        req.setCreateBy(loginUser.getUserId()+"");
        req.setUpdateBy(loginUser.getUserId()+"");
        req.setCreateTime(DateUtils.getNowDate());
        req.setUpdateTime(DateUtils.getNowDate());
        int i = psyConsultantTeamSupervisionMapper.insertPsyConsultantTeamSupervision(req);
        
        //redisCache.setCacheObject(CacheConstants.TEAM_SUP_BY_ID_KEY + req.getId(),req);
        refreshIdList();
        return i;
    }

    /**
     * 修改团队督导(组织)
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 结果
     */
    @Override
    @CacheEvict(cacheNames = CacheConstants.TEAM_SUP_BY_ID_KEY, key = "#req.id")
    public int updatePsyConsultantTeamSupervision(PsyConsultantTeamSupervision req)
    {
        req.setUpdateTime(DateUtils.getNowDate());
        int i = psyConsultantTeamSupervisionMapper.updatePsyConsultantTeamSupervision(req);
        
        //缓存覆盖 - 废案
        //redisCache.setCacheObject(CacheConstants.TEAM_SUP_BY_ID_KEY + req.getId(),req);
        refreshIdList();
        
        return i;
    }
    
    //当类型不为"团队督导"时, 清除冗余字段
    private void clearColumn(PsyConsultantTeamSupervision team){
        if (team.getTeamType() != 1){
            //team.setConsultantId(null);
            //team.setStatus(null);
            //team.setPrice(null);
            //team.setTeamType(null);
            team.setTitle(null);
            team.setCycleNumber(null);
            team.setPeriodNo(null);
            team.setWeekDay(null);
            team.setLectureStartTime(null);
            team.setLectureEndTime(null);
           // team.setFirstLectureDate(null);
            team.setMaxNumPeople(null);
            team.setSurplusJoinNum(null);
            team.setMemberList(null);

        }
    }
    
    /**
     * 批量删除团队督导(组织)
     * 
     * @param ids 需要删除的团队督导(组织)主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantTeamSupervisionByIds(Long[] ids)
    {
        int i = psyConsultantTeamSupervisionMapper.deletePsyConsultantTeamSupervisionByIds(ids);
        //批量删除缓存
        redisCache.deleteMultiCache(CacheConstants.TEAM_SUP_BY_ID_KEY,Arrays.asList(ids));
        refreshIdList();
        return i;
    }

    /**
     * 删除团队督导(组织)信息
     * 
     * @param id 团队督导(组织)主键
     * @return 结果
     */
    @Override
    @Caching(
            evict = {
                   // @CacheEvict(cacheNames = "selectPsyConsultantTeamSupervisionListCache", allEntries = true),
                    @CacheEvict(cacheNames = CacheConstants.TEAM_SUP_BY_ID_KEY, key = "#id"),
            }
    )
    public int deletePsyConsultantTeamSupervisionById(Long id)
    {
        int i = psyConsultantTeamSupervisionMapper.deletePsyConsultantTeamSupervisionById(id);
        refreshIdList();
        return i;
    }

    /**
     * 付款完成后, 处理订单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleOrder(PsyConsultantOrder consultantOrder){
        Long serverId = Long.valueOf(consultantOrder.getServerId());
        //购买团督服务
        if (PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM.equals(consultantOrder.getServerType())){
            PsyConsultantSupervisionMember member = new PsyConsultantSupervisionMember();
                member.setTeamSupervisionId(serverId);
                member.setMemberId(consultantOrder.getPayConsultantId()+"");
                member.setSupervisionType(PsyConstants.CONSULTANT_ORDER_TEAM_SUP_NUM);
                member.setOrderNo(consultantOrder.getOrderNo());
            memberService.insertPsyConsultantSupervisionMember(member);

            PsyConsultantTeamSupervision team = selectPsyConsultantTeamSupervisionById(Long.valueOf(serverId));
            //若团队已满员
            if (team.getSurplusJoinNum() <= 0){
                handleTeamFull(serverId);
            }
        }
    }

    /**
     * 团队满员处理
     * @param teamId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handleTeamFull(Long teamId){
        PsyConsultantTeamSupervision team = psyConsultantTeamSupervisionMapper.selectPsyConsultantTeamSupervisionById(teamId);

        //更新团队信息
        List<String> lectureDayList = getNextNDays(team.getCycleNumber().intValue(), team.getWeekDay());//获取开课日期清单
        team.setFirstLectureDate(lectureDayList.get(0));//初次开课日期
        team.setStatus(ConsultConstant.TEAM_STATUS_START_1);
        psyConsultantTeamSupervisionMapper.updatePsyConsultantTeamSupervision(team);
        
        //为督导师添加排程
        ArrayList<PsyConsultantSchedule> scheduleList = new ArrayList<>();
        ArrayList<PsyConsultWork> workDayList = new ArrayList<>();
        int timeNum = 1;
        for (String lectureDay : lectureDayList) {
            //每日任务
            PsyConsultantSchedule schedule = new PsyConsultantSchedule();
            schedule.setTeamId(teamId);
            schedule.setTimeStart(team.getLectureStartTime());
            schedule.setTimeEnd(team.getLectureEndTime());
            schedule.setWeek(getWeekday(team.getWeekDay()));
            schedule.setTime(Integer.valueOf(team.getLectureStartTime().substring(0,2)));
            schedule.setConsultId(Long.valueOf(team.getConsultantId()));
            schedule.setScheduleType(21);//21.团督开课
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
            schedule.setCreateBy("system");
            schedule.setUpdateBy("system");
            
            schedule.setDay(lectureDay);
            schedule.setRealTime(lectureDay + " " + team.getLectureStartTime());
            schedule.setTimeNum(timeNum++);
            schedule.setTotalNum(team.getCycleNumber());
            scheduleList.add(schedule);

            //排程占用
            PsyConsultWork work = new PsyConsultWork();
            work.setConsultId(Long.valueOf(team.getConsultantId()));
            work.setDay(lectureDay);
            work.setWeek(getWeekday(team.getWeekDay()));
            work.setCreateTime(new Date());
            work.setUpdateTime(new Date());
            work.setCreateBy("system");
            work.setUpdateBy("system");
            String used = "[" + getUsedHours(team.getLectureStartTime(),team.getLectureEndTime()) + "]";
            work.setLive(used);
            work.setUsed(used);
            workDayList.add(work);
        }
        consultantScheduleService.insertPsyConsultantScheduleList(scheduleList);
        consultantWorkTemplateService.savePsyConsultantWorkBatch(workDayList);
        
    }

    //获取指定时间段涉及的时间点
    private String getUsedHours(String startTime, String endTime){
        //获取整点数
        Integer start = Integer.valueOf(startTime.split(":")[0]);
        Integer end = Integer.valueOf(endTime.split(":")[0]);
        
        //获取期间的每一个整点
        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (result.length() > 0) {
                result.append(","); // 如果不是第一个元素，添加逗号
            }
            result.append(i); // 添加当前数字
        }
        
        return result.toString();
    }
    
    /**
     * 获取往后N个周几的日期, 不含当天 (如:最近5个周三的日期)
     * @param currentDate
     * @param N
     * @param targetDay
     * @return
     */
    private List<String> getNextNDays(int N, int targetDay) {
        List<String> days = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 从明天开始计算
        LocalDate nextDay = LocalDate.now().plusDays(1);

        // DayOfWeek映射：1-周一, 2-周二, ..., 7-周日
        DayOfWeek targetDayOfWeek = DayOfWeek.of(targetDay);

        while (days.size() < N) {
            if (nextDay.getDayOfWeek() == targetDayOfWeek) {
                days.add(nextDay.format(formatter));
            }
            nextDay = nextDay.plusDays(1);
        }
        return days;
    }

    //星期翻译
    private String getWeekday(int day) {
        String weekday;
        switch(day) {
            case 1:
                weekday = "周一";
                break;
            case 2:
                weekday = "周二";
                break;
            case 3:
                weekday = "周三";
                break;
            case 4:
                weekday = "周四";
                break;
            case 5:
                weekday = "周五";
                break;
            case 6:
                weekday = "周六";
                break;
            case 7:
                weekday = "周天";
                break;
            default:
                weekday = "无效输入";
                break;
        }
        return weekday;
    }


    //获取团队督导(组织)与本用户关联信息
    @Override
    public RelateInfo getTeamRelateInfo(PsyConsultantTeamSupervision req){
        RelateInfo relateInfo = new RelateInfo();
        PsyConsultantOrder orderReq = new PsyConsultantOrder();
            orderReq.setPayConsultantId(req.getConsultantId());
            orderReq.setServerType("1");//团督
            orderReq.setServerId(req.getId()+"");
            orderReq.setStatus("2");//订单已完成
        List<PsyConsultantOrder> orderList = psyConsultantOrderMapper.selectPsyConsultantOrderList(orderReq);
        if (ObjectUtils.isNotEmpty(orderList)){
            relateInfo.setIsBuy(1);//已购
        }else {
            relateInfo.setIsBuy(0);//未购
        }
        return relateInfo;
    }

    //刷新缓存
    @Override
    public void refreshCacheByIdList(List<Long> idList){
        redisCache.deleteMultiCache(CacheConstants.TEAM_SUP_BY_ID_KEY,idList);
        for (Long id : idList) {
            self.selectPsyConsultantTeamSupervisionById(id);
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
        List<Long> teamIdList = psyConsultantTeamSupervisionMapper.selectList(new LambdaQueryWrapper<PsyConsultantTeamSupervision>()
                .select(PsyConsultantTeamSupervision::getId)
                .orderByDesc(PsyConsultantTeamSupervision::getCreateTime)).stream().map(p -> p.getId()).collect(Collectors.toList());

        //刷新byId缓存
        refreshCacheByIdList(teamIdList);
        refreshIdList();
    }
    
    //刷新该对象 各种类型下的id清单
    @Override
    public void refreshIdList(){
        //完整对象清单
        List<PsyConsultantTeamSupervision> allTeamList = psyConsultantTeamSupervisionMapper.selectList(new LambdaQueryWrapper<PsyConsultantTeamSupervision>()
                .select(PsyConsultantTeamSupervision::getId)
                .orderByDesc(PsyConsultantTeamSupervision::getCreateTime));

        //删除原先的所有idList
        //redisCache.deleteStartWith(CacheConstants.TEAM_SUP_ID_LIST);
        
        //id清单放入缓存
        ////完整id清单
        List<Long> allIdList = allTeamList.stream().map(p -> p.getId()).collect(Collectors.toList());
        redisCache.setCacheList(CacheConstants.TEAM_SUP_ID_LIST + "::" + "all",allIdList);
    }

}
