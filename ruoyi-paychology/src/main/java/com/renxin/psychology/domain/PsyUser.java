package com.renxin.psychology.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renxin.common.annotation.Excel;
import com.renxin.psychology.dto.OrderItemDTO;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;


/**
 * 用户对象 psy_user
 * 
 * @author renxin
 * @date 2022-08-26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PsyUser
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long id;

    /**  */
    @Excel(name = "用户名")
    private String name;

    /**  */
    @Excel(name = "手机号码")
    private String phone;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    @Excel(name = "用户剩余积分")
    private Integer integral;

    /**  */
    @Excel(name = "微信openId")
    private String wxOpenid;
    private String sex;

    /** 创建时间 */
    @Excel(name = "创建时间" ,dateFormat = "yyyy-MM-dd HH:mm:SS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //用户自己填写的标签
    private String userFillLabel;
    //咨询师为用户填写的标签
    private String consultantFillLabel;
    //客服为用户填写的标签
    private String adminFillLabel;

    //是否新用户(未领取新人礼包) 0是 1否
    private Integer isNewPeople;

    //设备id
    private String deviceId;

    //设备品牌
    private String deviceBrand;

    //设备型号
    private String deviceModel;

    //最后登录ip
    private String lastLoginIp;
    
    //咨询记录
    private List<OrderItemDTO> orderItemList;



}
