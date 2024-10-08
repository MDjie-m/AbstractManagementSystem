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
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
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

import java.io.IOException;
import java.util.*;

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
    private String bkAppKey;
    /**
     * 账户密钥
     */
    @Value(value = "${wechat.secret}")
    private String bkAppSecret;
    /**
     * 告警消息模板id
     */
    @Value(value = "${wechat.alarm_template_id}")
    private String alarmTemplateId;
    /**
     * 普通消息模板id
     */
    @Value(value = "${wechat.common_template_id}")
    private String commonTemplateId;
    /**
     * 获取token
     * "+ appId +"&secret=" + appIdSecret
     */
    @Value(value = "${wechat.token_uri}")
    private String tokenUri;
    /**
     * + accessToken;
     */
    @Value(value = "${wechat.user_list_uri}")
    private String userListUri;
    /**
     * + accessToken;
     */
    @Value(value = "${wechat.send_message_uri}")
    private String sendMessageUri;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 用户token
     */
    private String enterpriseToken = null;
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
    private void getOrRefreshToken() {
        try {
            String requestUrl = this.tokenUri + this.bkAppKey +"&secret=" + this.bkAppSecret;
            String res = HttpUtil.get(requestUrl);
            JSONObject jsonObject = JSONObject.parseObject(res);
            String accessToken = jsonObject.getString("access_token");
            this.enterpriseToken = accessToken;
            this.tokenFreshTimeSt = System.currentTimeMillis()/1000;
        } catch (Exception e) {
            log.error("---获取token出现异常{} {} ",e.getMessage(),e);
        }
    }

    /**
     * 获取用户列表openid
     */
    public void getUserList(){
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = this.userListUri+ this.enterpriseToken;
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
            // todo 根据手机号码获取微信id --对应数据估计得手动维护
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
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        // openId代表一个唯一微信用户，即微信消息的接收人
        JSONObject wechatUserId = getWechatUserId(noticeMessage);
        String openId = null;
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
        }
        // 公众号的模板id(也有相应的接口可以查询到)
        validateToken();
        String requestUrl = this.sendMessageUri + this.enterpriseToken;
        //拼接base参数
        Map<String, Object> sendBody = new HashMap<>();
        sendBody.put("touser", openId);
        sendBody.put("data", sendMag);
        sendBody.put("appid", bkAppKey);

       /* if(noticeMessage instanceof AlarmMessage){
            AlarmMessage alarmMessage = (AlarmMessage)noticeMessage;
            sendMag.put("message", new WeChatTemplateMsg(alarmMessage.getContent()));
            sendMag.put("time",new WeChatTemplateMsg(alarmMessage.getTime()));
            sendMag.put("level",new WeChatTemplateMsg(alarmMessage.getLevel(),"#FF69B4" ));
            sendMag.put("type",new WeChatTemplateMsg(alarmMessage.getType() ,"#173177"));
            sendMag.put("remark",new WeChatTemplateMsg(alarmMessage.getRemark(),"#173177"));
            sendBody.put("template_id", this.alarmTemplateId);
        }else*/
        {
            sendMag.put("content", new WeChatTemplateMsg(noticeMessage.getContent()));
            sendMag.put("title",new WeChatTemplateMsg(noticeMessage.getTitle()));
            String time = StringUtil.isEmpty(noticeMessage.getTime()) ? "2024-05-05" : noticeMessage.getTime();
            sendMag.put("time",new WeChatTemplateMsg(time,"#FF69B4"));
            sendBody.put("template_id", this.commonTemplateId);
        }

        try {
            // 1.创建httpclient对象
            CloseableHttpClient client = HttpClients.createDefault();
            // 2.创建post对象
            HttpPost post = new HttpPost(requestUrl);
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
    private void validateToken() {
        if (StringUtil.isEmpty(this.enterpriseToken)) {
            this.getOrRefreshToken();
        }
        Long now = System.currentTimeMillis()/1000;
        if(this.tokenFreshTimeSt == 0L){
            this.getOrRefreshToken();
        }else{
            Long diff = (now - tokenFreshTimeSt)/60;
            if(diff > 600){
                // 超过十分钟重新获取一下
                this.getOrRefreshToken();
            }
        }
    }
}