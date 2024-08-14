package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsultWork;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.vo.PsyConsultOrderItemVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;

import java.util.List;

/**
 * 咨询排班表Mapper接口
 * 
 * @author renxin
 * @date 2023-06-16
 */
public interface PsyConsultWorkMapper extends BaseMapper<PsyConsultWork>
{

    List<PsyConsultWork> checkDataUnique(PsyConsultWorkVO req);

    List<PsyConsultWork> getList(PsyConsultWorkVO req);

    List<Long> getConsultIds(PsyWorkReq req);

    List<PsyConsultWorkVO> getWorks(PsyWorkReq req);

    List<PsyConsultOrderItemVO> getOrderItems(PsyWorkReq req);
    
    int insertBatch(List<PsyConsultWork> list);

}
