package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantEquity;
import com.renxin.psychology.service.IPsyConsultantEquityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师权益Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/equity")
public class PsyConsultantEquityController extends BaseController
{
    @Autowired
    private IPsyConsultantEquityService psyConsultantEquityService;

    /**
     * 查询咨询师权益列表
     */
    @PreAuthorize("@ss.hasPermi('system:equity:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantEquity psyConsultantEquity)
    {
        startPage();
        List<PsyConsultantEquity> list = psyConsultantEquityService.selectPsyConsultantEquityList(psyConsultantEquity);
        return getDataTable(list);
    }

    /**
     * 导出咨询师权益列表
     */
    @PreAuthorize("@ss.hasPermi('system:equity:export')")
    @Log(title = "咨询师权益", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantEquity psyConsultantEquity)
    {
        List<PsyConsultantEquity> list = psyConsultantEquityService.selectPsyConsultantEquityList(psyConsultantEquity);
        ExcelUtil<PsyConsultantEquity> util = new ExcelUtil<PsyConsultantEquity>(PsyConsultantEquity.class);
        util.exportExcel(response, list, "咨询师权益数据");
    }

    /**
     * 获取咨询师权益详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:equity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantEquityService.selectPsyConsultantEquityById(id));
    }

    /**
     * 新增咨询师权益
     */
    @PreAuthorize("@ss.hasPermi('system:equity:add')")
    @Log(title = "咨询师权益", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantEquity psyConsultantEquity)
    {
        return toAjax(psyConsultantEquityService.insertPsyConsultantEquity(psyConsultantEquity));
    }

    /**
     * 修改咨询师权益
     */
    @PreAuthorize("@ss.hasPermi('system:equity:edit')")
    @Log(title = "咨询师权益", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantEquity psyConsultantEquity)
    {
        return toAjax(psyConsultantEquityService.updatePsyConsultantEquity(psyConsultantEquity));
    }

    /**
     * 删除咨询师权益
     */
    @PreAuthorize("@ss.hasPermi('system:equity:remove')")
    @Log(title = "咨询师权益", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyConsultantEquityService.deletePsyConsultantEquityByIds(ids));
    }
}
