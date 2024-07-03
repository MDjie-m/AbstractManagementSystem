package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantOrder;
import com.renxin.psychology.service.IPsyConsultantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 团队督导(组织)订单Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/order")
public class PsyConsultantOrderController extends BaseController
{
    @Autowired
    private IPsyConsultantOrderService psyConsultantOrderService;

    /**
     * 查询团队督导(组织)订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantOrder psyConsultantOrder)
    {
        startPage();
        List<PsyConsultantOrder> list = psyConsultantOrderService.selectPsyConsultantOrderList(psyConsultantOrder);
        return getDataTable(list);
    }

    /**
     * 导出团队督导(组织)订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "团队督导(组织)订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantOrder psyConsultantOrder)
    {
        List<PsyConsultantOrder> list = psyConsultantOrderService.selectPsyConsultantOrderList(psyConsultantOrder);
        ExcelUtil<PsyConsultantOrder> util = new ExcelUtil<PsyConsultantOrder>(PsyConsultantOrder.class);
        util.exportExcel(response, list, "团队督导(组织)订单数据");
    }

    /**
     * 获取团队督导(组织)订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{orderNo}")
    public AjaxResult getInfo(@PathVariable("orderNo") String orderNo)
    {
        return AjaxResult.success(psyConsultantOrderService.selectPsyConsultantOrderByOrderNo(orderNo));
    }

    /**
     * 新增团队督导(组织)订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "团队督导(组织)订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantOrder psyConsultantOrder)
    {
        return toAjax(psyConsultantOrderService.insertPsyConsultantOrder(psyConsultantOrder));
    }

    /**
     * 修改团队督导(组织)订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "团队督导(组织)订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantOrder psyConsultantOrder)
    {
        return toAjax(psyConsultantOrderService.updatePsyConsultantOrder(psyConsultantOrder));
    }

    /**
     * 删除团队督导(组织)订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "团队督导(组织)订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderNos}")
    public AjaxResult remove(@PathVariable String[] orderNos)
    {
        return toAjax(psyConsultantOrderService.deletePsyConsultantOrderByOrderNos(orderNos));
    }
}
