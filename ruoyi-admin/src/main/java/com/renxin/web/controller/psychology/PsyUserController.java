package com.renxin.web.controller.psychology;

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
import com.renxin.psychology.domain.PsyUser;
import com.renxin.psychology.service.IPsyUserService;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.common.core.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author renxin
 * @date 2022-08-26
 */
@RestController
@RequestMapping("/psychology/user")
public class PsyUserController extends BaseController
{
    @Autowired
    private IPsyUserService psyUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyUser psyUser)
    {
        startPage();
        List<PsyUser> list = psyUserService.selectPsyUserList(psyUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('psychology:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyUser psyUser)
    {
        List<PsyUser> list = psyUserService.selectPsyUserList(psyUser);
        ExcelUtil<PsyUser> util = new ExcelUtil<PsyUser>(PsyUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('psychology:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(psyUserService.selectPsyUserById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('psychology:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyUser psyUser)
    {
        return toAjax(psyUserService.insertPsyUser(psyUser));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('psychology:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyUser psyUser)
    {
        return toAjax(psyUserService.updatePsyUser(psyUser));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('psychology:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(psyUserService.deletePsyUserByIds(ids));
    }
}
