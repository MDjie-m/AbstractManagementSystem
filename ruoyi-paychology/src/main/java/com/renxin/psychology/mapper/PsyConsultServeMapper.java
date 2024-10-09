package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsultServe;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.vo.PsyConsultServeVO;

import java.util.List;

/**
 * 咨询服务Mapper接口
 * 
 * @author renxin
 * @date 2023-06-16
 */
public interface PsyConsultServeMapper extends BaseMapper<PsyConsultServe>
{

    int batchServeRef(List<PsyConsultServe> refs);

    @InterceptorIgnore(blockAttack = "true")
    int deleteAll(Long serverConfigId);

    List<PsyConsultServeVO> getServeRef(PsyConsultServe req);

    List<PsyConsultServeVO> getConsultServeRef(PsyConsultServe req);

    PsyConsultServeConfig getServerDetailByRelationId(String id);

}
