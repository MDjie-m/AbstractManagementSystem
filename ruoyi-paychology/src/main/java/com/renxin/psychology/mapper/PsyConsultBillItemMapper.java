package com.renxin.psychology.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.psychology.domain.PsyConsultBillItem;
import com.renxin.psychology.dto.BillItemDTO;
import com.renxin.psychology.request.PsyAdminBillReq;

import java.math.BigDecimal;
import java.util.List;

public interface PsyConsultBillItemMapper extends BaseMapper<PsyConsultBillItem>
{

    List<PsyConsultBillItem> getOrderItems();
    
    List<PsyConsultBillItem> getOrderItemsConsultant();

    List<BillItemDTO> getItemList(PsyAdminBillReq req);

    List<PsyConsultBillItem> getItemListForDetail(PsyAdminBillReq req);

    List<PsyConsultBillItem> getBillItems(PsyAdminBillReq req);

    BigDecimal getBillItemSum(PsyAdminBillReq req);

    List<PsyConsultBillItem> getConsultantOrderItems();
    
    void finishSchedule();
}
