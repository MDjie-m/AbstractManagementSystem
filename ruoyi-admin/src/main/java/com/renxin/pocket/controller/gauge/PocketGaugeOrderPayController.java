package com.renxin.pocket.controller.gauge;

import com.renxin.common.annotation.Log;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.gauge.domain.PsyOrderPay;
import com.renxin.gauge.service.IPsyOrderPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 心理咨询订单支付信息Controller
 *
 * @author renxin
 * @date 2022-10-12
 */
@RestController
@RequestMapping("/pocket/gauge/order/pay")
@Api(value = "pocketGaugeOrderPayController" ,tags = {"心理测评支付信息api"})
public class PocketGaugeOrderPayController extends BaseController {
    @Autowired
    private IPsyOrderPayService psyOrderPayService;

    /**
     * 查询心理咨询订单支付信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:pay:list')")
    @PostMapping("/list")
    @ApiIgnore
    @RateLimiter
    public TableDataInfo list(PsyOrderPay psyOrderPay) {
        startPage();
        List<PsyOrderPay> list = psyOrderPayService.selectPsyOrderPayList(psyOrderPay);
        return getDataTable(list);
    }

    /**
     * 导出心理咨询订单支付信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:pay:export')")
    @Log(title = "心理咨询订单支付信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiIgnore
    @RateLimiter
    public void export(HttpServletResponse response, PsyOrderPay psyOrderPay) {
        List<PsyOrderPay> list = psyOrderPayService.selectPsyOrderPayList(psyOrderPay);
        ExcelUtil<PsyOrderPay> util = new ExcelUtil<PsyOrderPay>(PsyOrderPay.class);
        util.exportExcel(response, list, "心理咨询订单支付信息数据");
    }

    /**
     * 获取心理咨询订单支付信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:pay:query')")
    @PostMapping(value = "/{id}")
    @ApiOperation("获取支付信息")
    @RateLimiter
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(psyOrderPayService.selectPsyOrderPayById(id));
    }
}
