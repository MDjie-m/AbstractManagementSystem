package com.renxin.pocket.controller.user;

import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.enums.OrderStatus;
import com.renxin.course.constant.CourConstant;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.domain.CourOrder;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.service.ICourOrderService;
import com.renxin.course.vo.CourseOrderVO;
import com.renxin.course.vo.OrderVO;
import com.renxin.framework.web.service.PocketTokenService;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.service.IPsyOrderService;
import com.renxin.pocket.controller.user.req.PocketUserOrderReq;
import com.renxin.psychology.dto.OrderListDTO;
import com.renxin.psychology.service.IPsyConsultOrderService;
import com.renxin.psychology.vo.PsyConsultOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户订单Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/pocket/user/order")
public class PocketUserOrderController extends BaseController
{

    @Resource
    private IPsyConsultOrderService psyConsultOrderService;
    @Resource
    private IPsyOrderService psyOrderService;

    @Autowired
    private ICourOrderService courOrderService;
    @Autowired
    private ICourCourseService courCourseService;


    @Autowired
    private PocketTokenService pocketTokenService;


    /**
     * 获取订单列表
     */
    @PostMapping(value = "/list")
    @RateLimiter
    public AjaxResult getOrderList(@RequestBody PocketUserOrderReq req, HttpServletRequest request)
    {
        LoginDTO loginUser = pocketTokenService.getLoginUser(request);
        Long userId = loginUser.getUserId();
        if(req.getOrderType()==1) {

            PsyConsultOrderVO psyConsultOrderVO = new PsyConsultOrderVO();
            psyConsultOrderVO.setUserId(userId);
            psyConsultOrderVO.setStatus(req.getOrderStatus() == null ? null : req.getOrderStatus().toString());
            List<OrderListDTO> list = psyConsultOrderService.getOrderList(psyConsultOrderVO);
            return AjaxResult.success(list);
        } else if(req.getOrderType()==2){
            List<CourseOrderVO>  courOrderlist= courOrderService.getOrderListByUserId(userId,req.getOrderStatus());
            return AjaxResult.success(courOrderlist);
        }else if(req.getOrderType()==3){
            PsyOrder psyOrder =new PsyOrder();
            psyOrder.setUserId(loginUser.getUserId());
            //.psyOrder.setOrderStatus(req.getOrderStatus());
            if(req.getOrderStatus()!=null && req.getOrderStatus().intValue()==1){
                psyOrder.setOrderStatus(2);
                psyOrder.setGaugeStatus(2);
            }else if(req.getOrderStatus()!=null && req.getOrderStatus().intValue()==2){
                psyOrder.setOrderStatus(2);
                psyOrder.setGaugeStatus(1);
            }
            List<PsyOrder> list = psyOrderService.queryOrderInfo(psyOrder);
            return AjaxResult.success(list);

        }else{
            return null;
        }
    }

}
