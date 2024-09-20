package com.renxin.consultant.controller;

import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.exception.ServiceException;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsultantDebitcard;
import com.renxin.psychology.service.IPsyConsultantDebitcardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/consultant/debitcard")
@Api(value = "ConsultantDebitcardController" ,tags = {"咨询师银行卡管理Api"})
public class ConsultantDebitcardController extends BaseController {

    @Resource
    private IPsyConsultantDebitcardService psyConsultantDebitcardService;
    
    @Resource
    private ConsultantTokenService consultantTokenService;

    @ApiOperation(value = "查询客户银行卡列表")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody PsyConsultantDebitcard psyConsultantDebitcard, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        psyConsultantDebitcard.setConsultantId(consultId);
        startPage();
        List<PsyConsultantDebitcard> list = psyConsultantDebitcardService.selectPsyConsultantDebitcardList(psyConsultantDebitcard);
        return getDataTable(list);
    }

    @ApiOperation(value = "获取客户银行卡详细信息")
    @PostMapping(value = "/detail")
    public AjaxResult getInfo(@RequestBody PsyConsultantDebitcard req,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        PsyConsultantDebitcard psyConsultantDebitcard = psyConsultantDebitcardService.selectPsyConsultantDebitcardByCardNumber(req.getCardNumber());
        if (psyConsultantDebitcard.getConsultantId().equals(consultId) ){
            return AjaxResult.success(psyConsultantDebitcard);
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "新增客户银行卡")
    @PostMapping(value = "/create")
    public AjaxResult add(@RequestBody PsyConsultantDebitcard psyConsultantDebitcard,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        psyConsultantDebitcard.setConsultantId(consultId);
        return toAjax(psyConsultantDebitcardService.insertPsyConsultantDebitcard(psyConsultantDebitcard));
    }

    @ApiOperation(value = "修改客户银行卡")
    @PostMapping(value = "/edit")
    public AjaxResult edit(@RequestBody PsyConsultantDebitcard psyConsultantDebitcard,HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        psyConsultantDebitcard.setConsultantId(consultId);
        return toAjax(psyConsultantDebitcardService.updatePsyConsultantDebitcard(psyConsultantDebitcard));
    }

    @ApiOperation(value = "删除客户银行卡")
    @PostMapping("/delete")
    public AjaxResult remove(@RequestBody PsyConsultantDebitcard req,HttpServletRequest request)
    {
        int count = 0;
        Long consultId = consultantTokenService.getConsultId(request);
        PsyConsultantDebitcard listReq = new PsyConsultantDebitcard();
            listReq.setConsultantId(consultId);
        List<PsyConsultantDebitcard> list = psyConsultantDebitcardService.selectPsyConsultantDebitcardList(listReq);
        //判断是否属于当前咨询师
        if (list.stream().anyMatch(card -> req.getCardNumber().equals(card.getCardNumber()))){
            //属于当前咨询师, 判断其是否为默认卡
            PsyConsultantDebitcard debitcard = psyConsultantDebitcardService.selectPsyConsultantDebitcardByCardNumber(req.getCardNumber());
            if (debitcard.getIsDefault() == 0){
                throw new ServiceException("默认卡无法删除, 请先将其他卡设为默认");
            }
            count = psyConsultantDebitcardService.deletePsyConsultantDebitcardByCardNumbers(new String[]{req.getCardNumber()});
        }

        return toAjax(count);
    }
}
