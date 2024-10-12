package com.renxin.common.wxMsg;

import com.github.pagehelper.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author : dyg
 * @className : SmsMessageDTO
 * @description : SMS短信平台 实体类
 * @date : 2023/8/30 10:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NoticeMessage implements Serializable {
    private static final long serialVersionUID = 5081758462088563857L;
    /**
     　* 消息类型
　　 **/
    private String messageType;
    /**
     * 通知消息渠道
     */
    protected NoticeMethodEnum noticeMethod;

    /**
     * 接收者-手机号码
     */
    protected String receiverPhone;

    protected String receiverId;
    
    protected Long consultantId;
    protected Long userId;
    
    /**
     * 接收者-用户名
     */
    protected String receiverUserName;
    /**
     * 消息内容
     */
    protected String content;
    /**
     * 消息内容
     */
    protected String title;
    /**
     * 消息时间
     */
    protected String time;

    /**
     * 发送结果
     */
    protected String result;

    /**
     * 错误信息
     */
    protected String error;

    //消息参数
    protected Map<String, TemplateMessageItemVo> msgMap;

    public String wrapperMessage() {
        StringBuilder sub = new StringBuilder();
        String time = StringUtil.isEmpty(this.getTime()) ? format(new Date(),"yyyy-MM-dd HH:mm:ss") : this.getTime();
        sub.append("消息标题:  ").append(this.getTitle()).append("\n" )
                .append("消息内容: ").append(this.getContent()).append("\n")
                .append("消息时间: ").append(time).append("\n" );
        return sub.toString();
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
}