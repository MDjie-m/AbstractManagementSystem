package com.renxin.psychology.service.impl;

import java.util.List;
import com.renxin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.psychology.mapper.PsyConsultantOrderMapper;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.service.IPsyConsultantOrderService;

/**
 * 团队督导(组织)订单Service业务层处理
 * 
 * @author renxin
 * @date 2024-06-26
 */
@Service
public class PsyConsultantOrderServiceImpl implements IPsyConsultantOrderService 
{
    @Autowired
    private PsyConsultantOrderMapper psyConsultantOrderMapper;

    /**
     * 查询团队督导(组织)订单
     * 
     * @param orderNo 团队督导(组织)订单主键
     * @return 团队督导(组织)订单
     */
    @Override
    public PsyConsultantOrder selectPsyConsultantOrderByOrderNo(String orderNo)
    {
        return psyConsultantOrderMapper.selectPsyConsultantOrderByOrderNo(orderNo);
    }

    /**
     * 查询团队督导(组织)订单列表
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 团队督导(组织)订单
     */
    @Override
    public List<PsyConsultantOrder> selectPsyConsultantOrderList(PsyConsultantOrder psyConsultantOrder)
    {
        return psyConsultantOrderMapper.selectPsyConsultantOrderList(psyConsultantOrder);
    }

    /**
     * 新增团队督导(组织)订单
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    @Override
    public int insertPsyConsultantOrder(PsyConsultantOrder psyConsultantOrder)
    {
        psyConsultantOrder.setCreateTime(DateUtils.getNowDate());
        return psyConsultantOrderMapper.insertPsyConsultantOrder(psyConsultantOrder);
    }

    /**
     * 修改团队督导(组织)订单
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    @Override
    public int updatePsyConsultantOrder(PsyConsultantOrder psyConsultantOrder)
    {
        psyConsultantOrder.setUpdateTime(DateUtils.getNowDate());
        return psyConsultantOrderMapper.updatePsyConsultantOrder(psyConsultantOrder);
    }

    /**
     * 批量删除团队督导(组织)订单
     * 
     * @param orderNos 需要删除的团队督导(组织)订单主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantOrderByOrderNos(String[] orderNos)
    {
        return psyConsultantOrderMapper.deletePsyConsultantOrderByOrderNos(orderNos);
    }

    /**
     * 删除团队督导(组织)订单信息
     * 
     * @param orderNo 团队督导(组织)订单主键
     * @return 结果
     */
    @Override
    public int deletePsyConsultantOrderByOrderNo(String orderNo)
    {
        return psyConsultantOrderMapper.deletePsyConsultantOrderByOrderNo(orderNo);
    }
}
