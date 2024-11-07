package com.renxin.psychology.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renxin.common.dcloud.CloudFunctions;
import com.renxin.common.utils.bean.BeanUtils;
import com.renxin.common.wechat.wxMsg.NoticeMessage;
import com.renxin.psychology.domain.PsyConsultBillItem;
import com.renxin.psychology.domain.PsyConsultantAccountRecord;
import com.renxin.psychology.domain.PsyConsultantSupervisionMember;
import com.renxin.psychology.dto.BillItemDTO;
import com.renxin.psychology.mapper.PsyConsultBillItemMapper;
import com.renxin.psychology.request.PsyAdminBillReq;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    @Autowired
    private IPsyConsultService consultService;
    
    @Resource
    private IPsyConsultantTeamSupervisionService teamService;
    
    @Resource
    private IPsyConsultantSupervisionMemberService memberService;

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
        //自动完成已到时的任务
        mapper.finishOrderItem();
        //刷新主订单状态
        mapper.finishConsultOrder();
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
        
        //自动完成已到时的任务 (个督,个人体验)
        mapper.finishSchedule();
        //刷新主订单状态
        mapper.finishConsultantOrder();
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
            // 以keyId 分组 计数
            Map<String, List<PsyConsultBillItem>> collect = consultantOrderItemList.stream().collect(groupingBy(PsyConsultBillItem::getKeyId));
            collect.forEach((key, value) -> {
                value.forEach(it -> {
                    it.setBuyNumStr(StrUtil.format("第{}次", it.getBuyNum()));
                    //固定分账金额为空, 说明此为 个督/体验
                    if (ObjectUtils.isEmpty(it.getBrokerage())){
                        if (ObjectUtils.isEmpty(it) || ObjectUtils.isEmpty(it.getRatio()) || ObjectUtils.isEmpty(it.getPrice())){
                            log.error("出现分账异常数据:" + it.toString());
                        }
                        it.setPrice(it.getOrderTotal().divide(new BigDecimal(it.getOrderNum()), 2, BigDecimal.ROUND_UP));
                        it.setBrokerage(it.getPrice().multiply(it.getRatio().divide(new BigDecimal(100), 2, BigDecimal.ROUND_UP)));
                        //剩余次数 = 总次数 - 已执行次数
                        it.setNum(it.getOrderNum() - it.getBuyNum());
                    }else{
                    //已有固定分账金额, 则为团队服务.
                        it.setPayAndChargeNum(it.getTeamTimeNUm());
                        it.setPayUserId(10000L);//系统官方
                    }
                });
            });
            mapper.insertBatch(consultantOrderItemList);
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
                record.setBillItemId(bill.getId()+"");
                record.setPayAmount(bill.getBrokerage());
                record.setCreateBy("system");
                record.setCreateTime(new Date());
                record.setUseTime(bill.getUseTime());
                record.setDelFlag("0");
            acctRecordList.add(record);
        }
        accountRecordService.insertPsyConsultantAccountRecordBatch(acctRecordList);

        //将schedule中的团督任务设为[已分账]
        List<Long> teamScheduleIdList = billList.stream().filter(p -> p.getScheduleType() == 21).map(PsyConsultBillItem::getId).collect(Collectors.toList());
        scheduleService.updateStatusBatch(teamScheduleIdList, "4");//团督已分账
        
        //咨询师缓存刷新
        consultService.refreshCacheByIdList(billList.stream().map(p -> p.getConsultId()).collect(Collectors.toList()));//收费咨询师
        consultService.refreshCacheByIdList(billList.stream().filter(p -> p.getPayUserType() == 2 && p.getPayUserId() != 10000L).map(p -> p.getPayUserId()).collect(Collectors.toList()));//付费咨询师
        //团督成员缓存刷新
        ////本次分账涉及的团队清单
        List<Long> teamIdList = billList.stream().filter(p -> p.getScheduleType() == 21).map(PsyConsultBillItem::getTeamId).collect(Collectors.toList());
        ////这些团队涉及的咨询师成员清单
        List<PsyConsultantSupervisionMember> memberList = memberService.getBaseMapper().selectList(new LambdaQueryWrapper<PsyConsultantSupervisionMember>()
                .select(PsyConsultantSupervisionMember::getMemberId)
                .in(PsyConsultantSupervisionMember::getTeamSupervisionId, teamIdList)
                .eq(PsyConsultantSupervisionMember::getMemberType, 1)//正式成员
                .eq(PsyConsultantSupervisionMember::getMemberUserType, 2)//咨询师
                .in(PsyConsultantSupervisionMember::getTeamType, Arrays.asList("1,2"))//团督或1V2督导
        );
        List<Long> consultantMemberIdList = memberList.stream().map(p -> p.getMemberId()).distinct().collect(Collectors.toList());
        consultService.refreshCacheByIdList(consultantMemberIdList);

        //todo通知--  咨询师分账已完成
        CloudFunctions cloudFunctions = new CloudFunctions();
        for (PsyConsultantAccountRecord record : acctRecordList) {
            NoticeMessage notice = new NoticeMessage();
                notice.setPush_clientid(consultService.getClientIdByConsultantId(record.getConsultantId()));
                notice.setTitle("分账通知");
                notice.setContent("您有一笔分成到账, 金额" + record.getPayAmount() + "元, 可申请提现.");
            cloudFunctions.sendGeTuiMessage(notice);
        }
       
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdate(List<PsyConsultBillItem> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            this.updateBatchById(list);
        }
    }

}
