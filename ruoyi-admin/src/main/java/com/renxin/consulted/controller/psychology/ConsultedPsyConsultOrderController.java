package com.renxin.consulted.controller.psychology;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.service.IPsyConsultOrderService;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 心理咨询师Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/consulted/consult/order")
public class ConsultedPsyConsultOrderController extends BaseController
{

    @Resource
    private IPsyConsultOrderService psyConsultOrderService;

    @GetMapping(value = "/getOrderInfo/{id}")
    @RateLimiter
    public AjaxResult getOrderInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultOrderService.getOne(id));
    }

    @GetMapping(value = "/getOrderDetail/{id}")
    @RateLimiter
    public AjaxResult getOrderDetail(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultOrderService.getOrderDetail(id));
    }

    @GetMapping(value = "/getOrderDetailByNo/{orderNo}")
    @RateLimiter
    public AjaxResult getOrderDetailByNo(@PathVariable("orderNo") String orderNo)
    {
        return AjaxResult.success(psyConsultOrderService.getOrderDetailByNo(orderNo));
    }

    /**
     * 获取订单列表
     */
    @PostMapping(value = "/getOrderList")
    @RateLimiter
    public AjaxResult getOrderList(@RequestBody PsyConsultOrderVO req)
    {
        return AjaxResult.success(psyConsultOrderService.getOrderList(req));
    }

    /**
     * 咨询
     */
    @PostMapping(value = "/doConsult/{id}/{workId}/{time}")
    @RateLimiter
    public AjaxResult doConsult(@PathVariable("id") Long id, @PathVariable("workId") Long workId, @PathVariable("time") Integer time)
    {
        return AjaxResult.success(psyConsultOrderService.doConsult(id, workId, time));
    }

    /**
     * 取消
     */
    @GetMapping(value = "/cancel/{id}")
    @RateLimiter
    public AjaxResult cancel(@PathVariable("id") Long id)
    {
        PsyConsultOrder order = psyConsultOrderService.getOrderById(id);
        psyConsultOrderService.cancel(order, order.getNickName());
        return AjaxResult.success();
    }

}
