package com.renxin.web.controller.supervision;

import com.github.pagehelper.PageHelper;
import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.domain.dto.ConsultDTO;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 团队督导(组织)Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/supervision-team")
@Api(value = "PsyConsultantTeamSupervisionController" ,tags = {"管理端-团队督导(组织)Api"})
public class PsyConsultantTeamSupervisionController extends BaseController
{
    @Autowired
    private IPsyConsultantTeamSupervisionService psyConsultantTeamSupervisionService;

    @Resource
    private ConsultantTokenService consultantTokenService;
    
    /**
     * 查询团队督导(组织)列表
     */
    @ApiOperation(value = "查询团队督导(组织)列表")
    //@PreAuthorize("@ss.hasPermi('system:supervision:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantTeamSupervision req)
    {
        startPage();
        List<PsyConsultantTeamSupervision> list = psyConsultantTeamSupervisionService.selectPsyConsultantTeamSupervisionList(req);
        return getDataTable(list);
    }

    /**
     * 导出团队督导(组织)列表
     */
//    @ApiOperation("导出团队督导(组织)列表")
//    //@PreAuthorize("@ss.hasPermi('system:supervision:export')")
//    @Log(title = "团队督导(组织)", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, PsyConsultantTeamSupervision psyConsultantTeamSupervision)
//    {
//        List<PsyConsultantTeamSupervision> list = psyConsultantTeamSupervisionService.selectPsyConsultantTeamSupervisionList(psyConsultantTeamSupervision);
//        ExcelUtil<PsyConsultantTeamSupervision> util = new ExcelUtil<PsyConsultantTeamSupervision>(PsyConsultantTeamSupervision.class);
//        util.exportExcel(response, list, "团队督导(组织)数据");
//    }

    /**
     * 获取团队督导(组织)详细信息
     */
    @ApiOperation("获取团队督导(组织)详细信息")
    //@PreAuthorize("@ss.hasPermi('system:supervision:query')")
    @GetMapping(value = "/queryById/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        PsyConsultantTeamSupervision team = psyConsultantTeamSupervisionService.selectPsyConsultantTeamSupervisionById(id);
        return AjaxResult.success(team);
    }

    /**
     * 新增团队督导(组织)
     */
    @ApiOperation("新增团队督导(组织)")
    //@PreAuthorize("@ss.hasPermi('system:supervision-team:add')")
    @Log(title = "团队督导(组织)", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PsyConsultantTeamSupervision psyConsultantTeamSupervision)
    {
        return toAjax(psyConsultantTeamSupervisionService.insertPsyConsultantTeamSupervision(psyConsultantTeamSupervision));
    }

    /**
     * 修改团队督导(组织)
     */
    @ApiOperation("修改团队督导(组织)")
    //@PreAuthorize("@ss.hasPermi('system:supervision:edit')")
    @Log(title = "团队督导(组织)", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PsyConsultantTeamSupervision psyConsultantTeamSupervision)
    {
        return toAjax(psyConsultantTeamSupervisionService.updatePsyConsultantTeamSupervision(psyConsultantTeamSupervision));
    }

    /**
     * 删除团队督导(组织)
     */
    @ApiOperation("删除团队督导(组织)")
    //@PreAuthorize("@ss.hasPermi('system:supervision:remove')")
    @Log(title = "团队督导(组织)", businessType = BusinessType.DELETE)
	@DeleteMapping("/deleteByIds/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyConsultantTeamSupervisionService.deletePsyConsultantTeamSupervisionByIds(ids));
    }
}
