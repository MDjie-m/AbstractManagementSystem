package com.renxin.psychology.service.impl;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.renxin.common.constant.PsyConstants;
import com.renxin.common.core.domain.model.LoginUser;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.utils.SecurityUtils;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.mapper.PsyConsultMapper;
import com.renxin.psychology.mapper.PsyConsultantSupervisionMemberMapper;
import com.renxin.psychology.service.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantTeamSupervisionMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 团队督导(组织)Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
public class PsyConsultantTeamSupervisionServiceImpl implements IPsyConsultantTeamSupervisionService 
{
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

    /**
     * 查询团队督导(组织)
     * 
     * @param id 团队督导(组织)主键
     * @return 团队督导(组织)
     */
    @Override
    @Cacheable(value = "selectPsyConsultantTeamSupervisionByIdCache", key = "#id",
            unless = "#result == null")
    public PsyConsultantTeamSupervision selectPsyConsultantTeamSupervisionById(Long id)
    {
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
        
        
        return team;
    }

    /**
     * 查询团队督导(组织)列表
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 团队督导(组织)
     */
    @Override
    /*@Cacheable(value = "selectPsyConsultantTeamSupervisionListCache", key = "#req.idList",
            unless = "#result == null||#result.isEmpty()")*/
    public List<PsyConsultantTeamSupervision> selectPsyConsultantTeamSupervisionList(PsyConsultantTeamSupervision req)
    {
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
        //查询"个人督导"或"个人体验"时, 直接查询咨询师数据
            teamList = psyConsultantTeamSupervisionMapper.selectPsyConsultantPersonSupervisionList(req);
        }
        
        return teamList;
    }

    /**
     * 新增团队督导(组织)
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPsyConsultantTeamSupervision(PsyConsultantTeamSupervision psyConsultantTeamSupervision)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        psyConsultantTeamSupervision.setCreateBy(loginUser.getUserId()+"");
        psyConsultantTeamSupervision.setUpdateBy(loginUser.getUserId()+"");
        psyConsultantTeamSupervision.setCreateTime(DateUtils.getNowDate());
        psyConsultantTeamSupervision.setUpdateTime(DateUtils.getNowDate());
        
        clearColumn(psyConsultantTeamSupervision);
        return psyConsultantTeamSupervisionMapper.insertPsyConsultantTeamSupervision(psyConsultantTeamSupervision);
    }

    /**
     * 修改团队督导(组织)
     * 
     * @param psyConsultantTeamSupervision 团队督导(组织)
     * @return 结果
     */
    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "selectPsyConsultantTeamSupervisionListCache", allEntries = true),
                    @CacheEvict(cacheNames = "selectPsyConsultantTeamSupervisionByIdCache", key = "#id"),
            }
    )
    public int updatePsyConsultantTeamSupervision(PsyConsultantTeamSupervision psyConsultantTeamSupervision)
    {
        psyConsultantTeamSupervision.setUpdateTime(DateUtils.getNowDate());
        clearColumn(psyConsultantTeamSupervision);
        return psyConsultantTeamSupervisionMapper.updatePsyConsultantTeamSupervision(psyConsultantTeamSupervision);
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
            team.setFirstLectureDate(null);
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
    @Caching(
        evict = {
                @CacheEvict(cacheNames = "selectPsyConsultantTeamSupervisionListCache", allEntries = true),
                @CacheEvict(cacheNames = "selectPsyConsultantTeamSupervisionByIdCache", allEntries = true),
        }
    )
    public int deletePsyConsultantTeamSupervisionByIds(Long[] ids)
    {
        return psyConsultantTeamSupervisionMapper.deletePsyConsultantTeamSupervisionByIds(ids);
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
                    @CacheEvict(cacheNames = "selectPsyConsultantTeamSupervisionListCache", allEntries = true),
                    @CacheEvict(cacheNames = "selectPsyConsultantTeamSupervisionByIdCache", key = "#id"),
            }
    )
    public int deletePsyConsultantTeamSupervisionById(Long id)
    {
        return psyConsultantTeamSupervisionMapper.deletePsyConsultantTeamSupervisionById(id);
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
    
}
