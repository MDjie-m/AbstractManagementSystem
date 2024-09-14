package com.renxin.webSocket;

import com.alibaba.fastjson2.JSON;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.domain.PersonInfo;
import com.renxin.common.domain.PsyMsgRecord;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.service.IPsyMsgRecordService;
import com.renxin.common.utils.StringUtils;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.psychology.vo.PsyConsultVO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 物理程序
 */
@Component
@Slf4j
public class MyWsHandler extends AbstractWebSocketHandler {
    private static Map<String, SessionBean> sessionBeanMap;
    private static AtomicInteger clientIdMaker;
    private static StringBuffer stringBuffer;
    
    @Autowired
    private ConsultantTokenService consultantTokenService;
    @Autowired
    private PocketTokenService pocketTokenService;
    @Autowired
    private IPsyMsgRecordService msgRecordService;
    @Autowired
    private IPsyConsultService consultService;
    @Autowired
    private IPsyUserService userService;
    
    //初始化
    static {
        sessionBeanMap = new ConcurrentHashMap<>();
        clientIdMaker = new AtomicInteger(0);
        stringBuffer = new StringBuffer();
    }
    //建立连接
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        SessionBean sessionBean = new SessionBean(session, clientIdMaker.getAndIncrement());
        
        Map<String, Object> reqMap = session.getAttributes();
        String userType = (String)reqMap.get("userType");
        String token = (String)reqMap.get("token");
        Long userId = 0L;
        if ("1".equals(userType)){//来访者
            LoginDTO loginUser = pocketTokenService.getLoginUser(token);
            if (ObjectUtils.isEmpty(loginUser)){ throw new ServiceException("token无效, 请重试");}
            userId = loginUser.getUserId();
        }else if("2".equals(userType)){//咨询师
            ConsultDTO loginUser = consultantTokenService.getLoginUser(token);
            if (ObjectUtils.isEmpty(loginUser)){ throw new ServiceException("token无效, 请重试");}
            userId = loginUser.getConsultId();
        }
        String userKey  = userType + "-" + userId;
        
        sessionBeanMap.put(userKey, sessionBean);
        log.info("成功建立连接:" + sessionBeanMap.get(userKey));
        //stringBuffer.append(sessionBeanMap.get(session.getId()).getClientId()+"进入了群聊<br/>");
        //sendMessage(sessionBeanMap);
    }

    
    //消息处理
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        //log.info(sessionBeanMap.get(session.getId()).getClientId()+":"+message.getPayload());
        String msgRecordJson = message.getPayload();
        PsyMsgRecord msgRecord = JSON.parseObject(msgRecordJson, PsyMsgRecord.class);
        //接收人
        String receiveUserKey = msgRecord.getReceiveUserType() + "-" + msgRecord.getReceiveUserId();

        //查询发送人信息
        Map<String, Object> attributes = session.getAttributes();
        String userType = (String)attributes.get("userType");
        String token = (String)attributes.get("token");
        Long userId = 0L;
        PersonInfo personInfo = new PersonInfo();
        if ("1".equals(userType)) {//来访者
            LoginDTO loginUser = pocketTokenService.getLoginUser(token);
            if (ObjectUtils.isEmpty(loginUser)){ throw new ServiceException("发送人token无效, 请重试");}
            userId = loginUser.getUserId();
            PsyUser psyUser = userService.selectPsyUserById(userId);
            BeanUtils.copyProperties(psyUser,personInfo);
            personInfo.setUserType(1);
        }else if("2".equals(userType)) {//咨询师
            ConsultDTO loginUser = consultantTokenService.getLoginUser(token);
            if (ObjectUtils.isEmpty(loginUser)){ throw new ServiceException("发送人token无效, 请重试");}
            userId = loginUser.getConsultId();
            PsyConsultVO consultant = consultService.getOne(userId);
            personInfo.setId(consultant.getId());
            personInfo.setAvatar(consultant.getAvatar());
            personInfo.setName(consultant.getNickName());
            personInfo.setUserType(2);
        }
        msgRecord.setSendBy(personInfo);
        msgRecordJson = JSON.toJSONString(msgRecord);
        
        //消息发送
        sendMessage(receiveUserKey, msgRecordJson);
        
        //消息记录
        msgRecord.setSendUserId(userId);
        msgRecord.setSendUserType(Integer.valueOf(userType));
        msgRecordService.insertPsyMsgRecord(msgRecord);
    }
    
    //异常时
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        if (session.isOpen()){
            session.close();
        }
        sessionBeanMap.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        int clientId = sessionBeanMap.get(session.getId()).getClientId();
        sessionBeanMap.remove(session);
        log.info(clientId+"关闭了连接");
       // stringBuffer.append(clientId+"退出了群聊<br/>");
       // sendMessageBatch(sessionBeanMap);
    }

    public void sendMessage(String receiceUserKey, String msgRecordJson){
            try {
                sessionBeanMap.get(receiceUserKey).getWebSocketSession().sendMessage(new TextMessage(msgRecordJson));
            } catch (Exception e) {
                log.error("webSocket消息发送出错:" + msgRecordJson);
            }
     
    }
    
    
    public void sendMessageBatch(Map<String,SessionBean> sessionBeanMap){
        for(String key:sessionBeanMap.keySet()){{
            try {
                sessionBeanMap.get(key).getWebSocketSession().sendMessage(new TextMessage(stringBuffer.toString()));
            } catch (Exception e) {
                log.info("出错啦");
            }
        }}
    }

//    //定时任务
//    @Scheduled(fixedDelay = 2000)//多少秒执行1次
//    public void sendMessage() throws Exception {
//        for (String key:sessionBeanMap.keySet()){
//            sessionBeanMap.get(key).getWebSocketSession().sendMessage(new TextMessage("：心跳"));
//        }
//    }
}