package com.renxin.web.controller.psychology;

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
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.enums.BusinessType;
import com.renxin.psychology.domain.PsyConsultant;
import com.renxin.psychology.service.IPsyConsultantService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 心理咨询师Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/psychology/consultant")
public class PsyConsultantController extends BaseController
{
    @Autowired
    private IPsyConsultantService psyConsultantService;

    /**
     * 查询心理咨询师列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:consultant:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultant psyConsultant)
    {
        startPage();
        List<PsyConsultant> list = psyConsultantService.selectPsyConsultantList(psyConsultant);
        return getDataTable(list);
    }

    /**
     * 导出心理咨询师列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:consultant:export')")
    @Log(title = "心理咨询师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultant psyConsultant)
    {
        List<PsyConsultant> list = psyConsultantService.selectPsyConsultantList(psyConsultant);
        ExcelUtil<PsyConsultant> util = new ExcelUtil<PsyConsultant>(PsyConsultant.class);
        util.exportExcel(response, list, "心理咨询师数据");
    }

    /**
     * 获取心理咨询师详细信息
     */
    @PreAuthorize("@ss.hasPermi('psychology:consultant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantService.selectPsyConsultantById(id));
    }

    /**
     * 新增心理咨询师
     */
    @PreAuthorize("@ss.hasPermi('psychology:consultant:add')")
    @Log(title = "心理咨询师", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultant psyConsultant)
    {
        return toAjax(psyConsultantService.insertPsyConsultant(psyConsultant));
    }

    /**
     * 修改心理咨询师
     */
    @PreAuthorize("@ss.hasPermi('psychology:consultant:edit')")
    @Log(title = "心理咨询师", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultant psyConsultant)
    {
        return toAjax(psyConsultantService.updatePsyConsultant(psyConsultant));
    }

    /**
     * 删除心理咨询师
     */
    @PreAuthorize("@ss.hasPermi('psychology:consultant:remove')")
    @Log(title = "心理咨询师", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyConsultantService.deletePsyConsultantByIds(ids));
    }
}
