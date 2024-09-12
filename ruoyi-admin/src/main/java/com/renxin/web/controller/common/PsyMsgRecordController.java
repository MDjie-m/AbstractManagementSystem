package com.renxin.web.controller.common;


import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.domain.PsyMsgRecord;
import com.renxin.common.service.IPsyMsgRecordService;
import com.renxin.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 消息记录Controller
 * 
 * @author renxin
 * @date 2024-09-11
 */
@RestController
@RequestMapping("/system/msgRecord")
public class PsyMsgRecordController extends BaseController
{
    @Autowired
    private IPsyMsgRecordService psyMsgRecordService;

    /**
     * 查询消息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyMsgRecord psyMsgRecord)
    {
        startPage();
        List<PsyMsgRecord> list = psyMsgRecordService.selectPsyMsgRecordList(psyMsgRecord);
        return getDataTable(list);
    }

    /**
     * 导出消息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyMsgRecord psyMsgRecord)
    {
        List<PsyMsgRecord> list = psyMsgRecordService.selectPsyMsgRecordList(psyMsgRecord);
        ExcelUtil<PsyMsgRecord> util = new ExcelUtil<PsyMsgRecord>(PsyMsgRecord.class);
        util.exportExcel(response, list, "消息记录数据");
    }

    /**
     * 获取消息记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyMsgRecordService.selectPsyMsgRecordById(id));
    }

    /**
     * 新增消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PsyMsgRecord psyMsgRecord)
    {
        return toAjax(psyMsgRecordService.insertPsyMsgRecord(psyMsgRecord));
    }

    /**
     * 修改消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PsyMsgRecord psyMsgRecord)
    {
        return toAjax(psyMsgRecordService.updatePsyMsgRecord(psyMsgRecord));
    }

    /**
     * 删除消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyMsgRecordService.deletePsyMsgRecordByIds(ids));
    }
}
