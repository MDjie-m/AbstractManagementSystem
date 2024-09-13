package com.renxin.common.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 消息记录对象 psy_msg_record
 *
 * @author renxin
 * @date 2024-09-11
 */
@Data
public class PsyMsgRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 发送人id */
    @Excel(name = "发送人id")
    private Long sendUserId;

    /** 发送人-用户类型 1来访者 2咨询师 */
    @Excel(name = "发送人-用户类型 1来访者 2咨询师")
    private Integer sendUserType;

    /** 接收人id */
    @Excel(name = "接收人id")
    private Long receiveUserId;

    /** 接收人-用户类型 1来访者 2咨询师 */
    @Excel(name = "接收人-用户类型 1来访者 2咨询师")
    private Integer receiveUserType;

    //对话人id
    private Long talkUserId;
    /** 对话人-用户类型 1来访者 2咨询师 */
    private Integer talkUserType;
    
    private Long talkUserId1;
    private Integer talkUserType1;
    private Long talkUserId2;
    private Integer talkUserType2;
    /** 消息类型(1.文字 2.图片 3.音频 4.视频 5.富文本) */
    @Excel(name = "消息类型(1.文字 2.图片 3.音频 4.视频 5.富文本)")
    private Integer msgType;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String msg;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 状态（0未读 1已读） */
    @Excel(name = "状态", readConverterExp = "0=未读,1=已读")
    private String status;

    /**  */
    @Excel(name = "")
    private String param1;

    /**  */
    @Excel(name = "")
    private String param2;

    /**  */
    @Excel(name = "")
    private String param3;

    /**  */
    @Excel(name = "")
    private String param4;

    /**  */
    @Excel(name = "")
    private String param5;
    
    //发送人信息
    private PersonInfo sendBy;

}
