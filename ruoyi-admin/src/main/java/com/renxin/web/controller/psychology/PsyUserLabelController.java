package com.renxin.web.controller.psychology;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.renxin.psychology.domain.PsyUserLabel;
import com.renxin.psychology.service.IPsyUserLabelService;
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
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 用户标签Controller
 * 
 * @author renxin
 * @date 2024-08-09
 */
@RestController
@RequestMapping("/system/label")
public class PsyUserLabelController extends BaseController
{
    @Autowired
    private IPsyUserLabelService psyUserLabelService;

    /**
     * 查询用户标签列表
     */
    //@PreAuthorize("@ss.hasPermi('system:label:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyUserLabel psyUserLabel)
    {
        startPage();
        List<PsyUserLabel> list = psyUserLabelService.selectPsyUserLabelList(psyUserLabel);
        return getDataTable(list);
    }

    /**
     * 导出用户标签列表
     */
    //@PreAuthorize("@ss.hasPermi('system:label:export')")
    @Log(title = "用户标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyUserLabel psyUserLabel)
    {
        List<PsyUserLabel> list = psyUserLabelService.selectPsyUserLabelList(psyUserLabel);
        ExcelUtil<PsyUserLabel> util = new ExcelUtil<PsyUserLabel>(PsyUserLabel.class);
        util.exportExcel(response, list, "用户标签数据");
    }

    /**
     * 获取用户标签详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:label:query')")
    @PostMapping(value = "/queryById")
    public AjaxResult getInfo(@RequestBody PsyUserLabel psyUserLabel)
    {
        return AjaxResult.success(psyUserLabelService.selectPsyUserLabelById(psyUserLabel.getId()));
    }

    /**
     * 新增用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:add')")
    @Log(title = "用户标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyUserLabel psyUserLabel)
    {
        return toAjax(psyUserLabelService.insertPsyUserLabel(psyUserLabel));
    }

    /**
     * 修改用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:edit')")
    @Log(title = "用户标签", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyUserLabel psyUserLabel)
    {
        return toAjax(psyUserLabelService.updatePsyUserLabel(psyUserLabel));
    }

    /**
     * 删除用户标签
     */
    //@PreAuthorize("@ss.hasPermi('system:label:remove')")
//    @Log(title = "用户标签", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(psyUserLabelService.deletePsyUserLabelByIds(ids));
//    }
}
