package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.request.PsyConsultReq;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.psychology.vo.PsyConsultVO;

import java.util.List;

/**
 * 咨询师Mapper接口
 * 
 * @author renxin
 * @date 2023-06-16
 */
public interface PsyConsultMapper extends BaseMapper<PsyConsult>
{

    List<PsyConsult> search(PsyConsultReq req);

    List<PsyConsult> getList(PsyConsultVO req);

    int tombstonedByIds(Long[] ids);

    PsyConsult queryById(Long id);
    
    void refreshServerNum();
    void updateServerNumZero();

    List<PsyConsult> queryConsultantList(PsyConsultOrderVO req);
    
    List<PsyConsult> queryConsultantListByServiceObject(PsyConsult req);


}
