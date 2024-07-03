package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantEquityVerification;
import com.renxin.psychology.service.IPsyConsultantEquityVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师权益核销Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/verification")
public class PsyConsultantEquityVerificationController extends BaseController
{
    @Autowired
    private IPsyConsultantEquityVerificationService psyConsultantEquityVerificationService;

    /**
     * 查询咨询师权益核销列表
     */
    @PreAuthorize("@ss.hasPermi('system:verification:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantEquityVerification psyConsultantEquityVerification)
    {
        startPage();
        List<PsyConsultantEquityVerification> list = psyConsultantEquityVerificationService.selectPsyConsultantEquityVerificationList(psyConsultantEquityVerification);
        return getDataTable(list);
    }

    /**
     * 导出咨询师权益核销列表
     */
    @PreAuthorize("@ss.hasPermi('system:verification:export')")
    @Log(title = "咨询师权益核销", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantEquityVerification psyConsultantEquityVerification)
    {
        List<PsyConsultantEquityVerification> list = psyConsultantEquityVerificationService.selectPsyConsultantEquityVerificationList(psyConsultantEquityVerification);
        ExcelUtil<PsyConsultantEquityVerification> util = new ExcelUtil<PsyConsultantEquityVerification>(PsyConsultantEquityVerification.class);
        util.exportExcel(response, list, "咨询师权益核销数据");
    }

    /**
     * 获取咨询师权益核销详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:verification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantEquityVerificationService.selectPsyConsultantEquityVerificationById(id));
    }

    /**
     * 新增咨询师权益核销
     */
    @PreAuthorize("@ss.hasPermi('system:verification:add')")
    @Log(title = "咨询师权益核销", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantEquityVerification psyConsultantEquityVerification)
    {
        return toAjax(psyConsultantEquityVerificationService.insertPsyConsultantEquityVerification(psyConsultantEquityVerification));
    }

    /**
     * 修改咨询师权益核销
     */
    @PreAuthorize("@ss.hasPermi('system:verification:edit')")
    @Log(title = "咨询师权益核销", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantEquityVerification psyConsultantEquityVerification)
    {
        return toAjax(psyConsultantEquityVerificationService.updatePsyConsultantEquityVerification(psyConsultantEquityVerification));
    }

    /**
     * 删除咨询师权益核销
     */
    @PreAuthorize("@ss.hasPermi('system:verification:remove')")
    @Log(title = "咨询师权益核销", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyConsultantEquityVerificationService.deletePsyConsultantEquityVerificationByIds(ids));
    }
}
