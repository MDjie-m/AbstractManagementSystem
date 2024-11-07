package com.renxin.psychology.task;

import com.renxin.common.constant.Constants;
import com.renxin.common.dcloud.CloudFunctions;
import com.renxin.common.wechat.wxMsg.NoticeMessage;
import com.renxin.common.wechat.wxMsg.NoticeMethodEnum;
import com.renxin.common.wechat.wxMsg.TemplateMessageItemVo;
import com.renxin.common.wechat.wxMsg.WxMsgUtils;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.domain.PsyConsultantSchedule;
import com.renxin.psychology.domain.PsyConsultantSupervisionMember;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.service.*;
import com.renxin.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("consultOrderTask")
public class OrderTask {

    @Resource
    private ISysConfigService configService;

    @Resource
    private IPsyConsultOrderService psyConsultOrderService;
    
    @Resource
    private IPsyConsultOrderItemService orderItemService;

    @Resource
    private WxMsgUtils wxMsgUtils;
    
    @Resource
    private IPsyUserService userService;

    @Autowired
    private IPsyConsultService consultService;
    
    @Resource
    private IPsyConsultantScheduleService scheduleService;

    @Autowired
    private IPsyConsultantSupervisionMemberService memberService;

    public void cancel()
    {
        String val = configService.selectConfigByKey("order.cancel.time");
        int num = StringUtils.isNotEmpty(val) ? Integer.parseInt(val) : 15;
        List<PsyConsultOrder> cancelList = psyConsultOrderService.getCancelList(num);
        if (CollectionUtils.isNotEmpty(cancelList)) {
            cancelList.forEach(order -> {
                psyConsultOrderService.cancel(order, "job");
            });
            List<Long> collect = cancelList.stream().map(PsyConsultOrder::getId).collect(Collectors.toList());
            log.info("咨询订单取消, 订单id={} 自动修改订单状态为已取消，操作已完成", collect);
        }
    }

