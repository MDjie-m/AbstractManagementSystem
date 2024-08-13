package com.renxin.psychology.service;

import com.renxin.psychology.domain.PsyConsultOrderItem;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.request.PsyWorkReq;

import java.util.List;

/**
 * 咨询订单Service接口
 * 
 * @author renxin
 * @date 2023-06-26
 */
public interface IPsyConsultOrderItemService
{

    void del(Long id);

    void add(PsyConsultOrderItem item);

    void update(PsyConsultOrderItem item);

    PsyConsultOrderItem getOne(Long id);

    boolean updateBatch(List<PsyConsultOrderItem> entities);

    List<PsyConsultOrderItem> getList(Long orderId);

    PsyConsultOrderItem getOneByOrderId(Long orderId);

    List<OrderItemDTO> getOrderItemList(Long consultantId,String day);    
    
    List<OrderItemDTO> getTodoList(PsyWorkReq req);    
    
    List<OrderItemDTO> queryOrderItemList(OrderItemDTO req);    
    
    Integer getTimeNumForConsulted(PsyWorkReq req);    

}
