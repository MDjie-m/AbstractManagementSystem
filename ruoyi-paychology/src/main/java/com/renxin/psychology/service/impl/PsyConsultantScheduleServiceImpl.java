package com.renxin.psychology.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.mapper.PsyConsultantScheduleMapper;
import com.renxin.psychology.service.IPsyConsultantOrderService;
import com.renxin.psychology.service.IPsyConsultantScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Integer usedNum = order.getUsedNum();//已用次数
        Integer surplusNum = order.getSurplusNum();//剩余次数
        
        if (surplusNum < consultantScheduleList.size()){
            throw new ServiceException("该订单剩余[咨询次数]不足, 当前剩余" + surplusNum +"次");
        }
        
        sortSchedule(consultantScheduleList);//按预约时间排序
        for (PsyConsultantSchedule schedule : consultantScheduleList) {
            schedule.setWeek(getWeekByDay(schedule.getDay()));
            schedule.setScheduleType(2);//2.咨询服务
            schedule.setTimeNum(++usedNum);//第几次执行
        }
        psyConsultantScheduleMapper.insertPsyConsultantScheduleBatch(consultantScheduleList);
        
        //work占用
        
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
    
    
    
}
