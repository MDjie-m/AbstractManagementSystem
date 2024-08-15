package com.renxin.gauge.mapper;

import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.dto.OrderQueryDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 心理测评订单信息Mapper接口
 * 
 * @author renxin
 * @date 2022-10-12
 */
public interface PsyOrderMapper 
{
    /**
     * 查询心理测评订单信息
     * 
     * @param id 心理测评订单信息主键
     * @return 心理测评订单信息
     */
    public PsyOrder selectPsyOrderById(Long id);

    /**
     * 查询心理测评订单信息列表
     * 
     * @param psyOrder 心理测评订单信息
     * @return 心理测评订单信息集合
     */
    public List<PsyOrder> selectPsyOrderList(PsyOrder psyOrder);

    public List<PsyOrder> queryOrderList(OrderQueryDTO psyOrder);

    List<PsyOrder> getOrderByCancel(@Param("status") Integer status, @Param("time") Date time);

    /**
     * 新增心理测评订单信息
     * 
     * @param psyOrder 心理测评订单信息
     * @return 结果
     */
    public Long insertPsyOrder(PsyOrder psyOrder);

    /**
     * 修改心理测评订单信息
     * 
     * @param psyOrder 心理测评订单信息
     * @return 结果
     */
    public int updatePsyOrder(PsyOrder psyOrder);

    public int updatePsyOrderByOrder(PsyOrder psyOrder);

    /**
     * 删除心理测评订单信息
     * 
     * @param id 心理测评订单信息主键
     * @return 结果
     */
    public int deletePsyOrderById(Long id);

    /**
     * 批量删除心理测评订单信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePsyOrderByIds(Long[] ids);

    List<PsyOrder> queryOrderPage(PsyOrder psyOrder);

    int getMyReportNum(LoginDTO loginDTO);

    int getOrderNumByGaugeId(Long gaugeId);

    void updatePsyOrderByOrderId(HashMap<String, Object> paramMap);

    /**
     * 查询测评订单
     *
     * @param orderId 测评订单编号
     * @return 课程订单
     */
    public PsyOrder selectPsyOrderByOrderId(String orderId);
}
