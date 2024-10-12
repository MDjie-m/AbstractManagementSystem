package com.renxin.common.wxMsg;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
//import com.yj.commons.tools.utils.DateUtils;
//import com.yj.commons.tools.utils.JsonUtil;
//import com.yj.commons.tools.utils.StringUtil;
//import com.yj.notice.message.AlarmMessage;
//import com.yj.notice.message.NoticeMessage;
//import com.yj.notice.service.MessageService;
import com.github.pagehelper.util.StringUtil;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.redis.RedisCache;
import com.renxin.common.exception.ServiceException;
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.wechat.vo.TemplateMessageItemVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import static com.yj.commons.tools.utils.DateUtils.DATE_TIME_PATTERN;

/**
 * @author : dyg
 * @className : WxMessageServiceImpl
 * @description : 描述说明该类的功能
 * @date : 2023/9/5 9:35
 */
@Service("WechatMessageService")
@Slf4j
@Data
public class WxMsgUtils implements MessageService  {
    /**
     * 账号app_id
     */
    @Value(value = "${wechat.appid}")
    private String appid;
    /**
     * 账户密钥
     */
    @Value(value = "${wechat.secret}")
    private String secret;

    /**
     * 获取token
     * "+ appId +"&secret=" + appIdSecret
     */
    @Value(value = "${wechat.token_uri}")
    private String tokenUri;
    
    /**
     * 用户清单链接;
     */
    @Value(value = "${wechat.user_list_uri}")
    private String userListUri;
    
    /**
     * 发送订阅消息链接;
     */
    @Value(value = "${wechat.send_subscribe_message_uri}")
    private String sendSubscribeMessageUri;

    /**
     * 咨询即将开始 - 消息模板id
     */
    @Value(value = "${wechat.consult_start_template_id}")
    private String consultStartTemplateId;
    /**
     * 预约咨询成功 - 消息模板id
     */
    @Value(value = "${wechat.schedule_success_template_id}")
    private String scheduleSuccessTemplateId;

    /**
     * 预约咨询请假 - 消息模板id
     */
    @Value(value = "${wechat.schedule_leave_template_id}")
    private String scheduleLeaveTemplateId;

    /**
     * 未读消息 - 消息模板id
     */
    @Value(value = "${wechat.unread_msg_template_id}")
    private String unreadMsgTemplateId;

    @Resource
    private IPsyConsultService psyConsultService;

    @Resource
    private IPsyUserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private RedisCache redisCache;

    private Long tokenFreshTimeSt = 0L;

    //@Override
    public String send(NoticeMessage message) {
        String result = null;
        try {
            result = this.sendMessage(message);
        }catch (Exception e){
            log.error(e.getMessage());
            result = e.getMessage()+ " " + e;
        }
        if (StringUtil.isNotEmpty(result)) {
            log.error(result);
        } else {
            result = "发送成功";
        }
        return result;
    }

    //@Override
    public String getNoticeMethod() {
        return NoticeMethodEnum.WECHAT.getName();
    }

    /**
     * 获取或者刷新token
     */
/*    private void getOrRefreshToken() {
        try {
            String requestUrl = this.tokenUri + this.appid +"&secret=" + this.secret;
            String res = HttpUtil.get(requestUrl);
            JSONObject jsonObject = JSONObject.parseObject(res);
            String accessToken = jsonObject.getString("access_token");
        } catch (Exception e) {
            log.error("---获取token出现异常{} {} ",e.getMessage(),e);
        }
    }*/

    /**
     * 获取Token
     */
    public String getAccessToken() {
        
        String access_token = redisCache.getCacheObject(Constants.WECHAT_PROGRAM_ACCESS_TOKEN_KEY);

        if (access_token != null) {
            return access_token;
        } else {
            String requestUrl = this.tokenUri + this.appid +"&secret=" + this.secret;
            String res = HttpUtil.get(requestUrl);
            JSONObject jsonObject = JSONObject.parseObject(res);
            String accessToken = jsonObject.getString("access_token");
            Integer expires_in = jsonObject.getInteger("expires_in");
            // 更新缓存
            redisCache.setCacheObject(Constants.WECHAT_PROGRAM_ACCESS_TOKEN_KEY, access_token, expires_in,
                    TimeUnit.SECONDS);
            return access_token;
        }
    }

