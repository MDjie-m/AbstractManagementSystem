package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantDebitcard;
import com.renxin.psychology.service.IPsyConsultantDebitcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 客户银行卡Controller
 * 
 * @author renxin
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/finance/debitcard")
public class PsyConsultantDebitcardController extends BaseController
{
    @Autowired
    private IPsyConsultantDebitcardService psyConsultantDebitcardService;

    /**
     * 查询客户银行卡列表
     */
    @PreAuthorize("@ss.hasPermi('system:debitcard:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantDebitcard psyConsultantDebitcard)
    {
        startPage();
        List<PsyConsultantDebitcard> list = psyConsultantDebitcardService.selectPsyConsultantDebitcardList(psyConsultantDebitcard);
        return getDataTable(list);
    }

    /**
     * 导出客户银行卡列表
     */
    @PreAuthorize("@ss.hasPermi('system:debitcard:export')")
    @Log(title = "客户银行卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantDebitcard psyConsultantDebitcard)
    {
        List<PsyConsultantDebitcard> list = psyConsultantDebitcardService.selectPsyConsultantDebitcardList(psyConsultantDebitcard);
        ExcelUtil<PsyConsultantDebitcard> util = new ExcelUtil<PsyConsultantDebitcard>(PsyConsultantDebitcard.class);
        util.exportExcel(response, list, "客户银行卡数据");
    }

    /**
     * 获取客户银行卡详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:debitcard:query')")
    @GetMapping(value = "/{cardNumber}")
    public AjaxResult getInfo(@PathVariable("cardNumber") String cardNumber)
    {
        return AjaxResult.success(psyConsultantDebitcardService.selectPsyConsultantDebitcardByCardNumber(cardNumber));
    }

    /**
     * 新增客户银行卡
     */
    @PreAuthorize("@ss.hasPermi('system:debitcard:add')")
    @Log(title = "客户银行卡", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantDebitcard psyConsultantDebitcard)
    {
        return toAjax(psyConsultantDebitcardService.insertPsyConsultantDebitcard(psyConsultantDebitcard));
    }

    /**
     * 修改客户银行卡
     */
    @PreAuthorize("@ss.hasPermi('system:debitcard:edit')")
    @Log(title = "客户银行卡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantDebitcard psyConsultantDebitcard)
    {
        return toAjax(psyConsultantDebitcardService.updatePsyConsultantDebitcard(psyConsultantDebitcard));
    }

    /**
     * 删除客户银行卡
     */
    @PreAuthorize("@ss.hasPermi('system:debitcard:remove')")
    @Log(title = "客户银行卡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cardNumbers}")
    public AjaxResult remove(@PathVariable String[] cardNumbers)
    {
        return toAjax(psyConsultantDebitcardService.deletePsyConsultantDebitcardByCardNumbers(cardNumbers));
    }
}
