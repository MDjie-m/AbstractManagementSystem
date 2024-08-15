package com.renxin.gauge.service;

import java.util.List;
import com.renxin.gauge.domain.PsyOrderPay;

/**
 * 心理咨询订单支付信息Service接口
 * 
 * @author renxin
 * @date 2022-10-12
 */
public interface IPsyOrderPayService 
{
    /**
     * 查询心理咨询订单支付信息
     * 
     * @param id 心理咨询订单支付信息主键
     * @return 心理咨询订单支付信息
     */
    public PsyOrderPay selectPsyOrderPayById(Long id);

    /**
     * 查询心理咨询订单支付信息列表
     * 
     * @param psyOrderPay 心理咨询订单支付信息
     * @return 心理咨询订单支付信息集合
     */
    public List<PsyOrderPay> selectPsyOrderPayList(PsyOrderPay psyOrderPay);

    /**
     * 新增心理咨询订单支付信息
     * 
     * @param psyOrderPay 心理咨询订单支付信息
     * @return 结果
     */
    public int insertPsyOrderPay(PsyOrderPay psyOrderPay);

    /**
     * 修改心理咨询订单支付信息
     * 
     * @param psyOrderPay 心理咨询订单支付信息
     * @return 结果
     */
    public int updatePsyOrderPay(PsyOrderPay psyOrderPay);

    /**
     * 修改订单支付信息 by  orderId
     *
     * @param psyOrderPay 心理咨询订单支付信息
     * @return 结果
     */
    public int updatePsyOrderPayByOrderId(PsyOrderPay psyOrderPay);
    /**
     * 批量删除心理咨询订单支付信息
     * 
     * @param ids 需要删除的心理咨询订单支付信息主键集合
     * @return 结果
     */
    public int deletePsyOrderPayByIds(Long[] ids);

    /**
     * 删除心理咨询订单支付信息信息
     * 
     * @param id 心理咨询订单支付信息主键
     * @return 结果
     */
    public int deletePsyOrderPayById(Long id);
}
