package com.renxin.web.controller.psychology;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.SecurityUtils;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsult;
import com.renxin.psychology.domain.PsyConsultServe;
import com.renxin.psychology.request.PsyAdminConsultReq;
import com.renxin.psychology.request.PsyRefConsultServeReq;
import com.renxin.psychology.service.IPsyConsultConfigService;
import com.renxin.psychology.service.IPsyConsultService;
import com.renxin.psychology.vo.PsyConsultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 心理咨询师Controller
 *
 * @author renxin
 * @date 2023-06-25
 */
@RestController
@RequestMapping("/psychology/consult")
public class PsyConsultController extends BaseController
{
    @Resource
    private IPsyConsultService psyConsultService;

    @Resource
    private IPsyConsultConfigService psyConsultConfigService;

    @GetMapping("/getAttrs/{dictTypes}")
    public AjaxResult getAttrs(@PathVariable(value = "dictTypes") String[] dictTypes)
    {
        return AjaxResult.success(psyConsultConfigService.getConfigByTypes(dictTypes));
    }

    /**
     * 获取咨询师入驻申请详细信息
     */
    @GetMapping(value = "/getAvailableUserName/{name}")
    public AjaxResult getAvailableUserName(@PathVariable("name") String name)
    {
        return AjaxResult.success(psyConsultService.getAvailableUserName(name));
    }

    /**
     * 查询心理咨询师列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:query')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultVO psyConsult)
    {
        startPage();
        List<PsyConsult> list = psyConsultService.getList(psyConsult);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('psychology:consult:query')")
    @GetMapping("/adminList")
    public TableDataInfo list(PsyAdminConsultReq psyConsult)
    {
        startPage();
        List<PsyConsult> list = psyConsultService.getList(psyConsult);
        return getDataTable(list);
    }

    /**
     * 查询所有心理咨询师
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:query')")
    @GetMapping("/getAll")
    public AjaxResult getAll(PsyConsultVO psyConsult)
    {
        return AjaxResult.success(psyConsultService.getList(psyConsult));
    }

    /**
     * 导出心理咨询师列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:export')")
    @Log(title = "心理咨询师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyAdminConsultReq psyConsult)
    {
        List<PsyConsult> list = psyConsultService.getList(psyConsult);
        ExcelUtil<PsyConsult> util = new ExcelUtil<PsyConsult>(PsyConsult.class);
        util.exportExcel(response, list, "心理咨询师数据");
    }

    /**
     * 获取心理咨询师详细信息
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultService.getOne(id));
    }

    /**
     * 新增咨询服务配置
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:edit')")
    @Log(title = "咨询服务配置", businessType = BusinessType.INSERT)
    @PostMapping("/refConsultServe")
    public AjaxResult refConsultServe(@RequestBody PsyRefConsultServeReq req)
    {
        AjaxResult ajaxResult = psyConsultService.refConsultServe(req);
        return ajaxResult;
    }

    @PreAuthorize("@ss.hasPermi('psychology:consult:edit')")
    @PostMapping("/delConsultServeRef")
    public AjaxResult delConsultServeRef(@RequestBody PsyConsultServe req)
    {
        if (req.getServeId() == null || req.getConsultId() == null) {
            return AjaxResult.error();
        }
        return toAjax(psyConsultService.delConsultServeRef(req));
    }

    /**
     * 新增心理咨询师
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:add')")
    @Log(title = "心理咨询师", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultVO psyConsult)
    {
        if (!SecurityUtils.isAdmin(getUserId())) {
            return error("必须是超级管理员才可以添加咨询师");
        }
        AjaxResult add = psyConsultService.add(psyConsult);
        psyConsultService.getOne(psyConsult.getId());
        return add;
    }

    /**
     * 修改心理咨询师
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:edit')")
    @Log(title = "心理咨询师", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultVO psyConsult)
    {
        AjaxResult update = psyConsultService.update(psyConsult);
        psyConsultService.getOne(psyConsult.getId());
        return update;
    }

    /**
     * 删除心理咨询师
     */
    @PreAuthorize("@ss.hasPermi('psychology:consult:remove')")
    @Log(title = "心理咨询师", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        if (!SecurityUtils.isAdmin(getUserId())) {
            return error("必须是超级管理员才可以删除咨询师");
        }
        if (ids == null || ids.length == 0) {
            return error("请选择数据进行删除");
        }
        return toAjax(psyConsultService.deleteAll(ids));
    }

    /**
     * 所有咨询师全量关联服务
     * @return
     */
    @PostMapping("/addAllRelation")
    public AjaxResult addAllRelation()
    {
        if (!SecurityUtils.isAdmin(getUserId())) {
            return error("必须是超级管理员才可以执行");
        }
        psyConsultService.addAllRelation();
        return AjaxResult.success();
    }

    /**
     * 刷新咨询师缓存
     */
    @ApiOperation("刷新咨询师缓存")
    //@PreAuthorize("@ss.hasPermi('system:supervision:query')")
    @GetMapping(value = "/refreshCacheAll")
    public AjaxResult refreshCacheAll()
    {
        psyConsultService.refreshCacheAll();
        return AjaxResult.success();
    }
    
}
