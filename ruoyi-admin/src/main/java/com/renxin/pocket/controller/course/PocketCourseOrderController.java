package com.renxin.pocket.controller.course;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.utils.OrderIdUtils;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourOrderService;
import com.renxin.course.vo.OrderVO;
import com.renxin.gauge.domain.PsyOrderPay;
import com.renxin.gauge.service.IPsyOrderPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pocket/course/order")
@Api(value = "pocketCourseOrderController" ,tags = {"课程订单API"})
public class PocketCourseOrderController extends BaseController
{
    @Autowired
    private ICourOrderService courOrderService;

    @Autowired
    private ICourCourseService courCourseService;

    @Autowired
    private IPsyOrderPayService psyOrderPayService;

    /**
     * 根据用户ID查询课程订单列表
     */
    @PostMapping("/list")
    @ApiOperation("查询课程订单列表")
    @RateLimiter
    public AjaxResult list(@RequestBody CourOrder courOrder)
    {
        List<CourOrder> list = courOrderService.selectCourOrderList(courOrder);

        List<OrderVO> orderVOList = new ArrayList<>();
        // 返回的订单信息中需要包含课程详情信息
        list.forEach(item -> {
            if (item.getStatus() == CourConstant.COUR_ORDER_STATUE_CANCELED) {
                return;
            }
            CourCourse courCourse = courCourseService.selectCourCourseById(item.getCourseId());
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(item, orderVO);
            orderVO.setCourseInfo(courCourse);
            orderVOList.add(orderVO);
        });
        return AjaxResult.success(orderVOList);
    }

    /**
     * 根据订单ID查询课程订单详情
     */
    @PostMapping("/detail")
    @ApiOperation("根据订单ID查询课程订单详情")
    @RateLimiter
    public AjaxResult detail(@RequestParam(value = "orderId") Integer id)
    {
        CourOrder courOrder = courOrderService.selectCourOrderById(id);
        CourCourse course = courCourseService.selectCourCourseById(courOrder.getCourseId());
        courOrder.setCourseInfo(course);

        return AjaxResult.success(courOrder);
    }

    /**
     * 根据订单信息生成课程订单
     */
    @PutMapping("/add")
    @ApiOperation("根据订单信息生成课程订单")
    @RateLimiter
    public AjaxResult generateOrder(@RequestBody CourOrder courOrder)
    {

        courOrder.setOrderId(OrderIdUtils.getOrderId());
        courOrder.setCreateTime(new Date());
        courOrder.setStatus(0); // 0代表订单处于创建状态

        return AjaxResult.success(courOrderService.insertCourOrder(courOrder));
    }

    /**
     * 取消订单
     */
    @PostMapping("/cancel")
    @ApiOperation("取消订单")
    @RateLimiter
    public AjaxResult cancelOrder(@RequestParam Integer orderId)    {

        CourOrder courOrder = new CourOrder();
        courOrder.setId(orderId);
        courOrder.setStatus(CourConstant.COUR_ORDER_STATUE_CANCELED); // 订单处于取消状态

        // 订单取消同时关闭支付信息
        PsyOrderPay orderPay = new PsyOrderPay();
        orderPay.setPayStatus(CourConstant.PAY_STATUE_CANCEL);
        orderPay.setPayType(CourConstant.PAY_WAY_WEIXIN);
        orderPay.setOrderId(orderId);
        psyOrderPayService.updatePsyOrderPayByOrderId(orderPay);

        return AjaxResult.success(courOrderService.updateCourOrder(courOrder));
    }
}
