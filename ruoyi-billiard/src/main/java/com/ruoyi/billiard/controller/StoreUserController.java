package com.ruoyi.billiard.controller;

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
import com.ruoyi.billiard.domain.StoreUser;
import com.ruoyi.billiard.service.IStoreUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门店员工Controller
 * 
 * @author ruoyi
 * @date 2024-09-05
 */
@RestController
@RequestMapping("/billiard/user")
public class StoreUserController extends BaseController
{
    @Autowired
    private IStoreUserService storeUserService;

    /**
     * 查询门店员工列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreUser storeUser)
    {
        startPage();
        List<StoreUser> list = storeUserService.selectStoreUserList(storeUser);
        return getDataTable(list);
    }

    /**
     * 导出门店员工列表
     */
    @PreAuthorize("@ss.hasPermi('billiard:user:export')")
    @Log(title = "门店员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreUser storeUser)
    {
        List<StoreUser> list = storeUserService.selectStoreUserList(storeUser);
        ExcelUtil<StoreUser> util = new ExcelUtil<StoreUser>(StoreUser.class);
        util.exportExcel(response, list, "门店员工数据");
    }

    /**
     * 获取门店员工详细信息
     */
    @PreAuthorize("@ss.hasPermi('billiard:user:query')")
    @GetMapping(value = "/{storeUserId}")
    public AjaxResult getInfo(@PathVariable("storeUserId") Long storeUserId)
    {
        return success(storeUserService.selectStoreUserByStoreUserId(storeUserId));
    }

    /**
     * 新增门店员工
     */
    @PreAuthorize("@ss.hasPermi('billiard:user:add')")
    @Log(title = "门店员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreUser storeUser)
    {
        return toAjax(storeUserService.insertStoreUser(storeUser));
    }

    /**
     * 修改门店员工
     */
    @PreAuthorize("@ss.hasPermi('billiard:user:edit')")
    @Log(title = "门店员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreUser storeUser)
    {
        return toAjax(storeUserService.updateStoreUser(storeUser));
    }

    /**
     * 删除门店员工
     */
    @PreAuthorize("@ss.hasPermi('billiard:user:remove')")
    @Log(title = "门店员工", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeUserIds}")
    public AjaxResult remove(@PathVariable Long[] storeUserIds)
    {
        return toAjax(storeUserService.deleteStoreUserByStoreUserIds(storeUserIds));
    }
}
