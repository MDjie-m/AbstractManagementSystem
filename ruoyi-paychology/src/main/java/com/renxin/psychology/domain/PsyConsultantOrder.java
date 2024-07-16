package com.renxin.psychology.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String id;
    
    /** 流水编号 */
    @Excel(name = "流水编号")
    private String orderNo;

    /** 服务ID :体验ID、个督ID、课程ID 套餐ID */
    @Excel(name = "服务ID :体验ID、个督ID、课程ID 套餐ID")
    private String serverId;

    /** 服务类型  1：体验 2:个督、3:团督、4:课程 5:个人套餐 */
    @Excel(name = "服务类型  1：体验 2:个督、3:团督、4:课程 5:个人套餐")
    private String serverType;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serverName;

    /** 订单状态0-待付款 1-进行中 2-已完成 3-已取消 */
    @Excel(name = "订单状态0-待付款 1-进行中 2-已完成 3-已取消")
    private String status;

    /** 支付咨询者id */
    @Excel(name = "支付咨询师id")
    private String payConsultantId;

    /** 支付咨询者名称 */
    @Excel(name = "支付咨询师名称")
    private String payConsultantName;

    /** 实际支付费用 */
    @Excel(name = "实际支付费用")
    private BigDecimal payAmount;

    /** 付款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
    // 咨询服务
    private Long workId;
    private Integer time;

    

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderNo", getOrderNo())
            .append("serverId", getServerId())
            .append("serverType", getServerType())
            .append("serverName", getServerName())
            .append("status", getStatus())
            .append("payConsultantId", getPayConsultantId())
            .append("payConsultantName", getPayConsultantName())
            .append("payAmount", getPayAmount())
            .append("payDatetime", getPayDatetime())
            .append("payStatus", getPayStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
