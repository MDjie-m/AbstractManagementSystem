package com.renxin.web.controller.gauge;

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
import com.renxin.gauge.domain.PsyGaugeQuestionsOptions;
import com.renxin.gauge.service.IPsyGaugeQuestionsOptionsService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 心理测评问题选项Controller
 * 
 * @author renxin
 * @date 2022-09-06
 */
@RestController
@RequestMapping("/gauge/options")
public class PsyGaugeQuestionsOptionsController extends BaseController
{
    @Autowired
    private IPsyGaugeQuestionsOptionsService psyGaugeQuestionsOptionsService;

    /**
     * 查询心理测评问题选项列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:options:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyGaugeQuestionsOptions psyGaugeQuestionsOptions)
    {
        startPage();
        List<PsyGaugeQuestionsOptions> list = psyGaugeQuestionsOptionsService.selectPsyGaugeQuestionsOptionsList(psyGaugeQuestionsOptions);
        return getDataTable(list);
    }

    /**
     * 导出心理测评问题选项列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:options:export')")
    @Log(title = "心理测评问题选项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyGaugeQuestionsOptions psyGaugeQuestionsOptions)
    {
        List<PsyGaugeQuestionsOptions> list = psyGaugeQuestionsOptionsService.selectPsyGaugeQuestionsOptionsList(psyGaugeQuestionsOptions);
        ExcelUtil<PsyGaugeQuestionsOptions> util = new ExcelUtil<PsyGaugeQuestionsOptions>(PsyGaugeQuestionsOptions.class);
        util.exportExcel(response, list, "心理测评问题选项数据");
    }

    /**
     * 获取心理测评问题选项详细信息
     */
    @PreAuthorize("@ss.hasPermi('gauge:options:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyGaugeQuestionsOptionsService.selectPsyGaugeQuestionsOptionsById(id));
    }

    /**
     * 新增心理测评问题选项
     */
    @PreAuthorize("@ss.hasPermi('gauge:options:add')")
    @Log(title = "心理测评问题选项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyGaugeQuestionsOptions psyGaugeQuestionsOptions)
    {
        return toAjax(psyGaugeQuestionsOptionsService.insertPsyGaugeQuestionsOptions(psyGaugeQuestionsOptions));
    }

    /**
     * 修改心理测评问题选项
     */
    @PreAuthorize("@ss.hasPermi('gauge:options:edit')")
    @Log(title = "心理测评问题选项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyGaugeQuestionsOptions psyGaugeQuestionsOptions)
    {
        return toAjax(psyGaugeQuestionsOptionsService.updatePsyGaugeQuestionsOptions(psyGaugeQuestionsOptions));
    }

    /**
     * 删除心理测评问题选项
     */
    @PreAuthorize("@ss.hasPermi('gauge:options:remove')")
    @Log(title = "心理测评问题选项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyGaugeQuestionsOptionsService.deletePsyGaugeQuestionsOptionsByIds(ids));
    }
}
