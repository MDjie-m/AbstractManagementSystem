package com.renxin.gauge.mapper;

import com.renxin.gauge.domain.PsyConsultingOrder;

import java.util.List;

/**
 * 咨询订单Mapper接口
 * 
 * @author yangchuang
 * @date 2023-01-29
 */
public interface PsyConsultingOrderMapper 
{
    /**
     * 查询咨询订单
     * 
     * @param id 咨询订单主键
     * @return 咨询订单
     */
    public PsyConsultingOrder selectPsyConsultingOrderById(Long id);

    /**
     * 查询咨询订单列表
     * 
     * @param psyConsultingOrder 咨询订单
     * @return 咨询订单集合
     */
    public List<PsyConsultingOrder> selectPsyConsultingOrderList(PsyConsultingOrder psyConsultingOrder);

    /**
     * 新增咨询订单
     * 
     * @param psyConsultingOrder 咨询订单
     * @return 结果
     */
    public int insertPsyConsultingOrder(PsyConsultingOrder psyConsultingOrder);

    /**
     * 修改咨询订单
     * 
     * @param psyConsultingOrder 咨询订单
     * @return 结果
     */
    public int updatePsyConsultingOrder(PsyConsultingOrder psyConsultingOrder);

    /**
     * 删除咨询订单
     * 
     * @param id 咨询订单主键
     * @return 结果
     */
    public int deletePsyConsultingOrderById(Long id);

    /**
     * 批量删除咨询订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyConsultingOrderByIds(Long[] ids);
}
