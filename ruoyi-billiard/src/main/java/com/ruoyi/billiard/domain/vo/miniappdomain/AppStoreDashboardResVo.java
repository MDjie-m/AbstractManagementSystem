package com.ruoyi.billiard.domain.vo.miniappdomain;

import com.ruoyi.billiard.domain.vo.Label;
import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

@Data
public class AppStoreDashboardResVo {
    //实时指标概览
    //台桌开台率
    @Label(value = "台桌开台率", group = "实时指标",suffix = "%")
    private BigDecimal deskOpenRate;

    //台桌开台数量
    @Label(value = "台桌开台数", group = "实时指标")
    private Long deskOpenCount;
    //台桌总数
    @Label(value = "台桌总数", group = "实时指标")
    private Long  deskCount;

    //未开台台桌
    @Label(value = "台桌空闲数", group = "实时指标")
    private Long deskNotOpenCount;

    //助教上课率
    @Label(value = "助教上课率", group = "实时指标",suffix = "%")
    private BigDecimal tutorWorkRate;

    //助教上课数
    @Label(value = "助教上课数", group = "实时指标")
    private Long tutorBusyCount;
    //助教空闲数量
    @Label(value = "助教空闲数", group = "实时指标")
    private Long tutorWaitCount;

    //营业额概览

    //营业额
    @Label(value = "营业额", group = "基础概况", tip = "门店订单应收款总额")
    private BigDecimal totalAmountDue;
    //总收入
    @Label(value = "总收入", group = "基础概况", tip = "门店实际收入总额")
    private BigDecimal totalAmount;
    //客单数
    @Label(value = "客单数", group = "基础概况", tip = "订单总数量")
    private Long orderCount;

    @Label(value = "客单价", group = "基础概况", tip = "每笔订单消费额")
    private BigDecimal orderAvg;

    @Label(value = "客单件", group = "基础概况", tip = "订单购买商品平均数量")
    private BigDecimal orderGoodsCountAvg;

    @Label(value = "退款额", group = "基础概况", tip = "给客人退款的总额")
    private BigDecimal totalRefundAmount;


    //商品概览


    @Label(value = "商品销售额", group = "商品概览", tip = "商品销售总金额")
    private BigDecimal goodsAmount;
    //客单数
    @Label(value = "商品销售成本", group = "商品概览", tip = "商品销售成本")
    private BigDecimal goodsCost;

    @Label(value = "商品毛利", group = "商品概览", tip = "商品销售额减去商品成本")
    private BigDecimal goodsProfit;

    @Label(value = "商品毛利率", group = "商品概览", tip = "毛利/商品销售额",suffix = "%")
    private BigDecimal goodsProfitRate;

    @Label(value = "客单价", group = "商品概览", tip = "每单商品平均价格")
    private BigDecimal goodsOrderAvg;

    @Label(value = "商品单数", group = "商品概览", tip = "每单商品平均价格")
    private Long goodsOrderCount;

    //台桌概览

    @Label(value = "总台桌费", group = "台桌概览", tip = "台桌费用合计")
    private BigDecimal deskAmount;

    @Label(value = "开台率", group = "台桌概览", tip = "发生过计费的台桌占比")
    private BigDecimal deskOpenRate1;

    @Label(value = "平均客单时长", group = "台桌概览", tip = "平均每笔订单计费时长")
    private BigDecimal orderTimeAvg;
    @Label(value = "平均台桌时长", group = "台桌概览", tip = "平均每张台桌计费时长")
    private BigDecimal deskTimeAvg;

    @Label(value = "台桌单数", group = "台桌概览", tip = "发生计费的订单数量")
    private Long deskTotalOrderCount;


    //助教概览

    @Label(value = "上课费", group = "助教概览", tip = "台桌费用合计")
    private BigDecimal tutorAmount;

    @Label(value = "助教上课率", group = "助教概览", tip = "上课助教/所有助教数量",suffix = "%")
    private BigDecimal tutorWorkedTodayRate;


    @Label(value = "助教单数", group = "助教概览", tip = "发生助教计费的订单数量")
    private Long tutorTotalOrderCount;


    //会员概览

    @Label(value = "会员充值金额", group = "会员概览")
    private BigDecimal memberRechargeAmount;


    @Label(value = "会员消费金额", group = "会员概览")
    private BigDecimal memberUsedAmount;

    @Label(value = "新客人数量", group = "会员概览", tip = "当天新增的会员")
    private Long newMemberCount;


    @Label(value = "会员客单率", group = "会员概览", tip = "会员消费订单数/总订单数",suffix = "%")
    private BigDecimal memberOrderRate;

    @Label(value = "会员消费占比", group = "会员概览", tip = "会员消费金额/门店总收入",suffix = "%")
    private BigDecimal memberAmountRate;


    @SneakyThrows
    public List<AppDashboardGroupVo> toGouppList() {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        Map<String, AppDashboardGroupVo> groupVoMap = new HashMap<>();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Label label = field.getAnnotation(Label.class);
            if (Objects.isNull(label)) {
                continue;
            }
            AppDashboardGroupVo groupVo = groupVoMap.get(label.group());
            if (Objects.isNull(groupVo)) {
                groupVo = new AppDashboardGroupVo();
                groupVo.setTitle(label.group());
                groupVoMap.put(label.group(), groupVo);
            }

            Object val = field.get(this);
            groupVo.getItemList().add(new AppDashboardItemVo<>(label.value(), val, label.tip(),label.suffix()));
        }
        return new ArrayList<>(groupVoMap.values());
    }
}
