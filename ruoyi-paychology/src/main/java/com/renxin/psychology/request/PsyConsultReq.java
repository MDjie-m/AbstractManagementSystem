package com.renxin.psychology.request;

import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 咨询订单对象 psy_consult_order
 * 
 * @author renxin
 * @date 2023-06-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PsyConsultReq extends BaseValObj implements Serializable
{

    private static final long serialVersionUID = 7980447039917688534L;

    //咨询师id
    private Long consultantId;
    //来访者id
    private Long userId;
    
    /** 登录名 */
    private String userName;

    /** 用户性别 */
    private String sex;

    /** 城市 */
    private String city;

    /** 省份 */
    private String province;

    /** 咨询方向 */
    private Set<String> way;

    /** 咨询价格 */
    private BigDecimal lowPrice;
    private BigDecimal highPrice;

    /** 咨询服务 */
    private String serve;

    /** 咨询时段 */
    private List<String> days;

    /** 可选时段 */
    private String type;

    private String time;

    /** 服务 */
    private Long serveId;

    /** 今日可约 */
    private String buy;

    private String single;

    /** 0-and 1-or */
    private String nand;

    //筛选最近几天内可约
    private Integer ableWaitDay;

    //服务对象   1来访者  2个案督导   3个人体验
    private List<String> serviceObjectList;

}
