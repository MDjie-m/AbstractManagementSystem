package com.ruoyi.caseinfo.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.caseinfo.domain.CaseInfo;
import com.ruoyi.caseinfo.service.ICaseInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案件信息Controller
 * 
 * @author ysg
 * @date 2024-08-12
 */
@RestController
@RequestMapping("/caseinfo/manage")
public class CaseInfoController extends BaseController
{
    @Autowired
    private ICaseInfoService caseInfoService;

    /**
     * 查询案件信息列表
     */
    @PreAuthorize("@ss.hasPermi('caseinfo:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseInfo caseInfo)
    {
        startPage();
        List<CaseInfo> list = caseInfoService.selectCaseInfoList(caseInfo);
        return getDataTable(list);
    }

    /**
     * 导出案件信息列表
     */
    @PreAuthorize("@ss.hasPermi('caseinfo:manage:export')")
    @Log(title = "案件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CaseInfo caseInfo)
    {
        List<CaseInfo> list = caseInfoService.selectCaseInfoList(caseInfo);
        ExcelUtil<CaseInfo> util = new ExcelUtil<CaseInfo>(CaseInfo.class);
        util.exportExcel(response, list, "案件信息数据");
    }

    /**
     * 获取案件信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('caseinfo:manage:query')")
    @GetMapping(value = "/{caseId}")
    public AjaxResult getInfo(@PathVariable("caseId") Long caseId)
    {
        return success(caseInfoService.selectCaseInfoByCaseId(caseId));
    }

    /**
     * 新增案件信息
     */
    @PreAuthorize("@ss.hasPermi('caseinfo:manage:add')")
    @Log(title = "案件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseInfo caseInfo)
    {
        return toAjax(caseInfoService.insertCaseInfo(caseInfo));
    }

    /**
     * 修改案件信息
     */
    @PreAuthorize("@ss.hasPermi('caseinfo:manage:edit')")
    @Log(title = "案件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseInfo caseInfo)
    {
        return toAjax(caseInfoService.updateCaseInfo(caseInfo));
    }

    /**
     * 删除案件信息
     */
    @PreAuthorize("@ss.hasPermi('caseinfo:manage:remove')")
    @Log(title = "案件信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{caseIds}")
    public AjaxResult remove(@PathVariable Long[] caseIds)
    {
        return toAjax(caseInfoService.deleteCaseInfoByCaseIds(caseIds));
    }
}
