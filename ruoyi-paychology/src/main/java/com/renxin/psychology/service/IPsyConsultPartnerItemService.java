package com.renxin.psychology.service;

import java.util.List;

import com.renxin.psychology.domain.PsyConsultPartnerItem;

/**
 * 咨询师入驻申请子Service接口
 *
 * @author renxin
 * @date 2023-11-07
 */
public interface IPsyConsultPartnerItemService
{
    PsyConsultPartnerItem getOne(Long id);

    List<PsyConsultPartnerItem> getListById(Long id);

    List<PsyConsultPartnerItem> getList(PsyConsultPartnerItem psyConsultPartnerItem);

    int add(PsyConsultPartnerItem psyConsultPartnerItem);

    int edit(PsyConsultPartnerItem psyConsultPartnerItem);

    int deleteAll(Long[] ids);

    int delete(Long id);
    
    int countTime(PsyConsultPartnerItem req);
}
