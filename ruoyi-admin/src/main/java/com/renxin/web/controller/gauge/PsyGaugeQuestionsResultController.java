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
import com.renxin.gauge.domain.PsyGaugeQuestionsResult;
import com.renxin.gauge.service.IPsyGaugeQuestionsResultService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 心理测评问题结果Controller
 * 
 * @author renxin
 * @date 2022-09-10
 */
@RestController
@RequestMapping("/gauge/result")
public class PsyGaugeQuestionsResultController extends BaseController
{
    @Autowired
    private IPsyGaugeQuestionsResultService psyGaugeQuestionsResultService;

    /**
     * 查询心理测评问题结果列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:result:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyGaugeQuestionsResult psyGaugeQuestionsResult)
    {
        startPage();
        List<PsyGaugeQuestionsResult> list = psyGaugeQuestionsResultService.selectPsyGaugeQuestionsResultList(psyGaugeQuestionsResult);
        return getDataTable(list);
    }

    /**
     * 导出心理测评问题结果列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:result:export')")
    @Log(title = "心理测评问题结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyGaugeQuestionsResult psyGaugeQuestionsResult)
    {
        List<PsyGaugeQuestionsResult> list = psyGaugeQuestionsResultService.selectPsyGaugeQuestionsResultList(psyGaugeQuestionsResult);
        ExcelUtil<PsyGaugeQuestionsResult> util = new ExcelUtil<PsyGaugeQuestionsResult>(PsyGaugeQuestionsResult.class);
        util.exportExcel(response, list, "心理测评问题结果数据");
    }

    /**
     * 获取心理测评问题结果详细信息
     */
    @PreAuthorize("@ss.hasPermi('gauge:result:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyGaugeQuestionsResultService.selectPsyGaugeQuestionsResultById(id));
    }

    /**
     * 新增心理测评问题结果
     */
    /*@PreAuthorize("@ss.hasPermi('gauge:result:add')")
    @Log(title = "心理测评问题结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyGaugeQuestionsResult psyGaugeQuestionsResult)
    {
        return toAjax(psyGaugeQuestionsResultService.insertPsyGaugeQuestionsResult(psyGaugeQuestionsResult));
    }*/

    /**
     * 修改心理测评问题结果
     */
    @PreAuthorize("@ss.hasPermi('gauge:result:edit')")
    @Log(title = "心理测评问题结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyGaugeQuestionsResult psyGaugeQuestionsResult)
    {
        return toAjax(psyGaugeQuestionsResultService.updatePsyGaugeQuestionsResult(psyGaugeQuestionsResult));
    }

    /**
     * 删除心理测评问题结果
     */
    @PreAuthorize("@ss.hasPermi('gauge:result:remove')")
    @Log(title = "心理测评问题结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyGaugeQuestionsResultService.deletePsyGaugeQuestionsResultByIds(ids));
    }
}
