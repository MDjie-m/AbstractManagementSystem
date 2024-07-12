package com.renxin.psychology.service;

import java.util.List;
import com.renxin.common.domain.PsyOrderLog;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.dto.OrderDTO;
import com.renxin.psychology.dto.OrderListDTO;
import com.renxin.psychology.request.PsyAdminOrderReq;
import com.renxin.psychology.request.PsyHxOrderReq;
import com.renxin.psychology.request.PsyRefOrderReq;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import com.renxin.wechat.vo.WechatPayVO;

/**
 * 咨询订单Service接口
 * 
 * @author renxin
 * @date 2023-06-26
 */
public interface IPsyConsultOrderService 
{

    OrderDTO getOrderDetail(Long id);

    OrderDTO getOrderDetailByNo(String orderNo);

    boolean checkNewByServe(Long orderId, Long serveId, Integer userId);

    PsyConsultOrder getOrderById(Long id);

    PsyConsultOrderVO getOne(Long id);

    String getOpenId(Long cId);

    List<PsyOrderLog> getLogs(String orderNo);

    List<PsyConsultOrder> getList(PsyAdminOrderReq req);

    List<PsyConsultOrder> getListForNotice(String last);

    List<PsyConsultOrder> getCancelList(int num);

    List<OrderListDTO> getOrderList(PsyConsultOrderVO req);

    String hx(PsyHxOrderReq req);

    String modifyRef(PsyRefOrderReq req);

    String modifyPrice(PsyConsultOrderVO req);

    void updatePayOrder(PsyConsultOrderVO req);

    Boolean sendPublicMsg(PsyConsultOrderVO psyOrder);

    void wechatPayNotify(PsyConsultOrderVO req);

    int doConsult(Long id, Long workId, Integer time);

    int add(PsyConsultOrderVO req);

    int close(Long orderId);

    void cancel(PsyConsultOrder order, String createBy);

    int update(PsyConsultOrderVO req);

    int remark(PsyConsultOrderVO req);

    int deleteAll(Long[] ids);

    int delete(Long id);
}
