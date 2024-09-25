package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsultServeConfig;
import com.renxin.psychology.request.PsyConsultServeConfigReq;

import java.util.List;

/**
 * 咨询服务配置Mapper接口
 * 
 * @author renxin
 * @date 2023-07-14
 */
public interface PsyConsultServeConfigMapper extends BaseMapper<PsyConsultServeConfig>
{

    List<PsyConsultServeConfig> getConfigList(PsyConsultServeConfigReq req);

    int tombstonedByIds(Long[] ids);

}
