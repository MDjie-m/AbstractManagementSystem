package com.renxin.psychology.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.renxin.common.dcloud.CloudFunctions;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.common.wechat.wxMsg.NoticeMessage;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.*;
import com.renxin.psychology.mapper.PsyConsultantScheduleMapper;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.request.PsyWorkTimeRes;
import com.renxin.psychology.service.*;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 咨询师排班任务Service业务层处理
 * 
 * @author renxin
 * @date 2024-07-25
 */
@Service
public class PsyConsultantScheduleServiceImpl implements IPsyConsultantScheduleService 
{
    @Autowired
    private PsyConsultantScheduleMapper psyConsultantScheduleMapper;
    
    @Autowired
    private IPsyConsultantOrderService consultantOrderService;

    @Resource
    private IPsyConsultWorkService psyConsultWorkService;

    @Resource
    private IPsyConsultServeService consultServeService;
    
    @Resource
    private IPsyConsultantTeamSupervisionService teamService;
    
    @Resource
    private IPsyConsultantSupervisionMemberService teamMemberService;
    
    @Autowired
    private IPsyConsultService consultService;
    
    @Resource
    private IPsyConsultPartnerItemService partnerItemService;
    
    @Resource
    private IPsyConsultOrderItemService orderItemService;

    /**
     * 查询咨询师排班任务
     * 
     * @param id 咨询师排班任务主键
     * @return 咨询师排班任务
     */
    @Override
    public PsyConsultantSchedule selectPsyConsultantScheduleById(Long id)
    {
        return psyConsultantScheduleMapper.selectPsyConsultantScheduleById(id);
    }

    /**
     * 查询咨询师排班任务列表
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 咨询师排班任务
     */
    @Override
    public List<PsyConsultantSchedule> selectPsyConsultantScheduleList(PsyConsultantSchedule req)
    {
        req.setOrderBy("real_time");
        req.setOrderDir("asc");
        return psyConsultantScheduleMapper.selectPsyConsultantScheduleList(req);
    }

