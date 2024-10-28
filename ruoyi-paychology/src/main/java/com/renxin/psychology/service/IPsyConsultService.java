package com.renxin.psychology.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.domain.PsyConsultServe;
import com.renxin.psychology.request.*;
import com.renxin.psychology.dto.PsyConsultInfoDTO;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;

import java.util.List;

public interface IPsyConsultService extends IService<PsyConsult> {

    PsyConsultInfoDTO getConsultInfoByServe(Long cId, Long sId);

    List<PsyConsultWorkVO> getConsultWorksById(Long id);
    
    List<PsyConsultWorkVO> getConsultWorks(PsyWorkReq req);

    PsyConsultVO getOne(Long id);

    PsyConsult getByPhone(String phone);
    
    PsyConsult getByPhoneOrInsert(String phone);

    List<PsyConsult> search(PsyConsultReq req);

    List<PsyConsult> getList(PsyConsultVO req);

    List<PsyConsult> getList(PsyAdminConsultReq req);
    
    List<PsyConsult> queryConsultantList(PsyConsultOrderVO req);

    void updateNum(Long id, int num);

    void updateByApp(PsyConsultVO req);

    int delConsultServeRef(PsyConsultServe req);

    AjaxResult refConsultServe(PsyRefConsultServeReq req);

    AjaxResult add(PsyConsultVO req);

    AjaxResult updateConsult(PsyConsultVO req);
    
    AjaxResult logout(PsyConsultVO req);

    String getAvailableUserName(String name);

    int deleteAll(Long[] ids);

    int delete(Long id);
    
    void addAllRelation(Long serverConfigId, Long consultantId);

     String getClientIdByConsultantId(Long consutantId);
     String getNameByConsultantId(Long consutantId);

    /**
     * 查询来访咨询师详情
     * @param req
     * @return
     */
    PsyConsultVO queryConsultantDetail(VisitorDetailReq req);
    
    //刷新byId缓存
    void refreshCacheByIdList(List<Long> idList);
    void refreshCacheById(Long id);
    void refreshCacheAll();
    //刷新id清单缓存
    void refreshIdList();
    
}
