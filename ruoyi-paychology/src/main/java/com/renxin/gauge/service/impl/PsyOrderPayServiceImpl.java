package com.renxin.gauge.service.impl;

import java.util.List;

import com.renxin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.renxin.gauge.mapper.PsyOrderPayMapper;
import com.renxin.gauge.domain.PsyOrderPay;
import com.renxin.gauge.service.IPsyOrderPayService;

/**
 * 心理咨询订单支付信息Service业务层处理
 *
 * @author renxin
 * @date 2022-10-12
 */
@Service
public class PsyOrderPayServiceImpl implements IPsyOrderPayService {
    @Autowired
    private PsyOrderPayMapper psyOrderPayMapper;

    /**
     * 查询心理咨询订单支付信息
     *
     * @param id 心理咨询订单支付信息主键
     * @return 心理咨询订单支付信息
     */
    @Override
    public PsyOrderPay selectPsyOrderPayById(Long id) {
        return psyOrderPayMapper.selectPsyOrderPayById(id);
    }

    /**
     * 查询心理咨询订单支付信息列表
     *
     * @param psyOrderPay 心理咨询订单支付信息
     * @return 心理咨询订单支付信息
     */
    @Override
    public List<PsyOrderPay> selectPsyOrderPayList(PsyOrderPay psyOrderPay) {
        return psyOrderPayMapper.selectPsyOrderPayList(psyOrderPay);
    }

    /**
     * 新增心理咨询订单支付信息
     *
     * @param psyOrderPay 心理咨询订单支付信息
     * @return 结果
     */
    @Override
    public int insertPsyOrderPay(PsyOrderPay psyOrderPay) {
        psyOrderPay.setCreateTime(DateUtils.getNowDate());
        return psyOrderPayMapper.insertPsyOrderPay(psyOrderPay);
    }

    /**
     * 修改心理咨询订单支付信息
     *
     * @param psyOrderPay 心理咨询订单支付信息
     * @return 结果
     */
    @Override
    public int updatePsyOrderPay(PsyOrderPay psyOrderPay) {
        return psyOrderPayMapper.updatePsyOrderPay(psyOrderPay);
    }

    public int updatePsyOrderPayByOrderId(PsyOrderPay psyOrderPay){
        return psyOrderPayMapper.updatePsyOrderPayByOrderId(psyOrderPay);
    }

    /**
     * 批量删除心理咨询订单支付信息
     *
     * @param ids 需要删除的心理咨询订单支付信息主键
     * @return 结果
     */
    @Override
    public int deletePsyOrderPayByIds(Long[] ids) {
        return psyOrderPayMapper.deletePsyOrderPayByIds(ids);
    }

    /**
     * 删除心理咨询订单支付信息信息
     *
     * @param id 心理咨询订单支付信息主键
     * @return 结果
     */
    @Override
    public int deletePsyOrderPayById(Long id) {
        return psyOrderPayMapper.deletePsyOrderPayById(id);
    }
}
