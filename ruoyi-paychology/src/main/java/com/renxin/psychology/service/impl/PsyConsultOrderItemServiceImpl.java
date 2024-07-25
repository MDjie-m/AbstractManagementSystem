package com.renxin.psychology.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.psychology.constant.ConsultConstant;
import com.renxin.psychology.domain.PsyConsultOrderItem;
import com.renxin.psychology.dto.OrderItemDTO;
import com.renxin.psychology.mapper.PsyConsultOrderItemMapper;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.IPsyConsultOrderItemService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 咨询订单Service业务层处理
 * 
 * @author renxin
 * @date 2023-06-26
 */
@Service
public class PsyConsultOrderItemServiceImpl extends ServiceImpl<PsyConsultOrderItemMapper, PsyConsultOrderItem> implements IPsyConsultOrderItemService
{

    @Resource
    private PsyConsultOrderItemMapper psyConsultOrderItemMapper;

    @Override
    public void del(Long id) {
        psyConsultOrderItemMapper.deleteById(id);
    }

    @Override
    public void add(PsyConsultOrderItem item) {
        psyConsultOrderItemMapper.insert(item);
    }

    @Override
    public void update(PsyConsultOrderItem item) {
        psyConsultOrderItemMapper.updateById(item);
    }

    @Override
    public PsyConsultOrderItem getOne(Long id) {
        return psyConsultOrderItemMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatch(List<PsyConsultOrderItem> entities) {
        return this.saveOrUpdateBatch(entities);
    }

    @Override
    public List<PsyConsultOrderItem> getList(Long orderId) {
        LambdaQueryWrapper<PsyConsultOrderItem> wp = new LambdaQueryWrapper<>();
        wp.eq(PsyConsultOrderItem::getOrderId, orderId);
        wp.between(PsyConsultOrderItem::getStatus, ConsultConstant.ONSULT_ORDER_ITEM_CREATED, ConsultConstant.ONSULT_ORDER_ITEM_FINISHED);
        return psyConsultOrderItemMapper.selectList(wp);
    }

    @Override
    public PsyConsultOrderItem getOneByOrderId(Long orderId) {
        LambdaQueryWrapper<PsyConsultOrderItem> wp = new LambdaQueryWrapper<>();
        wp.eq(PsyConsultOrderItem::getOrderId, orderId);
        wp.eq(PsyConsultOrderItem::getStatus, ConsultConstant.ONSULT_ORDER_ITEM_CREATED);
        List<PsyConsultOrderItem> items = psyConsultOrderItemMapper.selectList(wp);
        return CollectionUtils.isNotEmpty(items) ? items.get(0) : null;
    }

    @Override
    public List<OrderItemDTO> getOrderItemList(Long consultantId,String day) {

       return  psyConsultOrderItemMapper.getOrderItemList(consultantId, day);
    }
    
    @Override
    public List<OrderItemDTO> getTodoList(PsyWorkReq req){
        return psyConsultOrderItemMapper.getTodoList(req);
    }
    
    @Override
    public Integer getTimeNumForConsulted(PsyWorkReq req){
        return psyConsultOrderItemMapper.getTimeNumForConsulted(req);
    }


}
