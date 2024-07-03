package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantSupervisionMember;
import com.renxin.psychology.service.IPsyConsultantSupervisionMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 督导成员Controller
 * 
 * @author renxin
 * @date 2024-06-26
 */
@RestController
@RequestMapping("/system/member")
public class PsyConsultantSupervisionMemberController extends BaseController
{
    @Autowired
    private IPsyConsultantSupervisionMemberService psyConsultantSupervisionMemberService;

    /**
     * 查询督导成员列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantSupervisionMember psyConsultantSupervisionMember)
    {
        startPage();
        List<PsyConsultantSupervisionMember> list = psyConsultantSupervisionMemberService.selectPsyConsultantSupervisionMemberList(psyConsultantSupervisionMember);
        return getDataTable(list);
    }

    /**
     * 导出督导成员列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:export')")
    @Log(title = "督导成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantSupervisionMember psyConsultantSupervisionMember)
    {
        List<PsyConsultantSupervisionMember> list = psyConsultantSupervisionMemberService.selectPsyConsultantSupervisionMemberList(psyConsultantSupervisionMember);
        ExcelUtil<PsyConsultantSupervisionMember> util = new ExcelUtil<PsyConsultantSupervisionMember>(PsyConsultantSupervisionMember.class);
        util.exportExcel(response, list, "督导成员数据");
    }

    /**
     * 获取督导成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:member:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyConsultantSupervisionMemberService.selectPsyConsultantSupervisionMemberById(id));
    }

    /**
     * 新增督导成员
     */
    @PreAuthorize("@ss.hasPermi('system:member:add')")
    @Log(title = "督导成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantSupervisionMember psyConsultantSupervisionMember)
    {
        return toAjax(psyConsultantSupervisionMemberService.insertPsyConsultantSupervisionMember(psyConsultantSupervisionMember));
    }

    /**
     * 修改督导成员
     */
    @PreAuthorize("@ss.hasPermi('system:member:edit')")
    @Log(title = "督导成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantSupervisionMember psyConsultantSupervisionMember)
    {
        return toAjax(psyConsultantSupervisionMemberService.updatePsyConsultantSupervisionMember(psyConsultantSupervisionMember));
    }

    /**
     * 删除督导成员
     */
    @PreAuthorize("@ss.hasPermi('system:member:remove')")
    @Log(title = "督导成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyConsultantSupervisionMemberService.deletePsyConsultantSupervisionMemberByIds(ids));
    }
}
