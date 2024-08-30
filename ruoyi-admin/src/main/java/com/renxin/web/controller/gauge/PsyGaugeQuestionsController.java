package com.renxin.web.controller.gauge;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
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
import com.renxin.gauge.domain.PsyGaugeQuestions;
import com.renxin.gauge.service.IPsyGaugeQuestionsService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 心理测评问题Controller
 * 
 * @author renxin
 * @date 2022-09-06
 */
@RestController
@RequestMapping("/gauge/questions")
public class PsyGaugeQuestionsController extends BaseController
{
    @Autowired
    private IPsyGaugeQuestionsService psyGaugeQuestionsService;

    /**
     * 查询心理测评问题列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:questions:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyGaugeQuestions psyGaugeQuestions)
    {
        //startPage();
        PageHelper.startPage(1, 200, "");
        List<PsyGaugeQuestions> list = psyGaugeQuestionsService.selectPsyGaugeQuestionsList(psyGaugeQuestions);
        return getDataTable(list);
    }

    /**
     * 导出心理测评问题列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:questions:export')")
    @Log(title = "心理测评问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyGaugeQuestions psyGaugeQuestions)
    {
        List<PsyGaugeQuestions> list = psyGaugeQuestionsService.selectPsyGaugeQuestionsList(psyGaugeQuestions);
        ExcelUtil<PsyGaugeQuestions> util = new ExcelUtil<PsyGaugeQuestions>(PsyGaugeQuestions.class);
        util.exportExcel(response, list, "心理测评问题数据");
    }

    /**
     * 获取心理测评问题详细信息
     */
    @PreAuthorize("@ss.hasPermi('gauge:questions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyGaugeQuestionsService.selectPsyGaugeQuestionsById(id));
    }

    /**
     * 新增心理测评问题
     */
    @PreAuthorize("@ss.hasPermi('gauge:questions:add')")
    @Log(title = "心理测评问题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyGaugeQuestions psyGaugeQuestions)
    {
        int i = psyGaugeQuestionsService.insertPsyGaugeQuestions(psyGaugeQuestions);
        psyGaugeQuestionsService.selectPsyGaugeQuestionsById(psyGaugeQuestions.getId());
        return toAjax(i);
    }

    /**
     * 修改心理测评问题
     */
    @PreAuthorize("@ss.hasPermi('gauge:questions:edit')")
    @Log(title = "心理测评问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyGaugeQuestions psyGaugeQuestions)
    {
        int i = psyGaugeQuestionsService.updatePsyGaugeQuestions(psyGaugeQuestions);
        psyGaugeQuestionsService.selectPsyGaugeQuestionsById(psyGaugeQuestions.getId());
        return toAjax(i);
    }

    /**
     * 删除心理测评问题
     */
    @PreAuthorize("@ss.hasPermi('gauge:questions:remove')")
    @Log(title = "心理测评问题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyGaugeQuestionsService.deletePsyGaugeQuestionsByIds(ids));
    }


    /**
     * 刷新测评问题缓存
     */
    //@PreAuthorize("@ss.hasPermi('course:course:list')")
    @GetMapping("/refreshCacheAll")
    public AjaxResult refreshCacheAll()
    {
        psyGaugeQuestionsService.refreshCacheAll();
        return AjaxResult.success();
    }
}
