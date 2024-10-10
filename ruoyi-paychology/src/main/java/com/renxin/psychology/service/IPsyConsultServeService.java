package com.renxin.psychology.service;

import com.renxin.psychology.domain.PsyConsultServe;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.request.PsyRefConsultServeReq;
import com.renxin.psychology.vo.PsyConsultServeVO;

import java.util.List;

public interface IPsyConsultServeService {

    List<PsyConsultServe> getList(PsyConsultServe req);

    PsyConsultServe getOne(PsyConsultServe serveId);

    int getRefCountByConsultId(Long id);

    int batchServeRef(PsyRefConsultServeReq req, Boolean isRefreshConsultant);

    List<PsyConsultServeVO> getServeRef(PsyConsultServe req);

    List<PsyConsultServeVO> getConsultServeRef(PsyConsultServe req);

    int deleteAllRelation(Long serverConfigId);

    int delete(PsyConsultServe serve);

    PsyConsultServeConfig getServerDetailByRelationId(String id);
}
