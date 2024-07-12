package com.renxin.consulted.controller.order;

import com.renxin.common.annotation.Log;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.constant.RespMessageConstants;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.LoginDTO;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.enums.OrderStatus;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.framework.web.service.ConsultedTokenService;
import com.renxin.gauge.domain.PsyOrder;
import com.renxin.gauge.service.IPsyOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 心理测评订单信息Controller
 *
 * @author renxin
 * @date 2022-10-12
 */
@RestController
@RequestMapping("/consulted/gauge/order")
@Api(value = "consultedPsyOrderController" ,tags = {"心理测评订单api"})
public class ConsultedPsyOrderController extends BaseController {
    @Autowired
    private IPsyOrderService psyOrderService;

    @Autowired
    private ConsultedTokenService consultedTokenService;

    /**
     * 查询心理测评订单信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @PostMapping("/list")
    @ApiOperation("获取订单分页列表")
    @RateLimiter
    public TableDataInfo list(@RequestBody PsyOrder psyOrder, HttpServletRequest request) {
        if (psyOrder.getUserId() == null) {
            LoginDTO loginUser = consultedTokenService.getLoginUser(request);
            psyOrder.setUserId(loginUser.getUserId());
        }

        // 必须是已支付的订单
        psyOrder.setOrderStatus(OrderStatus.FINISHED.getValue());
        startPage();
        List<PsyOrder> list = psyOrderService.queryOrderInfo(psyOrder);
        return getDataTable(list);
    }

    /**
     * 导出心理测评订单信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "心理测评订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiIgnore
    @RateLimiter
    public void export(HttpServletResponse response, PsyOrder psyOrder) {
        List<PsyOrder> list = psyOrderService.selectPsyOrderList(psyOrder);
        ExcelUtil<PsyOrder> util = new ExcelUtil<PsyOrder>(PsyOrder.class);
        util.exportExcel(response, list, "心理测评订单信息数据");
    }

    /**
     * 获取心理测评订单信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取订单详细信息")
    @RateLimiter
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(psyOrderService.selectPsyOrderById(id));
    }


    /**
     * 获取我的报告数量
     */
    @PostMapping(value = "/getMyReportNum")
    @ApiOperation("获取我的报告数量")
    @RateLimiter
    public AjaxResult getMyReportNum(HttpServletRequest request) {
        LoginDTO loginUser = consultedTokenService.getLoginUser(request);
        return AjaxResult.success(RespMessageConstants.OPERATION_SUCCESS ,psyOrderService.getMyReportNum(loginUser));
    }

}
