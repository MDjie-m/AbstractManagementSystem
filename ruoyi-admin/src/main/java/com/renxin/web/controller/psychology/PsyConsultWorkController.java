package com.renxin.web.controller.psychology;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultWork;
import com.renxin.psychology.request.PsyConsultWorkReq;
import com.renxin.psychology.request.PsyWorkReq;
import com.renxin.psychology.service.IPsyConsultWorkService;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 排班Controller
 *
 * @author renxin
 * @date 2023-06-25
 */
@RestController
@RequestMapping("/psychology/work")
public class PsyConsultWorkController extends BaseController
{
    @Resource
    private IPsyConsultWorkService psyConsultWorkService;

    /**
     * 查询排班列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:work:query')")
    @GetMapping("/getWorks")
    public TableDataInfo getWorks(PsyWorkReq req)
    {
        startPage();
        List<Long> ids = psyConsultWorkService.getConsultIds(req);
        TableDataInfo dataInfo = getDataTable(ids);
        List<HashMap<String, String>> list = psyConsultWorkService.getWorks(req, ids);
        dataInfo.setRows(list);
        return dataInfo;
    }

    @GetMapping("/getWorkHeader/{month}")
    public AjaxResult getWorkHeader(@PathVariable("month") String month)
    {
        return AjaxResult.success(psyConsultWorkService.getWorkHeader(month));
    }

    /**
     * 查询排班列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:work:query')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultWorkVO req)
    {
        startPage();
        List<PsyConsultWork> list = psyConsultWorkService.getList(req);
        return getDataTable(list);
    }

    /**
     * 导出排班列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:work:export')")
    @Log(title = "排班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultWorkVO req)
    {
        List<PsyConsultWork> list = psyConsultWorkService.getList(req);
        ExcelUtil<PsyConsultWork> util = new ExcelUtil<PsyConsultWork>(PsyConsultWork.class);
        util.exportExcel(response, list, "排班数据");
    }

    /**
     * 获取排班详细信息
     */
    @PreAuthorize("@ss.hasPermi('psychology:work:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultWorkService.getOne(id));
    }

    /**
     * 新增排班
     */
    @PreAuthorize("@ss.hasPermi('psychology:work:add')")
    @Log(title = "排班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultWorkReq req)
    {
        psyConsultWorkService.doSave(req);
        return AjaxResult.success();
    }

    /**
     * 修改排班
     */
    @PreAuthorize("@ss.hasPermi('psychology:work:edit')")
    @Log(title = "排班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultWorkVO req)
    {
        List<PsyConsultWork> works = psyConsultWorkService.checkDataUnique(req);
        if (!CollectionUtils.isEmpty(works)) {
            return error("所选时段已存在排班，请修改时段后重试");
        }
        return toAjax(psyConsultWorkService.update(req));
    }
}
