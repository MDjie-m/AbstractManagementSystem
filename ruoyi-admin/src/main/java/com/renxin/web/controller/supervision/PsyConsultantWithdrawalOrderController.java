package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantWithdrawalOrder;
import com.renxin.psychology.service.IPsyConsultantWithdrawalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 提现订单Controller
 * 
 * @author renxin
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/withdrawal/order")
public class PsyConsultantWithdrawalOrderController extends BaseController
{
    @Autowired
    private IPsyConsultantWithdrawalOrderService psyConsultantWithdrawalOrderService;

    /**
     * 查询提现订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantWithdrawalOrder psyConsultantWithdrawalOrder)
    {
        startPage();
        List<PsyConsultantWithdrawalOrder> list = psyConsultantWithdrawalOrderService.selectPsyConsultantWithdrawalOrderList(psyConsultantWithdrawalOrder);
        return getDataTable(list);
    }

    /**
     * 导出提现订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "提现订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantWithdrawalOrder psyConsultantWithdrawalOrder)
    {
        List<PsyConsultantWithdrawalOrder> list = psyConsultantWithdrawalOrderService.selectPsyConsultantWithdrawalOrderList(psyConsultantWithdrawalOrder);
        ExcelUtil<PsyConsultantWithdrawalOrder> util = new ExcelUtil<PsyConsultantWithdrawalOrder>(PsyConsultantWithdrawalOrder.class);
        util.exportExcel(response, list, "提现订单数据");
    }

    /**
     * 获取提现订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{withdrawalNo}")
    public AjaxResult getInfo(@PathVariable("withdrawalNo") Long withdrawalNo)
    {
        return AjaxResult.success(psyConsultantWithdrawalOrderService.selectPsyConsultantWithdrawalOrderByWithdrawalNo(withdrawalNo));
    }

    /**
     * 新增提现订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "提现订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantWithdrawalOrder psyConsultantWithdrawalOrder)
    {
        return toAjax(psyConsultantWithdrawalOrderService.insertPsyConsultantWithdrawalOrder(psyConsultantWithdrawalOrder));
    }

    /**
     * 修改提现订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "提现订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantWithdrawalOrder psyConsultantWithdrawalOrder)
    {
        return toAjax(psyConsultantWithdrawalOrderService.updatePsyConsultantWithdrawalOrder(psyConsultantWithdrawalOrder));
    }

    /**
     * 删除提现订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "提现订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{withdrawalNos}")
    public AjaxResult remove(@PathVariable Long[] withdrawalNos)
    {
        return toAjax(psyConsultantWithdrawalOrderService.deletePsyConsultantWithdrawalOrderByWithdrawalNos(withdrawalNos));
    }
}
