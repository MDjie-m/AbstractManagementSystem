package com.renxin.psychology.service;

import com.renxin.common.core.domain.AjaxResult;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.domain.PsyConsultServe;
import com.renxin.psychology.request.PsyAdminConsultReq;
import com.renxin.psychology.request.PsyConsultReq;
import com.renxin.psychology.dto.PsyConsultInfoDTO;
import com.renxin.psychology.request.PsyRefConsultServeReq;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;

import java.util.List;

public interface IPsyConsultService {

    PsyConsultInfoDTO getConsultInfoByServe(Long cId, Long sId);

    List<PsyConsultWorkVO> getConsultWorksById(Long id);

    PsyConsultVO getOne(Long id);

    PsyConsult getByPhone(String phone);
    
    PsyConsult getByPhoneOrInsert(String phone);

    List<PsyConsult> search(PsyConsultReq req);

    List<PsyConsult> getList(PsyConsultVO req);

    List<PsyConsult> getList(PsyAdminConsultReq req);

    void updateNum(Long id, int num);

    void updateByApp(PsyConsultVO req);

    int delConsultServeRef(PsyConsultServe req);

    AjaxResult refConsultServe(PsyRefConsultServeReq req);

    AjaxResult add(PsyConsultVO req);

    AjaxResult update(PsyConsultVO req);

    String getAvailableUserName(String name);

    int deleteAll(Long[] ids);

    int delete(Long id);
    
    void addAllRelation();

}
