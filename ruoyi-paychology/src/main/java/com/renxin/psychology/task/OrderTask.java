package com.renxin.psychology.task;

import com.renxin.common.constant.Constants;
import com.renxin.common.wxMsg.NoticeMessage;
import com.renxin.common.wxMsg.NoticeMethodEnum;
import com.renxin.common.wxMsg.WxMsgUtils;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.service.IPsyConsultOrderItemService;
import com.renxin.psychology.service.IPsyConsultOrderService;
import com.renxin.system.service.ISysConfigService;
import com.renxin.wechat.vo.TemplateMessageItemVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
            //发送微信小程序订阅消息
            NoticeMessage notice = new NoticeMessage();
            notice.setMessageType(Constants.MSG_CONSULT_START);//咨询将开始
            notice.setNoticeMethod(NoticeMethodEnum.WECHAT);
            //notice.setReceiverId(wxMsgUtils.getOpenId(item.getUserId()));
            notice.setReceiverId("oP8146998AoIjkNMZx4s2vK4me5w");

            HashMap<String, TemplateMessageItemVo> msgMap = new HashMap<>();
            oneHourLater = oneHourLater.replace("|", " ");
            msgMap.put("time1", new TemplateMessageItemVo(oneHourLater));//预约时间
            msgMap.put("thing2", new TemplateMessageItemVo(item.getConsultantName()));//被咨询人
            msgMap.put("thing3", new TemplateMessageItemVo(item.getUserNickName()));//咨询人
            msgMap.put("thing4", new TemplateMessageItemVo("视频咨询"));//预约服务
            msgMap.put("thing5", new TemplateMessageItemVo("您有一个咨询将在1小时内开始"));//温馨提示

            notice.setMsgMap(msgMap);
            wxMsgUtils.send(notice);
        }
    }

}
