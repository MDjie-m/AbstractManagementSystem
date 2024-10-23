package com.renxin.psychology.dto;

import com.renxin.psychology.domain.PsyConsultPartner;
import com.renxin.psychology.domain.PsyConsultPartnerItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 咨询师入驻申请对象 psy_consult_partner
 * 
 * @author renxin
 * @date 2023-11-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PartnerDTO extends PsyConsultPartner implements Serializable
{
    private static final long serialVersionUID = -1705227712564097237L;

    private List<PsyConsultPartnerItem> items;

    private List<String> langList;

    /** 证件照 */
    private String cardImg;
    private List<String> cardImgs;

    /** 0-草稿,1-入驻审核中,2-审核通过,3-修改审核中,4-审核驳回 */
    private String status;

    /** 0-未缴费,1-已缴费 */
    private String pay;

}
