package com.renxin.consultant.controller;

import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsultantAddress;
import com.renxin.psychology.service.IPsyConsultantAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consultant/address")
@Api(value = "ConsultantAddressController" ,tags = {"咨询师地址管理Api"})
public class ConsultantAddressController extends BaseController {

    @Resource
    private IPsyConsultantAddressService psyConsultantAddressService;

    @Resource
    ConsultantTokenService consultantTokenService;

    @ApiOperation(value = "查询咨询师地址列表")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantAddress psyConsultantAddress ,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        psyConsultantAddress.setConsultantId(consultId);
        startPage();
        List<PsyConsultantAddress> list = psyConsultantAddressService.selectPsyConsultantAddressList(psyConsultantAddress);
        return getDataTable(list);
    }

    @ApiOperation(value = "获取咨询师地址详细信息")
    @PostMapping(value = "/detail")
    public AjaxResult getInfo(PsyConsultantAddress req ,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        PsyConsultantAddress psyConsultantAddress = psyConsultantAddressService.selectPsyConsultantAddressByAddressId(req.getAddressId());
        if (psyConsultantAddress.getConsultantId() == consultId){
            return AjaxResult.success(psyConsultantAddress);
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "新增咨询师地址")
    @PostMapping(value = "/add")
    public AjaxResult add(@RequestBody PsyConsultantAddress psyConsultantAddress,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        psyConsultantAddress.setConsultantId(consultId);
        return toAjax(psyConsultantAddressService.insertPsyConsultantAddress(psyConsultantAddress));
    }

    @ApiOperation(value = "修改咨询师地址")
    @PostMapping(value = "/edit")
    public AjaxResult edit(@RequestBody PsyConsultantAddress psyConsultantAddress,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        psyConsultantAddress.setConsultantId(consultId);
        return toAjax(psyConsultantAddressService.updatePsyConsultantAddress(psyConsultantAddress));
    }

    /**
     * 删除咨询师地址
     */
//    @ApiOperation(value = "删除咨询师地址")
//    @PostMapping("/{addressIds}")
//    public AjaxResult remove(@PathVariable Long[] addressIds)
//    {
//        return toAjax(psyConsultantAddressService.deletePsyConsultantAddressByAddressIds(addressIds));
//    }

}