    //咨询开始前1小时, 发送通知消息
    public void sendNoticeMsg()
    {
        CloudFunctions cloudFunctions = new CloudFunctions();
        //获取1小时后的时间
        String oneHourLater  = java.time.LocalDateTime.now().plusHours(1).withMinute(0).format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm"));
        String day = oneHourLater.split("\\|")[0];
        String time = oneHourLater.split("\\|")[1];
        
        //查询1小时后需要执行的咨询预约清单
        OrderItemDTO orderItemReq = new OrderItemDTO();
        orderItemReq.setDay(day);
        orderItemReq.setTimeStart(time);
        List<OrderItemDTO> orderItemList = orderItemService.queryOrderItemList(orderItemReq);
        for (OrderItemDTO item : orderItemList) {
            //发送微信小程序订阅消息--顾客
            NoticeMessage notice = new NoticeMessage();
            notice.setMessageType(Constants.MSG_CONSULT_START);//咨询将开始
            notice.setNoticeMethod(NoticeMethodEnum.WECHAT);
            notice.setReceiverId(userService.getOpenId(item.getUserId()));
            //notice.setReceiverId("oP8146998AoIjkNMZx4s2vK4me5w");

            HashMap<String, TemplateMessageItemVo> msgMap = new HashMap<>();
            oneHourLater = oneHourLater.replace("|", " ");
            msgMap.put("time1", new TemplateMessageItemVo(oneHourLater));//预约时间
            msgMap.put("thing2", new TemplateMessageItemVo(item.getConsultantName()));//被咨询人
            msgMap.put("thing3", new TemplateMessageItemVo(item.getUserNickName()));//咨询人
            msgMap.put("thing4", new TemplateMessageItemVo("视频咨询即将开始"));//预约服务
            msgMap.put("thing5", new TemplateMessageItemVo("您有一个咨询预约将在1小时内开始, 请准时上线"));//温馨提示

            notice.setMsgMap(msgMap);
            wxMsgUtils.send(notice);
            
            //todo通知--  该预约的咨询师
            NoticeMessage consultantNotice = new NoticeMessage();
                consultantNotice.setPush_clientid(consultService.getClientIdByConsultantId(item.getConsultId()));
                consultantNotice.setTitle("预约服务即将开始");
                consultantNotice.setContent("您有一个咨询预约将在" + oneHourLater + "开始, 请准时上线, 客户:" + item.getUserNickName());
            cloudFunctions.sendGeTuiMessage(consultantNotice);
        }
        
        //todo通知--  查询scheduleList
        PsyConsultantSchedule scheduleReq = new PsyConsultantSchedule();
            scheduleReq.setDay(day);
            scheduleReq.setTime(Integer.parseInt(time));
            scheduleReq.setStatus("0");
        List<PsyConsultantSchedule> scheduleList = scheduleService.selectPsyConsultantScheduleList(scheduleReq);

        for (PsyConsultantSchedule schedule : scheduleList) {
            //团队活动
            if (schedule.getScheduleType() == 21){
                //通知督导师
                NoticeMessage consultantNotice = new NoticeMessage();
                    consultantNotice.setPush_clientid(consultService.getClientIdByConsultantId(schedule.getConsultId()));
                    consultantNotice.setTitle("团队活动即将开始");   
                    consultantNotice.setContent("您名下的团队[" +schedule.getServerName()+  "]将在" + schedule.getTimeStart() + "开始活动, 请准时上线.");
                cloudFunctions.sendGeTuiMessage(consultantNotice);

                //查询该团督的成员清单
                PsyConsultantSupervisionMember memberReq = new PsyConsultantSupervisionMember();
                    memberReq.setTeamSupervisionId(schedule.getTeamId());
                List<PsyConsultantSupervisionMember> memberList = memberService.selectPsyConsultantSupervisionMemberList(memberReq);
                //通知成员
                for (PsyConsultantSupervisionMember member : memberList) {
                    if (member.getMemberUserType() == 1){//来访者
                        NoticeMessage notice = new NoticeMessage();
                        notice.setMessageType(Constants.MSG_CONSULT_START);//预约咨询将开始
                        notice.setNoticeMethod(NoticeMethodEnum.WECHAT);
                        notice.setReceiverId(userService.getOpenId(member.getMemberId()));

                        HashMap<String, TemplateMessageItemVo> msgMap = new HashMap<>();
                        msgMap.put("time1", new TemplateMessageItemVo(schedule.getTimeStart()));//预约时间
                        msgMap.put("thing2", new TemplateMessageItemVo());//被咨询人
                        msgMap.put("thing3", new TemplateMessageItemVo());//咨询人
                        msgMap.put("thing4", new TemplateMessageItemVo("团队活动即将开始"));//预约服务
                        msgMap.put("thing5", new TemplateMessageItemVo("您参与的团队[" + schedule.getServerName() + "]将在" + 
                                schedule.getTimeStart()  + "开始活动, 请准时上线."));//温馨提示
                        
                        notice.setMsgMap(msgMap);
                        wxMsgUtils.send(notice);
                    }
                    if (member.getMemberUserType() == 2){//咨询师
                        consultantNotice.setContent("您参与的团队[" + schedule.getServerName() + "]将在" + schedule.getTimeStart()  + "活动, 请准时上线.");
                        consultantNotice.setPush_clientid(consultService.getClientIdByConsultantId(member.getMemberId()));
                        cloudFunctions.sendGeTuiMessage(consultantNotice);
                    }
                    
                }
            }
            //个督/体验
            if (schedule.getScheduleType() == 22 || schedule.getScheduleType() == 23){
                //通知收费咨询师
                NoticeMessage consultantNotice = new NoticeMessage();
                    consultantNotice.setPush_clientid(consultService.getClientIdByConsultantId(schedule.getConsultId()));
                    consultantNotice.setTitle("预约督导即将开始");
                    consultantNotice.setContent("客户[" + schedule.getUserNickName() +  "]向您预约的督导服务将在" + schedule.getTimeStart() + "开始, 请准时上线.");
                cloudFunctions.sendGeTuiMessage(consultantNotice);
                
                //通知付费咨询师
                consultantNotice.setPush_clientid(consultService.getClientIdByConsultantId(Long.valueOf(schedule.getCreateBy())));
                consultantNotice.setContent("您向[" + consultService.getNameByConsultantId(schedule.getConsultId()) + "]预约的督导服务将在" + 
                        schedule.getTimeStart() + "开始, 请准时上线.");
                cloudFunctions.sendGeTuiMessage(consultantNotice);
            }
            
        }
    }

}
