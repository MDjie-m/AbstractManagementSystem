package com.renxin.psychology.service.impl;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyConsultantScheduleService;
import com.renxin.psychology.service.IPsyConsultantSupervisionMemberService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantTeamSupervisionMapper;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
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

    /**
     * 查询团队督导(组织)
     * 
     * @param id 团队督导(组织)主键
     * @return 团队督导(组织)
     */
    @Override
    public PsyConsultantTeamSupervision selectPsyConsultantTeamSupervisionById(Long id)
    {
        PsyConsultantTeamSupervision team = psyConsultantTeamSupervisionMapper.selectPsyConsultantTeamSupervisionById(id);
        

        //若为[团队督导]
        if (team.getTeamType() == 1){
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
                team.setSurplusNum(maxNumPeople - memberNum);
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
    public List<PsyConsultantTeamSupervision> selectPsyConsultantTeamSupervisionList(PsyConsultantTeamSupervision psyConsultantTeamSupervision)
    {
        List<PsyConsultantTeamSupervision> teamList = psyConsultantTeamSupervisionMapper.selectPsyConsultantTeamSupervisionList(psyConsultantTeamSupervision);

        for (PsyConsultantTeamSupervision team : teamList) {
            //最大团队人数
            Integer maxNumPeople = team.getMaxNumPeople();
            if (ObjectUtils.isNotEmpty(maxNumPeople)){
                //当前登记人数
                int memberNum = memberMapper.queryMemberCount(team.getId() + "");
                //剩余名额数
                team.setSurplusNum(maxNumPeople - memberNum);
            }
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
            team.setSurplusNum(null);
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
        return psyConsultantTeamSupervisionMapper.deletePsyConsultantTeamSupervisionByIds(ids);
    }

    /**
     * 删除团队督导(组织)信息
     * 
     * @param id 团队督导(组织)主键
     * @return 结果
     */
    @Override
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
            if (team.getSurplusNum() <= 0){
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
        List<String> nextNDays = getNextNDays(team.getCycleNumber().intValue(), team.getWeekDay());//获取开课日期清单
        team.setFirstLectureDate(nextNDays.get(0));//初次开课日期
        team.setStatus(ConsultConstant.TEAM_STATUS_START_1);
        psyConsultantTeamSupervisionMapper.updatePsyConsultantTeamSupervision(team);
        
        //为督导师添加排程
        PsyConsultantSchedule schedule = new PsyConsultantSchedule();
        schedule.setTeamId(teamId);
        schedule.setTimeStart(team.getLectureStartTime());
        schedule.setTimeEnd(team.getLectureEndTime());
        schedule.setWeek(getWeekday(team.getWeekDay()));
        schedule.setTime(Long.parseLong(team.getLectureStartTime().substring(0,2)));
        schedule.setConsultId(Long.valueOf(team.getConsultantId()));
        schedule.setScheduleType(2);//2.团督开课
        schedule.setCreateTime(new Date());
        schedule.setUpdateTime(new Date());
        schedule.setCreateBy("system");
        schedule.setUpdateBy("system");
        int timeNum = 1;
        for (String lectureDay : nextNDays) {
            schedule.setDay(lectureDay);
            schedule.setRealTime(lectureDay + " " + team.getLectureStartTime());
            schedule.setTimeNum(timeNum++);
            consultantScheduleService.insertPsyConsultantSchedule(schedule);
        }
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
