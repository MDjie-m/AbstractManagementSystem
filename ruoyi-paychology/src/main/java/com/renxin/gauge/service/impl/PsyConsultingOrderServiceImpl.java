package com.renxin.gauge.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import com.renxin.gauge.domain.PsyConsultingOrder;
import com.renxin.gauge.mapper.PsyConsultingOrderMapper;
import com.renxin.gauge.service.IPsyConsultingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 咨询订单Service业务层处理
 * 
 * @author yangchuang
 * @date 2023-01-29
 */
@Service
public class PsyConsultingOrderServiceImpl implements IPsyConsultingOrderService
{
    @Autowired
    private PsyConsultingOrderMapper psyConsultingOrderMapper;

    /**
     * 查询咨询订单
     * 
     * @param id 咨询订单主键
     * @return 咨询订单
     */
    @Override
    public PsyConsultingOrder selectPsyConsultingOrderById(Long id)
    {
        return psyConsultingOrderMapper.selectPsyConsultingOrderById(id);
    }

    /**
     * 查询咨询订单列表
     * 
     * @param psyConsultingOrder 咨询订单
     * @return 咨询订单
     */
    @Override
    public List<PsyConsultingOrder> selectPsyConsultingOrderList(PsyConsultingOrder psyConsultingOrder)
    {
        return psyConsultingOrderMapper.selectPsyConsultingOrderList(psyConsultingOrder);
    }

    /**
     * 新增咨询订单
     * 
     * @param psyConsultingOrder 咨询订单
     * @return 结果
     */
    @Override
    public int insertPsyConsultingOrder(PsyConsultingOrder psyConsultingOrder)
    {
        psyConsultingOrder.setCreateTime(DateUtils.getNowDate());
        return psyConsultingOrderMapper.insertPsyConsultingOrder(psyConsultingOrder);
    }

    /**
     * 修改咨询订单
     * 
     * @param psyConsultingOrder 咨询订单
     * @return 结果
     */
    @Override
    public int updatePsyConsultingOrder(PsyConsultingOrder psyConsultingOrder)
    {
        psyConsultingOrder.setUpdateTime(DateUtils.getNowDate());
        return psyConsultingOrderMapper.updatePsyConsultingOrder(psyConsultingOrder);
    }

    /**
     * 批量删除咨询订单
     * 
     * @param ids 需要删除的咨询订单主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultingOrderByIds(Long[] ids)
    {
        return psyConsultingOrderMapper.deletePsyConsultingOrderByIds(ids);
    }

    /**
     * 删除咨询订单信息
     * 
     * @param id 咨询订单主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultingOrderById(Long id)
    {
        return psyConsultingOrderMapper.deletePsyConsultingOrderById(id);
    }
}
