package com.renxin.psychology.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.renxin.course.domain.CourCourse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.renxin.common.annotation.Excel;
import com.renxin.common.core.domain.BaseEntity;

/**
 * 团队督导(组织)订单对象 psy_consultant_order
 * 
 * @author renxin
 * @date 2024-06-26
 */
@NoArgsConstructor
@TableName("psy_consultant_order")
@Data
public class PsyConsultantOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;
    
    /** 流水编号 */
    @Excel(name = "流水编号")
    private String orderNo;

    /** 服务ID :体验ID、个督ID、课程ID 套餐ID */
    @Excel(name = "服务ID :体验ID、个督ID、课程ID 套餐ID")
    private String serverId;

    /** 服务类型  1：团督 2:个督、3:体验、4:课程 5:个人套餐 */
    @Excel(name = "服务类型  1：团督 2:个督、3:体验、4:课程 5:个人套餐")
    private String serverType;
    
    //成员类型  1.正式成员   2.观摩成员
    private Integer memberType;
    private Integer memberUserType;//成员用户类型 1.来访者  2.咨询师

    /** 服务类型  1：团督 2:个督、3:体验、4:课程 5:个人套餐 */
    @TableField(exist = false)
    private List<String> serverTypeList;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serverName;

    /** 订单状态0-待付款 1-进行中 2-已完成 3-已取消 */
    @Excel(name = "订单状态0-待付款 1-进行中 2-已完成 3-已取消")
    private String status;

    /** 支付咨询者id */
    @Excel(name = "支付咨询师id")
    private Long payConsultantId;

    /** 支付咨询者名称 */
    @Excel(name = "支付咨询师名称")
    private String payConsultantName;

    /** 实际支付费用 */
    @Excel(name = "实际支付费用")
    private BigDecimal payAmount;
    //原价
    private BigDecimal originalPrice;
    //咨询师分成比例
    private BigDecimal consultantRatio;
    //督导师每堂课的收入
    private BigDecimal consultantPrice;
    
    //优惠券no
    private String couponNo;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "付款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDatetime;

    /** 支付状态 1,未支付 2,支付成功 3,退款中 4,部分退 5,全单退 6,退款失败 */
    @Excel(name = "支付状态 1,未支付 2,支付成功 3,退款中 4,部分退 5,全单退 6,退款失败")
    private String payStatus;

    //支付方式  1.现款支付   2.权益支付
    private Integer payType;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    private String payId;
    private String aliPayParam;//阿里支付参数
    private String wxPayParam;//微信支付参数
    @TableField(exist = false)
    private Map<String,String> wxPayParamMap;
    // 咨询服务
    private Long workId;
    private Integer time;
    private String paymentChannel;//支付渠道  aliPay   wechatPay
    
    
    //以下三字段, 仅在类型为 个案督导/个人体验 时, 有值
    @TableField(exist = false)
    private Integer totalNum;//总服务次数
    @TableField(exist = false)
    private Integer usedNum;//已使用服务次数
    @TableField(exist = false)
    private Integer surplusNum;//剩余服务次数
    @TableField(exist = false)
    private Long chargeConsultantId;//收费咨询师id
    @TableField(exist = false)
    private String chargeConsultantName;//收费咨询师name
    @TableField(exist = false)
    private String nextBeginTime;//下次服务开始时间
    @TableField(exist = false)
    private Boolean isConsultantReq = false;//咨询师端请求

    //团督详情
    @TableField(exist = false)
    private PsyConsultantTeamSupervision teamDetail;

    //服务详情 (个督/体验)
    @TableField(exist = false)
    private PsyConsultServeConfig serverDetail;
    
    //最近一次预约详情
    @TableField(exist = false)
    private PsyConsultantSchedule lastSchedule;
    
    //课程详情
    @TableField(exist = false)
    private CourCourse courseDetail;

    //课程详情
    @TableField(exist = false)
    private PsyConsultantPackage packageDetail;

    /** 创建时间止 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date createTimeEnd;

}
