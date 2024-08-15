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
import com.renxin.gauge.domain.PsyGaugeClass;
import com.renxin.gauge.service.IPsyGaugeClassService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 测评分类Controller
 * 
 * @author renxin
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/psychology/gaugeClass")
public class PsyGaugeClassController extends BaseController
{
    @Autowired
    private IPsyGaugeClassService psyGaugeClassService;

    /**
     * 查询测评分类列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:gaugeClass:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyGaugeClass psyGaugeClass)
    {
        startPage();
        List<PsyGaugeClass> list = psyGaugeClassService.selectPsyGaugeClassList(psyGaugeClass);
        return getDataTable(list);
    }

    /**
     * 导出测评分类列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:gaugeClass:export')")
    @Log(title = "测评分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyGaugeClass psyGaugeClass)
    {
        List<PsyGaugeClass> list = psyGaugeClassService.selectPsyGaugeClassList(psyGaugeClass);
        ExcelUtil<PsyGaugeClass> util = new ExcelUtil<PsyGaugeClass>(PsyGaugeClass.class);
        util.exportExcel(response, list, "测评分类数据");
    }

    /**
     * 获取测评分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('psychology:gaugeClass:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyGaugeClassService.selectPsyGaugeClassById(id));
    }

    /**
     * 新增测评分类
     */
    @PreAuthorize("@ss.hasPermi('psychology:gaugeClass:add')")
    @Log(title = "测评分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyGaugeClass psyGaugeClass)
    {
        try {
            int res = psyGaugeClassService.insertPsyGaugeClass(psyGaugeClass);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增测评分类失败");
        }
    }

    /**
     * 修改测评分类
     */
    @PreAuthorize("@ss.hasPermi('psychology:gaugeClass:edit')")
    @Log(title = "测评分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyGaugeClass psyGaugeClass)
    {
        try {
            int res = psyGaugeClassService.updatePsyGaugeClass(psyGaugeClass);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "修改测评分类失败");
        }
    }

    /**
     * 删除测评分类
     */
    @PreAuthorize("@ss.hasPermi('psychology:gaugeClass:remove')")
    @Log(title = "测评分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        try {
            int res = psyGaugeClassService.deletePsyGaugeClassByIds(ids);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "删除测评分类失败");
        }
    }
}
