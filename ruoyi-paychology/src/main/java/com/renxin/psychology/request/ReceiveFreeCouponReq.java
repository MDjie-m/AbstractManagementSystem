package com.renxin.psychology.request;

import com.renxin.common.core.domain.BaseValObj;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 领取免费优惠券请求体
 * 
 * @author renxin
 * @date 2023-06-26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReceiveFreeCouponReq extends BaseValObj implements Serializable
{

    /** 咨询师id */
    private Long consultId;

    /** 优惠券id 逗号分隔 */
    private String couponTemplateIdStr;

    

}