    /**
     * 新增咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    @Override
    public int insertPsyConsultantSchedule(PsyConsultantSchedule schedule)
    {
        schedule.setCreateTime(DateUtils.getNowDate());
        int i = psyConsultantScheduleMapper.insertPsyConsultantSchedule(schedule);
        
        //todo通知-- 双方咨询师
        CloudFunctions cloudFunctions = new CloudFunctions();
        if (schedule.getScheduleType() != 21){////排除团督, 团督已在完成招生时进行了通知
            NoticeMessage notice = new NoticeMessage();
            ///通知收费咨询师
            notice.setPush_clientid(consultService.getClientIdByConsultantId(schedule.getConsultId()));
            notice.setTitle("预约督导通知");
            notice.setContent("[" + consultService.getNameByConsultantId(Long.valueOf(schedule.getCreateBy()))+ "]向您预约了" 
                    + schedule.getDay() + " " + schedule.getTimeStart() + "~" + schedule.getTimeEnd()
                    + "的督导服务, 请记得准时上线");
            cloudFunctions.sendGeTuiMessage(notice);
            ////通知付费咨询师
            notice.setPush_clientid(consultService.getClientIdByConsultantId(Long.valueOf(schedule.getCreateBy())));
            notice.setContent("您向[" + consultService.getNameByConsultantId(schedule.getConsultId())+ "]预约过"
                    + schedule.getDay() + " " + schedule.getTimeStart() + "~" + schedule.getTimeEnd()
                    + "的督导服务, 请记得准时上线");
            cloudFunctions.sendGeTuiMessage(notice);
        }
        
        
        return 1;
    }

    @Override
    public int insertPsyConsultantScheduleList(List<PsyConsultantSchedule> list)
    {
        return psyConsultantScheduleMapper.insertPsyConsultantScheduleBatch(list);
    }
    

    /**
     * 确认团督的预约讲课已完成
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePsyConsultantSchedule(PsyConsultantSchedule req)
    {
        req.setUpdateTime(DateUtils.getNowDate());
        //修改团督预约的状态
        int i = psyConsultantScheduleMapper.updatePsyConsultantSchedule(req);
        
        //刷新团督cache
        PsyConsultantSchedule schedule = selectPsyConsultantScheduleById(req.getId());
        teamService.refreshCacheById(schedule.getTeamId());

        // 若该团督的所有课程都已完成, 则
        // 将团督状态置为[已结束]
        // 将consultant_order中, 申请加入该团督的订单, 也都置为[已完成]
        PsyConsultantTeamSupervision team = teamService.selectPsyConsultantTeamSupervisionById(schedule.getTeamId());
        if (ObjectUtils.isEmpty(team)){
            throw new ServiceException("未查询到相关团督");
        }
        if (team.getSurplusNum() <= 0){
            team.setStatus(2);//已结束
            teamService.updatePsyConsultantTeamSupervision(team);
            teamService.refreshCacheById(schedule.getTeamId());
        }

        return i;
    }

    /**
     * 批量删除咨询师排班任务
     * 
     * @param ids 需要删除的咨询师排班任务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantScheduleByIds(Long[] ids)
    {
        return psyConsultantScheduleMapper.deletePsyConsultantScheduleByIds(ids);
    }

    /**
     * 删除咨询师排班任务信息
     * 
     * @param id 咨询师排班任务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantScheduleById(Long id)
    {
        return psyConsultantScheduleMapper.deletePsyConsultantScheduleById(id);
    }
    

    /**
     * 批量预约服务 (个督/体验)
     * @param consultantScheduleList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reservationServerBatch(List<PsyConsultantSchedule> consultantScheduleList){
        //校验剩余可用次数
        PsyConsultantOrder order = consultantOrderService.selectPsyConsultantOrderByOrderNo(consultantScheduleList.get(0).getOrderId() + "");
        Long chargeConsultantId = order.getChargeConsultantId();
        Integer usedNum = order.getUsedNum();//已用次数
        Integer surplusNum = order.getSurplusNum();//剩余次数
        
        if (!consultantScheduleList.get(0).getCreateBy().equals(order.getPayConsultantId()+"")){
            throw new ServiceException("该订单不属于当前登录人");
        }
        if (surplusNum < consultantScheduleList.size()){
            throw new ServiceException("该订单剩余[咨询次数]不足, 当前剩余" + surplusNum +"次");
        }
        
        //查询该项服务详情
        PsyConsultServeConfig serverDetail = consultServeService.getServerDetailByRelationId(order.getServerId());
        //每次服务耗时
        Integer timeCost = ObjectUtils.isEmpty(serverDetail.getTime()) ? 50 : serverDetail.getTime();

        sortSchedule(consultantScheduleList);//按预约时间排序
        for (PsyConsultantSchedule schedule : consultantScheduleList) {
            PsyConsultWorkVO work = psyConsultWorkService.getOne(schedule.getWorkId());
            if (!chargeConsultantId.equals(work.getConsultId())){
                throw new ServiceException("workId与咨询师不相符");
            }
            
            //work校验
            Integer time = schedule.getTime();//开始时间(整点)
            if (psyConsultWorkService.checkWork(schedule.getWorkId(),null,time) == false){
                throw new ServiceException(work.getDay() + " " + addZero(time) + ":00" + "该时刻已被预约, 请刷新后重新选择预约时段");
            }else{
                //work占用
                psyConsultWorkService.handleWork(schedule.getWorkId(), null , time, 1);
            }
            
            
            schedule.setDay(work.getDay());
            schedule.setWeek(work.getWeek());
            schedule.setScheduleType(Integer.valueOf("2"+order.getServerType()));
            schedule.setTimeStart(addZero(time)+":00");
            schedule.setTimeEnd(addZero(time)+ ":" + timeCost);
            //schedule.setTimeNum(++usedNum);//第几次执行
            schedule.setTotalNum(order.getTotalNum());
            schedule.setStatus("0");//待办
            schedule.setRealTime(schedule.getDay() + " " + schedule.getTimeStart());
            schedule.setConsultId(Long.valueOf(chargeConsultantId));
            
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
        }
        //添加日程安排
        psyConsultantScheduleMapper.insertPsyConsultantScheduleBatch(consultantScheduleList);
        
        //若订单的[剩余次数]被用完, 则修改订单状态
        /*if (surplusNum == consultantScheduleList.size()){
            order.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);//已完成
            consultantOrderService.updatePsyConsultantOrder(order);
        }*/
        
    }
    
    //将8转换为08
    private String addZero(Integer time){
        if (time<10){
            return "0"+time;
        }
        return time+"";
                
    }
    
    //按预约时间排序
    private void sortSchedule(List<PsyConsultantSchedule> consultantScheduleList){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Sort using stream and collect back to list
        consultantScheduleList = consultantScheduleList.stream()
                .sorted(Comparator.comparing((PsyConsultantSchedule s) -> LocalDate.parse(s.getDay(), dateFormatter))
                        .thenComparing(s -> LocalTime.parse(s.getTimeStart(), timeFormatter)))
                .collect(Collectors.toList());
    }
    
    //根据日期获取周几
    private String getWeekByDay(String day){
        // 定义日期格式
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 将字符串转为 LocalDate 对象
        LocalDate date = LocalDate.parse(day, dateFormatter);

        // 获取周几
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 把 DayOfWeek 转换为中文名称
        String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        return weekDays[dayOfWeek.getValue() - 1];  // 注意 DayOfWeek 的值是从1到7，对应周一到周日
    }

    /**
     * 查询本次任务, 是[收费咨询师-付费咨询师]之间的第几次
     * @param req
     * @return
     */
    @Override
    public int getTimeNum(PsyWorkReq req){
        return psyConsultantScheduleMapper.getTimeNum(req);
    }


    /**
     * 查询咨询师工作时长
     */
    @Override
    public PsyWorkTimeRes querySumTime(Long consultId){
        PsyConsultantSchedule req = new PsyConsultantSchedule();
            req.setConsultId(consultId);
        
        PsyWorkTimeRes workTimeRes = new PsyWorkTimeRes();
        workTimeRes.setConsultId(consultId);
            req.setScheduleType(21);//团队(讲师向外提供服务)
        workTimeRes.setTeamSupTime(psyConsultantScheduleMapper.querySumTime(req));
            req.setScheduleType(22);//个督
        workTimeRes.setPersonSupTime(psyConsultantScheduleMapper.querySumTime(req));
            req.setScheduleType(23);//体验
        workTimeRes.setPersonExpTime(psyConsultantScheduleMapper.querySumTime(req));
        PsyWorkReq psyWorkReq = new PsyWorkReq();//咨询
            psyWorkReq.setConsultId(consultId);
        workTimeRes.setConsultTime(orderItemService.getTimeNumForConsulted(psyWorkReq));
        //req.setScheduleType(11);//倾听
        //workTimeRes.setListenTime(psyConsultantScheduleMapper.querySumTime(req));
        
        req.setConsultId(null);
        req.setCreateBy(consultId+"");
        //购买团队时长
        workTimeRes.setBuyTeamSupTime(calcBuyTeamSupTime(consultId));
        //购买个督
            req.setScheduleType(22);
        workTimeRes.setBuyPersonSupTime(psyConsultantScheduleMapper.querySumTime(req));
        //购买个人体验
            req.setScheduleType(23);
        workTimeRes.setBuyPersonExpTime(psyConsultantScheduleMapper.querySumTime(req));
        

        PsyConsultPartnerItem itemReq = new PsyConsultPartnerItem();
            itemReq.setConsultantId(consultId);
        //服务时长: 个案咨询经历 + 本平台[提供]的团督时长 + 个督时长 + 个人体验 + 咨询
            itemReq.setType(5);//个案咨询经历
        int partnerWorkTime = partnerItemService.countTime(itemReq);
        int workTime = partnerWorkTime + workTimeRes.getTeamSupTime() + workTimeRes.getPersonSupTime() + workTimeRes.getPersonExpTime() + workTimeRes.getConsultTime();
        workTimeRes.setWorkTime(workTime);
        
        //督导时长: 接受督导经历 + 本平台[购买]的团队时长 + 个督时长
            itemReq.setType(6);//接受督导经历
        int partnerSupTime = partnerItemService.countTime(itemReq);
        int buySupTime = partnerSupTime + workTimeRes.getBuyTeamSupTime() + workTimeRes.getBuyPersonSupTime();
        workTimeRes.setBuySupTime(buySupTime);
        
        //体验时长: 接受体验经历 + 本平台[购买]的体验时长
            itemReq.setType(7);//个人体验经历
        int partnerExpTime = partnerItemService.countTime(itemReq);
        int buyExpTime = partnerExpTime + workTimeRes.getBuyPersonExpTime();
        workTimeRes.setBuyExpTime(buyExpTime);
        
        // 个案咨询经历  :  在外部平台,  为顾客提供的服务时长
        //入驻申请单中的记录   是购买别人服务的记录    需要统计时长.     再加上 , 在本平台购买别的人的服务, 且已完成的记录.
        //  督导:个案 + 团队     体验 : 个人体验
        // 个案咨询经历  :  在外部平台,  为顾客提供的服务时长
        // 接收督导经历  : 在外部平台 , 购买的个案督导服务时长
        // 个人体验经历    同上↑
        
        
        return workTimeRes;
    }
    
    //计算指定咨询师, 购买的团队时长
    private int calcBuyTeamSupTime(Long consultantId){
        //加入的团队清单( 团督/1V2督导 + 正式成员 )
        PsyConsultantSupervisionMember memberReq= new PsyConsultantSupervisionMember();
            memberReq.setMemberId(consultantId);
            memberReq.setMemberUserType(2);//咨询师
            memberReq.setTeamTypeList(Arrays.asList(1,2));//团督/1V2督导
            memberReq.setMemberType(1);//正式成员
        List<Long> teamIdList = teamMemberService.selectPsyConsultantSupervisionMemberList(memberReq)
                .stream().map(p -> p.getTeamSupervisionId()).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(teamIdList)){
            return 0;
        }
        
        //统计这些团队已完成多少小时的课程
        PsyConsultantSchedule req = new PsyConsultantSchedule();
            req.setTeamIdList(teamIdList);
            req.setScheduleType(21);//团队
        int buyTeamSupTime = psyConsultantScheduleMapper.querySumTime(req);
        return buyTeamSupTime;
    }

    //查询团督已讲完的课程数
    @Override
    public int getTimeNumForTeam(Long teamId){
        PsyConsultantSchedule req = new PsyConsultantSchedule();
        req.setTeamId(teamId);
        req.setStatus("1");//已完成
        int num = psyConsultantScheduleMapper.getTimeNumForTeam(req);
        return num;
    }


    //批量修改状态
    @Override
    public void updateStatusBatch(List<Long> idList, String status){
        if (ObjectUtils.isEmpty(idList)){
            return;
        }
        psyConsultantScheduleMapper.updateStatusBatch(idList,status);
        
        //团督讲课完成时, 给咨询师成员刷新cache
        
    }
}
