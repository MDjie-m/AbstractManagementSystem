package com.renxin.psychology.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.utils.bean.BeanUtils;
import com.renxin.psychology.domain.PsyConsultBillItem;
import com.renxin.psychology.domain.PsyConsultantAccountRecord;
import com.renxin.psychology.dto.BillItemDTO;
import com.renxin.psychology.mapper.PsyConsultBillItemMapper;
import com.renxin.psychology.request.PsyAdminBillReq;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.IPsyConsultBillItemService;
import com.renxin.psychology.service.IPsyConsultantAccountRecordService;
import com.renxin.psychology.service.IPsyConsultantScheduleService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.groupingBy;

/**
 * 咨询核销记录Service业务层处理
 *
 * @author renxin
 * @date 2023-11-22
 */
@Service
public class PsyConsultBillItemServiceImpl extends ServiceImpl<PsyConsultBillItemMapper, PsyConsultBillItem> implements IPsyConsultBillItemService
{

    @Resource
    private PsyConsultBillItemMapper mapper;
    
    @Resource
    private IPsyConsultantScheduleService scheduleService;
    
    @Resource
    IPsyConsultantAccountRecordService accountRecordService;

    @Override
    public List<PsyConsultBillItem> getItemListForDetail(PsyAdminBillReq req) {
        return mapper.getItemListForDetail(req);
    }

    @Override
    public BigDecimal getBillItemSum(PsyAdminBillReq req) {
        return mapper.getBillItemSum(req);
    }

    @Override
    public List<BillItemDTO> getItemList(PsyAdminBillReq req) {
        return mapper.getItemList(req);
    }

    @Override
    public List<PsyConsultBillItem> getBillItems(PsyAdminBillReq req) {
        return mapper.getBillItems(req);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAdd() {
        //来访者订单核销
        List<PsyConsultBillItem> orderItems = mapper.getOrderItems();
        if (CollectionUtils.isNotEmpty(orderItems)) {
            // 以orderId 分组 计数
            Map<String, List<PsyConsultBillItem>> collect = orderItems.stream().collect(groupingBy(PsyConsultBillItem::getOrderId));
            collect.forEach((key, value) -> {
                // 修复 咨询次数 咨询次数问题
                // 序号 = 已核销-当前数+1
                int i = value.get(0).getOrderNum() - value.get(0).getNum() - value.size() + 1;
                AtomicInteger num = new AtomicInteger(i);
                value.forEach(it -> {
                    it.setBuyNum(num.get());
                    it.setBuyNumStr(StrUtil.format("第{}次", num.get()));
                    num.getAndIncrement();
                    it.setPrice(it.getOrderTotal().divide(new BigDecimal(it.getOrderNum()), 2, BigDecimal.ROUND_UP));
                    it.setBrokerage(it.getPrice().multiply(it.getRatio().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP)));
                });
            });
            this.saveOrUpdateBatch(orderItems);
            allotToAcct(orderItems);
        }
        
        //自动完成已到时的任务
        mapper.finishSchedule();
        
        //咨询师订单核销
        List<PsyConsultBillItem> consultantOrderItemList = mapper.getConsultantOrderItems();
        if (CollectionUtils.isNotEmpty(consultantOrderItemList)) {
            //个督/体验  需要查询已服务的次数
            consultantOrderItemList.forEach(p -> {
                if (ObjectUtils.isEmpty(p.getBuyNum()) || p.getBuyNum() == 0){
                    PsyWorkReq workReq = new PsyWorkReq();
                    workReq.setOrderNo(p.getOrderId());
                    workReq.setRealTime(p.getRealTime());
                    int timeNum = scheduleService.getTimeNum(workReq);
                    p.setBuyNum(timeNum);
                }
            });
            // 以orderId 分组 计数
            Map<String, List<PsyConsultBillItem>> collect = consultantOrderItemList.stream().collect(groupingBy(PsyConsultBillItem::getKeyId));
            collect.forEach((key, value) -> {
                value.forEach(it -> {
                    it.setBuyNumStr(StrUtil.format("第{}次", it.getBuyNum()));
                    if (ObjectUtils.isEmpty(it.getBrokerage())){
                        it.setPrice(it.getOrderTotal().divide(new BigDecimal(it.getOrderNum()), 2, BigDecimal.ROUND_UP));
                        it.setBrokerage(it.getPrice().multiply(it.getRatio().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP)));
                    }
                });
            });
            this.saveOrUpdateBatch(consultantOrderItemList);
            allotToAcct(consultantOrderItemList);
        }
        
        
    }
    
    //分配金额到账户
    private void allotToAcct(List<PsyConsultBillItem> billList){
        List<PsyConsultantAccountRecord> acctRecordList = new ArrayList<PsyConsultantAccountRecord>();
        for (PsyConsultBillItem bill : billList) {
            PsyConsultantAccountRecord record = new PsyConsultantAccountRecord();
                record.setConsultantId(bill.getConsultId());
                record.setPayType("0");//分成
                record.setOrderId(bill.getId()+"");
                record.setPayAmount(bill.getBrokerage());
                record.setCreateBy("system");
                record.setCreateTime(new Date());
            acctRecordList.add(record);
        }
        accountRecordService.insertPsyConsultantAccountRecordBatch(acctRecordList);
        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate(List<PsyConsultBillItem> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            this.updateBatchById(list);
        }
    }

}
