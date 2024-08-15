package com.renxin.gauge.task;

import com.renxin.gauge.constant.GaugeConstant;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("gaugeOrderTask")
public class OrderTask {

    @Resource
    private ISysConfigService configService;

    @Resource
    private IPsyOrderService psyOrderService;

    public void cancel()
    {
        String val = configService.selectConfigByKey("order.cancel.time");
        int num = StringUtils.isNotEmpty(val) ? Integer.parseInt(val) : 15;
        List<PsyOrder> cancelList = psyOrderService.getOrderByCancel(num);
        if (CollectionUtils.isNotEmpty(cancelList)) {
            cancelList.forEach(order -> {
                order.setOrderStatus(GaugeConstant.GAUGE_ORDER_STATUE_CANCELED);
                psyOrderService.updatePsyOrder(order);
            });
            List<Long> collect = cancelList.stream().map(PsyOrder::getId).collect(Collectors.toList());
            log.info("测评订单取消, 订单id={} 自动修改订单状态为已取消，操作已完成", collect);
        }
    }

}
