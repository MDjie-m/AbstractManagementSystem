package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantSupervisionMemberRecord;
import com.renxin.psychology.service.IPsyConsultantSupervisionMemberRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 督导笔记、成员评价 标签评价Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/record")
public class PsyConsultantSupervisionMemberRecordController extends BaseController
{
    @Autowired
    private IPsyConsultantSupervisionMemberRecordService psyConsultantSupervisionMemberRecordService;

    /**
     * 查询督导笔记、成员评价 标签评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantSupervisionMemberRecord psyConsultantSupervisionMemberRecord)
    {
        startPage();
        List<PsyConsultantSupervisionMemberRecord> list = psyConsultantSupervisionMemberRecordService.selectPsyConsultantSupervisionMemberRecordList(psyConsultantSupervisionMemberRecord);
        return getDataTable(list);
    }

    /**
     * 导出督导笔记、成员评价 标签评价列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "督导笔记、成员评价 标签评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantSupervisionMemberRecord psyConsultantSupervisionMemberRecord)
    {
        List<PsyConsultantSupervisionMemberRecord> list = psyConsultantSupervisionMemberRecordService.selectPsyConsultantSupervisionMemberRecordList(psyConsultantSupervisionMemberRecord);
        ExcelUtil<PsyConsultantSupervisionMemberRecord> util = new ExcelUtil<PsyConsultantSupervisionMemberRecord>(PsyConsultantSupervisionMemberRecord.class);
        util.exportExcel(response, list, "督导笔记、成员评价 标签评价数据");
    }

    /**
     * 获取督导笔记、成员评价 标签评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantSupervisionMemberRecordService.selectPsyConsultantSupervisionMemberRecordById(id));
    }

    /**
     * 新增督导笔记、成员评价 标签评价
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "督导笔记、成员评价 标签评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantSupervisionMemberRecord psyConsultantSupervisionMemberRecord)
    {
        return toAjax(psyConsultantSupervisionMemberRecordService.insertPsyConsultantSupervisionMemberRecord(psyConsultantSupervisionMemberRecord));
    }

    /**
     * 修改督导笔记、成员评价 标签评价
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "督导笔记、成员评价 标签评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantSupervisionMemberRecord psyConsultantSupervisionMemberRecord)
    {
        return toAjax(psyConsultantSupervisionMemberRecordService.updatePsyConsultantSupervisionMemberRecord(psyConsultantSupervisionMemberRecord));
    }

    /**
     * 删除督导笔记、成员评价 标签评价
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "督导笔记、成员评价 标签评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyConsultantSupervisionMemberRecordService.deletePsyConsultantSupervisionMemberRecordByIds(ids));
    }
}
