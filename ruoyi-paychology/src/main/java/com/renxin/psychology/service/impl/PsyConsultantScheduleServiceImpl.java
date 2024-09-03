package com.renxin.psychology.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.mapper.PsyConsultantScheduleMapper;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.request.PsyWorkTimeRes;
import com.renxin.psychology.service.IPsyConsultWorkService;
import com.renxin.psychology.service.IPsyConsultantOrderService;
import com.renxin.psychology.service.IPsyConsultantScheduleService;
import com.renxin.psychology.vo.PsyConsultWorkVO;
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
    public List<PsyConsultantSchedule> selectPsyConsultantScheduleList(PsyConsultantSchedule psyConsultantSchedule)
    {
        return psyConsultantScheduleMapper.selectPsyConsultantScheduleList(psyConsultantSchedule);
    }

    /**
     * 新增咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    @Override
    public int insertPsyConsultantSchedule(PsyConsultantSchedule psyConsultantSchedule)
    {
        psyConsultantSchedule.setCreateTime(DateUtils.getNowDate());
        return psyConsultantScheduleMapper.insertPsyConsultantSchedule(psyConsultantSchedule);
    }

    @Override
    public int insertPsyConsultantScheduleList(List<PsyConsultantSchedule> list)
    {
        return psyConsultantScheduleMapper.insertPsyConsultantScheduleBatch(list);
    }
    

    /**
     * 修改咨询师排班任务
     * 
     * @param psyConsultantSchedule 咨询师排班任务
     * @return 结果
     */
    @Override
    public int updatePsyConsultantSchedule(PsyConsultantSchedule psyConsultantSchedule)
    {
        psyConsultantSchedule.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantScheduleMapper.updatePsyConsultantSchedule(psyConsultantSchedule);
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
        
        if (!consultantScheduleList.get(0).getCreateBy().equals(order.getPayConsultantId())){
            throw new ServiceException("该订单不属于当前登录人");
        }
        if (surplusNum < consultantScheduleList.size()){
            throw new ServiceException("该订单剩余[咨询次数]不足, 当前剩余" + surplusNum +"次");
        }
        
        sortSchedule(consultantScheduleList);//按预约时间排序
        for (PsyConsultantSchedule schedule : consultantScheduleList) {
            PsyConsultWorkVO work = psyConsultWorkService.getOne(schedule.getWorkId());
            if (!chargeConsultantId.equals(work.getConsultId()+"")){
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
            schedule.setTimeEnd(addZero(time)+":50");
            //schedule.setTimeNum(++usedNum);//第几次执行
            schedule.setStatus("0");//待办
            schedule.setRealTime(schedule.getDay() + " " + schedule.getTimeStart());
            schedule.setConsultId(Long.valueOf(chargeConsultantId));
            
            schedule.setCreateTime(new Date());
            schedule.setUpdateTime(new Date());
        }
        //添加日程安排
        psyConsultantScheduleMapper.insertPsyConsultantScheduleBatch(consultantScheduleList);
        
        //若订单的[剩余次数]被用完, 则修改订单状态
        if (surplusNum == consultantScheduleList.size()){
            order.setStatus(ConsultConstant.CONSULT_ORDER_STATUE_FINISHED);//已完成
            consultantOrderService.updatePsyConsultantOrder(order);
        }
        
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
    public int getTimeNumForConsultant(PsyWorkReq req){
        return psyConsultantScheduleMapper.getTimeNumForConsultant(req);
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

        req.setScheduleType(21);//团督
        workTimeRes.setTeamSupTime(psyConsultantScheduleMapper.querySumTime(req));
        req.setScheduleType(22);//个督
        workTimeRes.setPersonSupTime(psyConsultantScheduleMapper.querySumTime(req));
        req.setScheduleType(23);//体验
        workTimeRes.setPersonExpTime(psyConsultantScheduleMapper.querySumTime(req));
        req.setScheduleType(12);//咨询
        workTimeRes.setConsultTime(psyConsultantScheduleMapper.querySumTime(req));
        req.setScheduleType(11);//倾听
        workTimeRes.setListenTime(psyConsultantScheduleMapper.querySumTime(req));
        
        return workTimeRes;
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
    
}
