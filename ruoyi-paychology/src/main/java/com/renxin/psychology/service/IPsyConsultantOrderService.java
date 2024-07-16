package com.renxin.psychology.service;

import java.rmi.ServerException;
import java.util.List;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.wechat.vo.WechatPayVO;

/**
 * 团队督导(组织)订单Service接口
 * 
 * @author renxin
 * @date 2024-06-26
 */
public interface IPsyConsultantOrderService 
{
    /**
     * 查询团队督导(组织)订单
     * 
     * @param orderNo 团队督导(组织)订单主键
     * @return 团队督导(组织)订单
     */
    public PsyConsultantOrder selectPsyConsultantOrderByOrderNo(String orderNo);

    /**
     * 查询团队督导(组织)订单列表
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 团队督导(组织)订单集合
     */
    public List<PsyConsultantOrder> selectPsyConsultantOrderList(PsyConsultantOrder psyConsultantOrder);

    /**
     * 新增团队督导(组织)订单
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    public int insertPsyConsultantOrder(PsyConsultantOrder psyConsultantOrder);

    /**
     * 修改团队督导(组织)订单
     * 
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    public int updatePsyConsultantOrder(PsyConsultantOrder psyConsultantOrder);

    /**
     * 批量删除团队督导(组织)订单
     * 
     * @param orderNos 需要删除的团队督导(组织)订单主键集合
     * @return 结果
     */
    public int deletePsyConsultantOrderByOrderNos(String[] orderNos);

    /**
     * 删除团队督导(组织)订单信息
     * 
     * @param orderNo 团队督导(组织)订单主键
     * @return 结果
     */
    public int deletePsyConsultantOrderByOrderNo(String orderNo);


    /**
     * 创建订单
     * @param wechatPay
     */
    void createConsultantOrder(PsyConsultantOrder wechatPay);
    
    
    /**
     * 生成团队督导(组织)订单
     *
     * @param psyConsultantOrder 团队督导(组织)订单
     * @return 结果
     */
    public PsyConsultantOrder generatePsyConsultantOrder(PsyConsultantOrder psyConsultantOrder);

    /** 支付成功回调 */
    void paySuccessCallback(String outTradeNo, String payId);
    
    
}
