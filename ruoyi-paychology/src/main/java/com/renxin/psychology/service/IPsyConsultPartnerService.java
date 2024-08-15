package com.renxin.psychology.service;

import java.util.List;

import com.renxin.common.core.domain.AjaxResult;
import com.renxin.psychology.domain.PsyConsultPartner;
import com.renxin.psychology.domain.PsyConsultPartnerItem;
import com.renxin.psychology.dto.PartnerDTO;
import com.renxin.psychology.request.PsyAdminPartnerReq;

/**
 * 咨询师入驻申请Service接口
 *
 * @author renxin
 * @date 2023-11-07
 */
public interface IPsyConsultPartnerService {

    // 点击填写入驻资料，查询/创建草稿
    // 查询本人申请表 + 字表
    // 编辑主表资料
    // 编辑字表资料
    // 确认并提交资料

    void draft(Long userId);
    
    Long consultantDraft(Long consultId);

    int addItem(PsyConsultPartnerItem item);

    int saveItem(PsyConsultPartnerItem item);

    int editItem(PsyConsultPartnerItem item);

    int delItem(Long id);

    int save(PsyConsultPartner entity);
    
    int saveByConsultId(PsyConsultPartner entity);

    AjaxResult createUser(Long id);

    PartnerDTO getInfoByUserId(Long userId);

    PartnerDTO getInfoById(Long userId);
    
    PartnerDTO getInfoByConsultId(Long userId);
    
    PartnerDTO getDetailByConsultId(Long userId);

    List<PsyConsultPartner> getList(PsyAdminPartnerReq psyConsultPartner);

    void saveItemList(PsyConsultPartner req);

}
