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
import com.ruoyi.system.domain.FjxUserAddress;
import com.ruoyi.system.service.IFjxUserAddressService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户地址Controller
 * 
 * @author ruoyi
 * @date 2024-11-03
 */
@RestController
@RequestMapping("/fjx/address")
public class FjxUserAddressController extends BaseController
{
    @Autowired
    private IFjxUserAddressService fjxUserAddressService;

    /**
     * 查询用户地址列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(FjxUserAddress fjxUserAddress)
    {
        startPage();
        List<FjxUserAddress> list = fjxUserAddressService.selectFjxUserAddressList(fjxUserAddress);
        return getDataTable(list);
    }

    /**
     * 导出用户地址列表
     */
    @PreAuthorize("@ss.hasPermi('fjx:address:export')")
    @Log(title = "用户地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FjxUserAddress fjxUserAddress)
    {
        List<FjxUserAddress> list = fjxUserAddressService.selectFjxUserAddressList(fjxUserAddress);
        ExcelUtil<FjxUserAddress> util = new ExcelUtil<FjxUserAddress>(FjxUserAddress.class);
        util.exportExcel(response, list, "用户地址数据");
    }

    /**
     * 获取用户地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('fjx:address:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fjxUserAddressService.selectFjxUserAddressById(id));
    }

    /**
     * 新增用户地址
     */
    @PreAuthorize("@ss.hasPermi('fjx:address:add')")
    @Log(title = "用户地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FjxUserAddress fjxUserAddress)
    {
        return toAjax(fjxUserAddressService.insertFjxUserAddress(fjxUserAddress));
    }

    /**
     * 修改用户地址
     */
    @PreAuthorize("@ss.hasPermi('fjx:address:edit')")
    @Log(title = "用户地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FjxUserAddress fjxUserAddress)
    {
        return toAjax(fjxUserAddressService.updateFjxUserAddress(fjxUserAddress));
    }

    /**
     * 删除用户地址
     */
    @PreAuthorize("@ss.hasPermi('fjx:address:remove')")
    @Log(title = "用户地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fjxUserAddressService.deleteFjxUserAddressByIds(ids));
    }
}
