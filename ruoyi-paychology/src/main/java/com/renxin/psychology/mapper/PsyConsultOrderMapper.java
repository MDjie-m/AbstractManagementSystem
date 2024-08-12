package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.dto.OrderDTO;
import com.renxin.psychology.dto.OrderListDTO;
import com.renxin.psychology.request.PsyAdminOrderReq;
import com.renxin.psychology.vo.PsyConsultOrderVO;

import java.util.List;

/**
 * 咨询订单Mapper接口
 * 
 * @author renxin
 * @date 2023-06-26
 */
public interface PsyConsultOrderMapper extends BaseMapper<PsyConsultOrder>
{
    List<PsyConsultOrder> getList(PsyAdminOrderReq req);

    List<OrderListDTO> getOrderList(PsyConsultOrderVO req);

    OrderDTO getOrderDetail(Long id);

    int tombstonedByIds(Long[] ids);

    //我的顾客清单
    public List<PsyConsultOrderVO> queryUserList(PsyConsultOrderVO req);
}
