package com.renxin.psychology.service;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.renxin.psychology.domain.PsyConsultWork;
import com.renxin.psychology.dto.HeaderDTO;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.dto.RecentWorkDTO;
import com.renxin.psychology.request.PsyConsultWorkReq;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.vo.PsyConsultWorkVO;

import java.util.HashMap;
import java.util.List;

public interface IPsyConsultWorkService {

    HashMap<String, String> getWorkDetail(PsyWorkReq req);

    List<HashMap<String, String>> getWorks(PsyWorkReq req);
    
    List<OrderItemDTO> getTodoList(PsyWorkReq req);

    List<PsyConsultWork> getList(PsyConsultWorkVO req);


    List<PsyConsultWorkVO> getConsultWorks(PsyWorkReq req);

    List<PsyConsultWork> checkDataUnique(PsyConsultWorkVO req);

    List<Long> getConsultIds(PsyWorkReq req);

    List<HashMap<String, String>> getWorks(PsyWorkReq req, List<Long> ids);

    List<HeaderDTO> getWorkHeader(String month);

    PsyConsultWorkVO getOne(Long id);

    Boolean checkWork(Long id, Long consultId, Integer time);

    // type 1-试用 2-释放
    PsyConsultWork handleWork(Long id, Long consultId, Integer time, int type);

    void doSave(PsyConsultWorkReq req);

    int add(PsyConsultWorkVO req);

    int update(PsyConsultWorkVO req);

    int delete(Long id);

    List<RecentWorkDTO> recentWorkList(PsyWorkReq req);

}