    /**
     * 获取用户列表openid
     */
    public void getUserList(){
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = this.userListUri + getAccessToken();
        ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, null, String.class);
        log.info("结果是: {}",response.getBody());
        com.alibaba.fastjson2.JSONObject result = com.alibaba.fastjson2.JSONObject.parseObject(response.getBody());
        com.alibaba.fastjson2.JSONArray openIdJsonArray = result.getJSONObject("data").getJSONArray("openid");
        Iterator iterator = openIdJsonArray.iterator();
        if (iterator.hasNext()){
            log.debug("用户openid："+iterator.next());
        }
    }

    @Data
    public class WeChatTemplateMsg {
        /**
         * 消息
         */
        private String value;
        /**
         * 消息颜色
         */
        private String color;

        public WeChatTemplateMsg(String value) {
            this.value = value;
            this.color = "#173177";
        }

        public WeChatTemplateMsg(String value, String color) {
            this.value = value;
            this.color = color;
        }
    }

    /**
     * 获取用户id
     * @param alarmMessage
     * @return
     */
    private JSONObject getWechatUserId(NoticeMessage alarmMessage){
        JSONObject result = new JSONObject();
        String openId = alarmMessage.getReceiverId();
        if(StringUtil.isEmpty(openId)){
            // todo 根据手机号码获取微信id -- 对应数据估计得手动维护
            String receiverPhone = alarmMessage.getReceiverPhone();
            if(StringUtil.isEmpty(receiverPhone)){
                result.put("message","消息接收者手机号码+微信id都为空，消息无法发送");
            }
            openId = getWechatUserId(receiverPhone);
        }
        result.put("userId",openId);
        return result;
    }

    /**
     * 发送消息
     * @param noticeMessage
     * @return
     */
    public String sendMessage(NoticeMessage noticeMessage){
        String result = null;
        
        // openId代表一个唯一微信用户，即微信消息的接收人
        String openId = noticeMessage.getReceiverId();
       /*// JSONObject wechatUserId = getWechatUserId(noticeMessage);
        if(wechatUserId.containsKey("message")){
            String message = wechatUserId.getString("message");
            log.error(message);
            return message;
        }else{
            Object userId = wechatUserId.get("userId");
            if(Objects.isNull(userId)){
                String message = "未能根据手机号码"+noticeMessage.getReceiverPhone()+"成功获取用户的微信id";
                return message;
            }
            openId = wechatUserId.getString("userId");
        }*/
        String requestUrl = this.sendSubscribeMessageUri + getAccessToken();

        // 消息模板参数
        Map<String, TemplateMessageItemVo> sendMsg = noticeMessage.getMsgMap();
        String time = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        sendMsg.put("content", new TemplateMessageItemVo(noticeMessage.getContent()));
        sendMsg.put("title",new TemplateMessageItemVo(noticeMessage.getTitle()));
        sendMsg.put("time",new TemplateMessageItemVo(time));
        
       /* sendMsg.put("time1",new WeChatTemplateMsg("2024-05-05"));
        sendMsg.put("thing2",new WeChatTemplateMsg("aaa"));
        sendMsg.put("thing3",new WeChatTemplateMsg("bbb"));
        sendMsg.put("thing4",new WeChatTemplateMsg("ccc"));
        sendMsg.put("thing5",new WeChatTemplateMsg("ddd"));
        sendMsg.put("date3",new WeChatTemplateMsg("2019/10/14"));
        sendMsg.put("time60",new WeChatTemplateMsg("2022年04月15日 13:00~14:00"));
        sendMsg.put("thing94",new WeChatTemplateMsg("李老师"));
        sendMsg.put("thing7",new WeChatTemplateMsg("ddd"));*/
        
        //拼接base参数
        Map<String, Object> sendBody = new HashMap<>();
        sendBody.put("touser", openId);
        sendBody.put("data", sendMsg);
        sendBody.put("appid", appid);
        sendBody.put("template_id", getTempIdByMsgType(noticeMessage.getMessageType()));

        try {
            // 1.创建httpclient对象
            CloseableHttpClient client = HttpClients.createDefault();
            // 2.创建post对象
            HttpPost post = new HttpPost(requestUrl);
            //HttpGet post = new HttpGet(requestUrl);
            StringEntity postingString = new StringEntity(JSONObject.toJSONString(sendBody), "utf-8");
            post.setEntity(postingString);
            // 3.执行post方法:得到结果
            CloseableHttpResponse response = client.execute(post);
            // 4.处理结果
            //  1.得到状态码
            int statusCode = response.getStatusLine().getStatusCode();
            log.info("----http code : {}", statusCode);
            if (statusCode == 200) {
                //  2.得到实体内容
                org.apache.http.HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf-8");
                JSONObject jsonObject = JSONObject.parseObject(content, JSONObject.class);
                String messageCode = jsonObject.getString("errcode");
                String msgId = jsonObject.getString("msgid");
                String errmsg = jsonObject.getString("errmsg");
                result = "messageCode : " + messageCode + ", msgId: " +msgId + ", errmsg: " + errmsg;
            }
            //  5.关闭连接
            client.close();
        } catch (IOException e) {
            log.error("------exception ： {} {} ",e.getMessage(),e);
        }
        return result;
    }
    
    //根据消息类型获取TempId
    private String getTempIdByMsgType(String msgType){
        switch (msgType){
            case Constants.MSG_CONSULT_START:
                return consultStartTemplateId;
            case Constants.MSG_SCHEDULE_SUCCESS:
                return scheduleSuccessTemplateId;
            case Constants.MSG_SCHEDULE_LEAVE:
                return scheduleLeaveTemplateId;
            case Constants.MSG_UNREAD_MSG:
                return unreadMsgTemplateId;
            default:
                throw new ServiceException("获取消息类型templateId失败, msgType:" + msgType);
        }
    }

    /**
     * 根据手机号码获取用户id
     * @param receiverPhone
     * @return
     */
    private String getWechatUserId(String receiverPhone) {
        String userId = null;
        return userId;
    }

    /**
     * 验证并刷新token
     */
/*    private void validateToken() {
        if (StringUtil.isEmpty(this.enterpriseToken)) {
            this.getOrRefreshToken();
        }
        Long now = System.currentTimeMillis()/1000;
        if(this.tokenFreshTimeSt == 0L){
            this.getOrRefreshToken();
        }else{
            Long diff = (now - tokenFreshTimeSt)/60;
            if(diff > 10){
                // 超过十分钟重新获取一下
                this.getOrRefreshToken();
            }
        }
    }*/
    
    public String getOpenId(Long cId) {
        PsyUser psyUser = userService.selectPsyUserById(cId);
        if (ObjectUtils.isEmpty(psyUser)){
            throw new ServiceException("该用户id获取不到相应的openid");
        }
        return psyUser.getWxOpenid();
    }
}