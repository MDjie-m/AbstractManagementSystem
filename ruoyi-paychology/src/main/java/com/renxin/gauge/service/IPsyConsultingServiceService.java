package com.renxin.gauge.service;

import com.renxin.gauge.domain.PsyConsultingService;

import java.util.List;

/**
 * 咨询服务Service接口
 * 
 * @author yangchuang
 * @date 2023-01-31
 */
public interface IPsyConsultingServiceService 
{
    /**
     * 查询咨询服务
     * 
     * @param id 咨询服务主键
     * @return 咨询服务
     */
    public PsyConsultingService selectPsyConsultingServiceById(Long id);

    /**
     * 查询咨询服务列表
     * 
     * @param psyConsultingService 咨询服务
     * @return 咨询服务集合
     */
    public List<PsyConsultingService> selectPsyConsultingServiceList(PsyConsultingService psyConsultingService);

    /**
     * 新增咨询服务
     * 
     * @param psyConsultingService 咨询服务
     * @return 结果
     */
    public int insertPsyConsultingService(PsyConsultingService psyConsultingService);

    /**
     * 修改咨询服务
     * 
     * @param psyConsultingService 咨询服务
     * @return 结果
     */
    public int updatePsyConsultingService(PsyConsultingService psyConsultingService);

    /**
     * 批量删除咨询服务
     * 
     * @param ids 需要删除的咨询服务主键集合
     * @return 结果
     */
    public int deletePsyConsultingServiceByIds(Long[] ids);

    /**
     * 删除咨询服务信息
     * 
     * @param id 咨询服务主键
     * @return 结果
     */
    public int deletePsyConsultingServiceById(Long id);
}
