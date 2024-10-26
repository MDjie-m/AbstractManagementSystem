package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.FjxUserFeedback;
import com.ruoyi.system.service.IFjxUserFeedbackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户反馈信息Controller
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
@RestController
@RequestMapping("/fjx/feedback")
public class FjxUserFeedbackController extends BaseController
{
    @Autowired
    private IFjxUserFeedbackService fjxUserFeedbackService;

    /**
     * 查询用户反馈信息列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjxUserFeedback fjxUserFeedback)
    {
        startPage();
        List<FjxUserFeedback> list = fjxUserFeedbackService.selectFjxUserFeedbackList(fjxUserFeedback);
        return getDataTable(list);
    }

    /**
     * 导出用户反馈信息列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:feedback:export')")
    @Log(title = "用户反馈信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FjxUserFeedback fjxUserFeedback)
    {
        List<FjxUserFeedback> list = fjxUserFeedbackService.selectFjxUserFeedbackList(fjxUserFeedback);
        ExcelUtil<FjxUserFeedback> util = new ExcelUtil<FjxUserFeedback>(FjxUserFeedback.class);
        util.exportExcel(response, list, "用户反馈信息数据");
    }

    /**
     * 获取用户反馈信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(fjxUserFeedbackService.selectFjxUserFeedbackById(id));
    }

    /**
     * 新增用户反馈信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:feedback:add')")
    @Log(title = "用户反馈信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjxUserFeedback fjxUserFeedback)
    {
        return toAjax(fjxUserFeedbackService.insertFjxUserFeedback(fjxUserFeedback));
    }

    /**
     * 修改用户反馈信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:feedback:edit')")
    @Log(title = "用户反馈信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjxUserFeedback fjxUserFeedback)
    {
        return toAjax(fjxUserFeedbackService.updateFjxUserFeedback(fjxUserFeedback));
    }

    /**
     * 删除用户反馈信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:feedback:remove')")
    @Log(title = "用户反馈信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fjxUserFeedbackService.deleteFjxUserFeedbackByIds(ids));
    }
}
