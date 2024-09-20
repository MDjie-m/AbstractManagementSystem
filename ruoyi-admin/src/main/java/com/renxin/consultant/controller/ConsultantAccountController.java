package com.renxin.consultant.controller;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.psychology.domain.PsyConsultantAccount;
import com.renxin.psychology.domain.PsyConsultantAccountRecord;
import com.renxin.psychology.service.IPsyConsultantAccountRecordService;
import com.renxin.psychology.service.IPsyConsultantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 账户Controller
 * 
 * @author renxin
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/consultant/account")
public class ConsultantAccountController extends BaseController
{
    @Autowired
    private IPsyConsultantAccountService psyConsultantAccountService;

    @Autowired
    private IPsyConsultantAccountRecordService psyConsultantAccountRecordService;

    @Resource
    ConsultantTokenService consultantTokenService;

    /**
     * 余额查询
     */
    //@PreAuthorize("@ss.hasPermi('system:record:add')")
    @PostMapping("/amount")
    public AjaxResult queryAcctAmount(HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        BigDecimal acctAmount = psyConsultantAccountRecordService.calcAcctAmount(consultId);
        return AjaxResult.success(acctAmount);
    }

    /**
     * 取款申请
     */
    //@PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "账户明细流水", businessType = BusinessType.INSERT)
    @PostMapping("/cashWithdrawal")
    public AjaxResult cashWithdrawal(@RequestBody PsyConsultantAccountRecord psyConsultantAccountRecord, HttpServletRequest request)
    {
        Long consultId = consultantTokenService.getConsultId(request);
        psyConsultantAccountRecord.setConsultantId(consultId);
        psyConsultantAccountRecord.setPayType("1");//取款
        psyConsultantAccountRecordService.insertPsyConsultantAccountRecord(psyConsultantAccountRecord);
        return AjaxResult.success("取款申请提交成功");
    }
    
    
    /**
     * 查询账户列表
     */
    @PreAuthorize("@ss.hasPermi('system:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantAccount psyConsultantAccount)
    {
        startPage();
        List<PsyConsultantAccount> list = psyConsultantAccountService.selectPsyConsultantAccountList(psyConsultantAccount);
        return getDataTable(list);
    }

   

    /**
     * 获取账户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:account:query')")
    @GetMapping(value = "/{consultantId}")
    public AjaxResult getInfo(@PathVariable("consultantId") Long consultantId)
    {
        PsyConsultantAccount account = psyConsultantAccountService.selectPsyConsultantAccountByConsultantId(consultantId);
        return AjaxResult.success(account);
    }

  

    /**
     * 修改账户
     */
    @PreAuthorize("@ss.hasPermi('system:account:edit')")
    @Log(title = "账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantAccount psyConsultantAccount)
    {
        return toAjax(psyConsultantAccountService.updatePsyConsultantAccount(psyConsultantAccount));
    }

   
}
