package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsultPartnerItem;

/**
 * 咨询师入驻申请子Mapper接口
 * 
 * @author renxin
 * @date 2023-11-07
 */
public interface PsyConsultPartnerItemMapper extends BaseMapper<PsyConsultPartnerItem>
{
     int updateStatusByPartnerId(PsyConsultPartnerItem req);
     
     int countTime(PsyConsultPartnerItem req);
}
