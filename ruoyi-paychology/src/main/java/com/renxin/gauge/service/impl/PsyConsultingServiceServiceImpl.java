package com.renxin.gauge.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.domain.PsyConsultingService;
import com.renxin.gauge.mapper.PsyConsultingServiceMapper;
import com.renxin.gauge.service.IPsyConsultingServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 咨询服务Service业务层处理
 * 
 * @author yangchuang
 * @date 2023-01-31
 */
@Service
public class PsyConsultingServiceServiceImpl implements IPsyConsultingServiceService
{
    @Autowired
    private PsyConsultingServiceMapper psyConsultingServiceMapper;

    /**
     * 查询咨询服务
     * 
     * @param id 咨询服务主键
     * @return 咨询服务
     */
    @Override
    public PsyConsultingService selectPsyConsultingServiceById(Long id)
    {
        return psyConsultingServiceMapper.selectPsyConsultingServiceById(id);
    }

    /**
     * 查询咨询服务列表
     * 
     * @param psyConsultingService 咨询服务
     * @return 咨询服务
     */
    @Override
    public List<PsyConsultingService> selectPsyConsultingServiceList(PsyConsultingService psyConsultingService)
    {
        return psyConsultingServiceMapper.selectPsyConsultingServiceList(psyConsultingService);
    }

    /**
     * 新增咨询服务
     * 
     * @param psyConsultingService 咨询服务
     * @return 结果
     */
    @Override
    public int insertPsyConsultingService(PsyConsultingService psyConsultingService)
    {
        psyConsultingService.setCreateTime(DateUtils.getNowDate());
        return psyConsultingServiceMapper.insertPsyConsultingService(psyConsultingService);
    }

    /**
     * 修改咨询服务
     * 
     * @param psyConsultingService 咨询服务
     * @return 结果
     */
    @Override
    public int updatePsyConsultingService(PsyConsultingService psyConsultingService)
    {
        psyConsultingService.setUpdateTime(DateUtils.getNowDate());
        return psyConsultingServiceMapper.updatePsyConsultingService(psyConsultingService);
    }

    /**
     * 批量删除咨询服务
     * 
     * @param ids 需要删除的咨询服务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultingServiceByIds(Long[] ids)
    {
        return psyConsultingServiceMapper.deletePsyConsultingServiceByIds(ids);
    }

    /**
     * 删除咨询服务信息
     * 
     * @param id 咨询服务主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultingServiceById(Long id)
    {
        return psyConsultingServiceMapper.deletePsyConsultingServiceById(id);
    }
}
