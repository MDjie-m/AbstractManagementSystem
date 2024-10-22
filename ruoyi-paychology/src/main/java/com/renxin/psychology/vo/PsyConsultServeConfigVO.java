package com.renxin.psychology.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 咨询服务配置对象 psy_consult_server_config
 * 
 * @author renxin
 * @date 2023-07-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PsyConsultServeConfigVO extends BaseValObj implements Serializable
{
    private static final long serialVersionUID = 1L;


    private String name;

    /** 咨询形式 语音咨询、视频咨询、当面咨询 */
    private Integer mode;
    private String modeName;

    /** 服务类型 单次咨询 套餐咨询 */
    private Integer type;
    private String typeName;

    /** 服务介绍 */
    private String info;

    /** 价格 */
    private BigDecimal price;
    //咨询师分成比例
    private BigDecimal consultantRatio;
    /** 时长 */
    private Integer time;

    /** 数量 */
    private Integer num;

    /** 排序 倒叙 */
    private Integer zIndex;

    /** 限购 0-不限制 1-限制 */
    private Integer bound;
    private String boundName;

    /** 有效期 */
    private Integer end;

    /** 销量 */
    private Integer sales;

    /** 咨询师数量 */
    private Integer ref;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 状态（0正常 1停用） */
    private String status;

    //咨询师级别 1.学员咨询师   2.初级咨询师   3.中级咨询师   4.高级咨询师   5.督导师
    private Integer level;

    //服务对象   1来访者  2个案督导   3个人体验
    private String serviceObject;


    //private List<String> serviceObjectList;
}
