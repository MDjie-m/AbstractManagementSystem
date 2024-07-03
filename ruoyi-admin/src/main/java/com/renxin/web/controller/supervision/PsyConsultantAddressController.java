package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantAddress;
import com.renxin.psychology.service.IPsyConsultantAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师地址Controller
 * 
 * @author renxin
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/finance/address")
public class PsyConsultantAddressController extends BaseController
{
    @Autowired
    private IPsyConsultantAddressService psyConsultantAddressService;

    /**
     * 查询咨询师地址列表
     */
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantAddress psyConsultantAddress)
    {
        startPage();
        List<PsyConsultantAddress> list = psyConsultantAddressService.selectPsyConsultantAddressList(psyConsultantAddress);
        return getDataTable(list);
    }

    /**
     * 导出咨询师地址列表
     */
    @Log(title = "咨询师地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantAddress psyConsultantAddress)
    {
        List<PsyConsultantAddress> list = psyConsultantAddressService.selectPsyConsultantAddressList(psyConsultantAddress);
        ExcelUtil<PsyConsultantAddress> util = new ExcelUtil<PsyConsultantAddress>(PsyConsultantAddress.class);
        util.exportExcel(response, list, "咨询师地址数据");
    }

    /**
     * 获取咨询师地址详细信息
     */
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        return AjaxResult.success(psyConsultantAddressService.selectPsyConsultantAddressByAddressId(addressId));
    }

    /**
     * 新增咨询师地址
     */
    @Log(title = "咨询师地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantAddress psyConsultantAddress)
    {
        return toAjax(psyConsultantAddressService.insertPsyConsultantAddress(psyConsultantAddress));
    }

    /**
     * 修改咨询师地址
     */
    @Log(title = "咨询师地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantAddress psyConsultantAddress)
    {
        return toAjax(psyConsultantAddressService.updatePsyConsultantAddress(psyConsultantAddress));
    }

    /**
     * 删除咨询师地址
     */
    @Log(title = "咨询师地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(psyConsultantAddressService.deletePsyConsultantAddressByAddressIds(addressIds));
    }
}
