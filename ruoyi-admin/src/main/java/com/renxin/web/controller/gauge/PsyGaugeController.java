package com.renxin.web.controller.gauge;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.renxin.web.controller.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.renxin.gauge.domain.PsyGauge;
import com.renxin.gauge.service.IPsyGaugeService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 心理测评Controller
 * 
 * @author renxin
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/psychology/gauge")
public class PsyGaugeController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private IPsyGaugeService psyGaugeService;

    /**
     * 查询心理测评列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:gauge:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyGauge psyGauge)
    {
        startPage();
        List<PsyGauge> list = psyGaugeService.selectPsyGaugeList(psyGauge);
        return getDataTable(list);
    }

    @GetMapping("/getGauges")
    public List<PsyGauge> getGauges(PsyGauge psyGauge)
    {
        return psyGaugeService.selectPsyGaugeList(psyGauge);
    }

    /**
     * 导出心理测评列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:gauge:export')")
    @Log(title = "心理测评", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyGauge psyGauge)
    {
        List<PsyGauge> list = psyGaugeService.selectPsyGaugeList(psyGauge);
        ExcelUtil<PsyGauge> util = new ExcelUtil<PsyGauge>(PsyGauge.class);
        util.exportExcel(response, list, "心理测评数据");
    }

    /**
     * 获取心理测评详细信息
     */
    @PreAuthorize("@ss.hasPermi('psychology:gauge:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyGaugeService.selectPsyGaugeById(id));
    }

    /**
     * 新增心理测评
     */
    @PreAuthorize("@ss.hasPermi('psychology:gauge:add')")
    @Log(title = "心理测评", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyGauge psyGauge)
    {

        psyGauge.setCreateBy(getUsername());
        try {
            int res = psyGaugeService.insertPsyGauge(psyGauge);
            psyGaugeService.selectPsyGaugeById(psyGauge.getId());
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增心理测评失败");
        }
    }

    /**
     * 修改心理测评
     */
    @PreAuthorize("@ss.hasPermi('psychology:gauge:edit')")
    @Log(title = "心理测评", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyGauge psyGauge)
    {
        psyGauge.setUpdateBy(getUsername());
        try {
            int res = psyGaugeService.updatePsyGauge(psyGauge);
            psyGaugeService.selectPsyGaugeById(psyGauge.getId());
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增心理测评失败");
        }
    }

    /**
     * 删除心理测评
     */
    @PreAuthorize("@ss.hasPermi('psychology:gauge:remove')")
    @Log(title = "心理测评", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        try {
            int res = psyGaugeService.deletePsyGaugeByIds(ids);
            return AjaxResult.success(res);
        } catch (Exception e) {
            return AjaxResult.error(500, "新增心理测评失败");
        }
    }

    /**
     * 刷新测评缓存
     */
    //@PreAuthorize("@ss.hasPermi('course:course:list')")
    @GetMapping("/refreshCacheAll")
    public AjaxResult refreshCacheAll()
    {
        psyGaugeService.refreshCacheAll();
        return AjaxResult.success();
    }
    
}
