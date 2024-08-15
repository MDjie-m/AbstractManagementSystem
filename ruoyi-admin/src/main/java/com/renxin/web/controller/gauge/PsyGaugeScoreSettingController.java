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
import com.renxin.gauge.domain.PsyGaugeScoreSetting;
import com.renxin.gauge.service.IPsyGaugeScoreSettingService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 心理测评普通设置Controller
 * 
 * @author renxin
 * @date 2022-09-10
 */
@RestController
@RequestMapping("/gauge/setting")
public class PsyGaugeScoreSettingController extends BaseController
{
    @Autowired
    private IPsyGaugeScoreSettingService psyGaugeScoreSettingService;

    /**
     * 查询心理测评普通设置列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:setting:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyGaugeScoreSetting psyGaugeScoreSetting)
    {
        startPage();
        List<PsyGaugeScoreSetting> list = psyGaugeScoreSettingService.selectPsyGaugeScoreSettingList(psyGaugeScoreSetting);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('gauge:setting:listByIds')")
    @GetMapping("list/by/{ids}")
    public TableDataInfo list(@PathVariable Long[] ids)
    {
        startPage();
        List<PsyGaugeScoreSetting> list = psyGaugeScoreSettingService.selectPsyGaugeScoreSettingListByIds(ids);
        return getDataTable(list);
    }

    /**
     * 导出心理测评普通设置列表
     */
    @PreAuthorize("@ss.hasPermi('gauge:setting:export')")
    @Log(title = "心理测评普通设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyGaugeScoreSetting psyGaugeScoreSetting)
    {
        List<PsyGaugeScoreSetting> list = psyGaugeScoreSettingService.selectPsyGaugeScoreSettingList(psyGaugeScoreSetting);
        ExcelUtil<PsyGaugeScoreSetting> util = new ExcelUtil<PsyGaugeScoreSetting>(PsyGaugeScoreSetting.class);
        util.exportExcel(response, list, "心理测评普通设置数据");
    }

    /**
     * 获取心理测评普通设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('gauge:setting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyGaugeScoreSettingService.selectPsyGaugeScoreSettingById(id));
    }

    /**
     * 新增心理测评普通设置
     */
    @PreAuthorize("@ss.hasPermi('gauge:setting:add')")
    @Log(title = "心理测评普通设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyGaugeScoreSetting psyGaugeScoreSetting)
    {


        return AjaxResult.success(psyGaugeScoreSettingService.insertPsyGaugeScoreSetting(psyGaugeScoreSetting));

    }

    /**
     * 修改心理测评普通设置
     */
    @PreAuthorize("@ss.hasPermi('gauge:setting:edit')")
    @Log(title = "心理测评普通设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyGaugeScoreSetting psyGaugeScoreSetting)
    {
        return toAjax(psyGaugeScoreSettingService.updatePsyGaugeScoreSetting(psyGaugeScoreSetting));
    }

    /**
     * 删除心理测评普通设置
     */
    @PreAuthorize("@ss.hasPermi('gauge:setting:remove')")
    @Log(title = "心理测评普通设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyGaugeScoreSettingService.deletePsyGaugeScoreSettingByIds(ids));
    }
}
