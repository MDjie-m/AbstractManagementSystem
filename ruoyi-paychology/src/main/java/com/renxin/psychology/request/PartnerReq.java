package com.renxin.psychology.request;

import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *
 * @author renxin
 * @date 2023-11-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PartnerReq extends BaseValObj implements Serializable
{

    private static final long serialVersionUID = 7668345973076735000L;

    private Long id;

    /** 登录名 */
    private Long userId;

    /** 咨询师号 */
    private Long cId;

    /** 步骤1-4 */
    private String step;

    /** 申请类型1-4 */
    private String type;

    /** 资质证明 */
    private String name;

    /** 0-草稿,1-入驻审核中,2-审核通过,3-修改审核中,4-审核驳回 */
    private String status;

    /** 0-未缴费,1-已缴费 */
    private String pay;

}
