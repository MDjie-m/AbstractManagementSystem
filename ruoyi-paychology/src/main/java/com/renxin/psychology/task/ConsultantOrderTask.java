package com.renxin.psychology.task;

import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.service.IPsyConsultOrderService;
import com.renxin.psychology.service.IPsyConsultantOrderService;
import com.renxin.psychology.service.IPsyCouponService;
import com.renxin.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("consultantOrderTask")
public class ConsultantOrderTask {

    @Resource
    private ISysConfigService configService;

    @Resource
    private IPsyConsultantOrderService consultantOrderService;

    @Resource
    private IPsyCouponService couponService;

    public void cancel()
    {
        String val = configService.selectConfigByKey("order.cancel.time");
        int num = StringUtils.isNotEmpty(val) ? Integer.parseInt(val) : 15;

        List<PsyConsultantOrder> cancelList = consultantOrderService.getCancelList(num);
        if (CollectionUtils.isNotEmpty(cancelList)) {
            cancelList.forEach(order -> {
                order.setStatus("3");//已取消
                order.setUpdateBy("job");
                order.setUpdateTime(new Date());
                consultantOrderService.updatePsyConsultantOrder(order);
                couponService.returnCoupon(order.getCouponNo());//归还优惠券
            });
            List<Long> collect = cancelList.stream().map(PsyConsultantOrder::getId).collect(Collectors.toList());
            log.info("咨询订单取消, 订单id={} 自动修改订单状态为已取消，操作已完成", collect);
        }
    }

}
