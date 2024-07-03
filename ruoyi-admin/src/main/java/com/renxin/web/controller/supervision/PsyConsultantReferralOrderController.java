package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantReferralOrder;
import com.renxin.psychology.service.IPsyConsultantReferralOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 转介订单Controller
 * 
 * @author renxin
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/referral/order")
public class PsyConsultantReferralOrderController extends BaseController
{
    @Autowired
    private IPsyConsultantReferralOrderService psyConsultantReferralOrderService;

    /**
     * 查询转介订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantReferralOrder psyConsultantReferralOrder)
    {
        startPage();
        List<PsyConsultantReferralOrder> list = psyConsultantReferralOrderService.selectPsyConsultantReferralOrderList(psyConsultantReferralOrder);
        return getDataTable(list);
    }

    /**
     * 导出转介订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "转介订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantReferralOrder psyConsultantReferralOrder)
    {
        List<PsyConsultantReferralOrder> list = psyConsultantReferralOrderService.selectPsyConsultantReferralOrderList(psyConsultantReferralOrder);
        ExcelUtil<PsyConsultantReferralOrder> util = new ExcelUtil<PsyConsultantReferralOrder>(PsyConsultantReferralOrder.class);
        util.exportExcel(response, list, "转介订单数据");
    }

    /**
     * 获取转介订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{referralId}")
    public AjaxResult getInfo(@PathVariable("referralId") Long referralId)
    {
        return AjaxResult.success(psyConsultantReferralOrderService.selectPsyConsultantReferralOrderByReferralId(referralId));
    }

    /**
     * 新增转介订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "转介订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantReferralOrder psyConsultantReferralOrder)
    {
        return toAjax(psyConsultantReferralOrderService.insertPsyConsultantReferralOrder(psyConsultantReferralOrder));
    }

    /**
     * 修改转介订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "转介订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantReferralOrder psyConsultantReferralOrder)
    {
        return toAjax(psyConsultantReferralOrderService.updatePsyConsultantReferralOrder(psyConsultantReferralOrder));
    }

    /**
     * 删除转介订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "转介订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{referralIds}")
    public AjaxResult remove(@PathVariable Long[] referralIds)
    {
        return toAjax(psyConsultantReferralOrderService.deletePsyConsultantReferralOrderByReferralIds(referralIds));
    }
}
