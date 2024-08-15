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
import com.renxin.gauge.domain.PsyGaugeMultiSetting;
import com.renxin.gauge.service.IPsyGaugeMultiSettingService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 心理测评多维设置Controller
 * 
 * @author renxin
 * @date 2022-09-10
 */
@RestController
@RequestMapping("/gauge/multiSetting")
public class PsyGaugeMultiSettingController extends BaseController
{
    @Autowired
    private IPsyGaugeMultiSettingService psyGaugeMultiSettingService;

    /**
     * 查询心理测评多维设置列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:multiSetting:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyGaugeMultiSetting psyGaugeMultiSetting)
    {
        startPage();
        List<PsyGaugeMultiSetting> list = psyGaugeMultiSettingService.selectPsyGaugeMultiSettingList(psyGaugeMultiSetting);
        return getDataTable(list);
    }

    /**
     * 导出心理测评多维设置列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:multiSetting:export')")
    @Log(title = "心理测评多维设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyGaugeMultiSetting psyGaugeMultiSetting)
    {
        List<PsyGaugeMultiSetting> list = psyGaugeMultiSettingService.selectPsyGaugeMultiSettingList(psyGaugeMultiSetting);
        ExcelUtil<PsyGaugeMultiSetting> util = new ExcelUtil<PsyGaugeMultiSetting>(PsyGaugeMultiSetting.class);
        util.exportExcel(response, list, "心理测评多维设置数据");
    }

    /**
     * 获取心理测评多维设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('gauge:multiSetting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyGaugeMultiSettingService.selectPsyGaugeMultiSettingById(id));
    }

    /**
     * 新增心理测评多维设置
     */
    @PreAuthorize("@ss.hasPermi('gauge:multiSetting:add')")
    @Log(title = "心理测评多维设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyGaugeMultiSetting psyGaugeMultiSetting)
    {
        return toAjax(psyGaugeMultiSettingService.insertPsyGaugeMultiSetting(psyGaugeMultiSetting));
    }

    /**
     * 修改心理测评多维设置
     */
    @PreAuthorize("@ss.hasPermi('gauge:multiSetting:edit')")
    @Log(title = "心理测评多维设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyGaugeMultiSetting psyGaugeMultiSetting)
    {
        return toAjax(psyGaugeMultiSettingService.updatePsyGaugeMultiSetting(psyGaugeMultiSetting));
    }

    /**
     * 删除心理测评多维设置
     */
    @PreAuthorize("@ss.hasPermi('gauge:multiSetting:remove')")
    @Log(title = "心理测评多维设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyGaugeMultiSettingService.deletePsyGaugeMultiSettingByIds(ids));
    }
}
