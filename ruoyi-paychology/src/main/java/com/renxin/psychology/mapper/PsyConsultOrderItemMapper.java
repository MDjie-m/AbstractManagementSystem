package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsultOrderItem;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.request.PsyWorkReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 咨询订单Mapper接口
 * 
 * @author renxin
 * @date 2023-06-26
 */
public interface PsyConsultOrderItemMapper extends BaseMapper<PsyConsultOrderItem>
{
    public List<OrderItemDTO> getOrderItemList(@Param("consultId") Long consultId, @Param("day") String day);

    public List<OrderItemDTO> getTodoList(PsyWorkReq req);
    
    public Integer getTimeNumForConsulted(PsyWorkReq req);

    public List<OrderItemDTO> queryOrderItemList(OrderItemDTO req);

    Integer querySumTime(PsyWorkReq req);
}
